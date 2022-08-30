/* Write a function that takes in the root nodes of two Binary Trees and returns a boolean representing whether
their leaf traversals are the same. The leaf traversal of a Binary Tree traverses only its leaf nodes from left to right. A
leaf node is any node that has no left or right children. For example, the leaf traversal of the following Binary Tree
is 1, 3, 2.
       4
     /   \
    1     5
         /  \
        3    2
Each BinaryTree node has an integer value, a left child node, and a right child node. Children nodes can either be BinaryTree
nodes themselves or None/null.
Sample Input:
tree1 =   1                           tree2 =   1
         /  \                                  /  \
        2    3                                2    3
       / \    \                              / \    \
      4   5    6                            4   7    5
         /  \                                       /  \
        7    8                                     8    6
Sample Output: true
#Hint1 - To traverse the leaf nodes of a tree from left to right, you'll need to use a pre-order traversal.
#Hint2 - The simplest approach to solving this problem is to perform a pre-order traversal on both trees, to store their leaf
nodes in arrays in the order in which they're visited, and to then compare the two resulting arrays.
This solutions works, but it's not optimal from a space-complexity perspective. Can you think of a way to solve this problem
using less extra space? It's possible to solve this with 0(h1 + h2) space or better, where
h1 is the height of tree1 and h2 is the height of tree2.
#Hint3 - To solve this problem with a more optimal space complexity, you can perform pre-order traversals on both trees at
the same time. As you traverse the trees, you need to look for the next leaf node in each tree and
pause the traversal as soon as you find it. Once you've found the next leaf node in both trees, you can compare their
values and see if they match; if they do, continue the traversal , and repeat the process. If they
don't match, the leaf traversals aren't the same, and you can return false.
#Hint4 - Another unique way to solve this problem is to connect all of the leaf nodes in each individual tree so as to form
two linked lists. Since the leaf nodes don't have any children, you can use their right pointers to
store the next leaf nodes in the leaf traversals. And since you're reusing the input trees to store the leaf traversals, the
only extra space you'll be using comes from the recursion used in the traversal of the trees. Once
both trees have their leaf nodes connected, you can iterate through the linked lists and check if they're the same. To compare
the linked lists, you'll need to keep track of their heads (the first leaf node in each tree).
Optimal Space & Time Complexity - O(n + m) time | O(max(h1, h2)) space - where n is the number of nodes in the first Binary
Tree, m is the number in the second, h1 is the height of the first Binary Tree, and h2 is the height of the second
*/
package BinaryTrees;
import java.util.Stack;
public class CompareLeafTraversalSolutionI {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
    // O(n+m) time | O(h1+h2) space - where n is the number of nodes in the first Binary Tree, m is the number in the second,
    // h1 is the height of the first Binary Tree, and h2 is the height of the second
    public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) {
        Stack<BinaryTree> tree1TraversalStack = new Stack<BinaryTree>();
        tree1TraversalStack.push(tree1);
        Stack<BinaryTree> tree2TraversalStack = new Stack<BinaryTree>();
        tree2TraversalStack.push(tree2);
        while (tree1TraversalStack.size() > 0 && tree2TraversalStack.size() > 0) {
            BinaryTree tree1Leaf = getNextLeafNode(tree1TraversalStack);
            BinaryTree tree2Leaf = getNextLeafNode(tree2TraversalStack);
            if (tree1Leaf.value != tree2Leaf.value) {
                return false;
            }
        }
        return (tree1TraversalStack.size() == 0) && (tree2TraversalStack.size() == 0);
    }
    public BinaryTree getNextLeafNode(Stack<BinaryTree> traversalStack) {
        BinaryTree currentNode = traversalStack.pop();
        while (!isLeafNode(currentNode)) {
            if (currentNode.right != null) {
                traversalStack.push(currentNode.right);
            }
            // We purposely add the left node to the stack after the right node so that it gets popped off the stack first.
            if (currentNode.left != null) {
                traversalStack.push(currentNode.left);
            }
            currentNode = traversalStack.pop();
        }
        return currentNode;
    }
    public boolean isLeafNode(BinaryTree node) {
        return (node.left == null) && (node.right == null);
    }
}