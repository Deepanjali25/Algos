/* Write a function that takes in a binary tree and inverts it. In other words, the function should swap every left node
in the tree for its corresponding right node. Each BinaryTree node has an integer value, a left child node, and a right
 child node. Children nodes can either be BinaryTree nodes themselves or None/null.
 Sample Input
 tree =   1
         /   \
        2     3
      /  \   /  \
     4    5 6    7
    / \
   8   9
Sample Output
tree =     1
         /   \
        3     2
      /  \   /  \
     7    6  5   4
                / \
               9   8
Hint 1 - Start by inverting the root node of the Binary Tree. Inverting this root node simply consists of
swapping its left and right child nodes, which can be done the same way as swapping two
variables.
Hint 2 - Once the first swap mentioned in Hint #1 is done, you must invert the root node's left child node
and its right child node. You can do so just as you did for the root node. Then, you will have to
continue inverting child nodes' nodes until you reach the bottom of the tree.
Hint 3 - How can you accomplish the process described in Hint #2? While recursion seems appropriate,
would an iterative approach work? What would be the time and space complexity implications of
both approaches?

Optimal Space & Time Complexity - O(n) time | O(d) space - where n is the number of nodes in the Binary Tree and d is the depth
(height) of the Binary Tree
*/
package BinaryTrees;

import java.util.ArrayDeque;

public class InvertBinaryTreeSolutionI {
    //O(n) time | O(n) space
    public static void invertBinaryTree(BinaryTree tree) {
        ArrayDeque<BinaryTree> queue = new ArrayDeque<BinaryTree>();
        queue.addLast(tree);
        while (queue.size() > 0) {
            BinaryTree current = queue.pollFirst();
            swapLeftAndRight(current);
            if (current.left != null) {
                queue.addLast(current.left);
            }
            if (current.right != null) {
                queue.addLast(current.right);
            }
        }
    }
    public static void swapLeftAndRight(BinaryTree tree) {
        BinaryTree left = tree.left;
        tree.left = tree.right;
        tree.right = left;
    }
    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
