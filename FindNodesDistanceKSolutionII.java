/* You're given the root node of a Binary Tree, a target value of a node that's contained in the tree, 3
and a positive integer k . Write a function that returns the values of all the nodes that are exactly distance k from
the node with target value. The distance between two nodes is defined as the number of edges that must be traversed to go
from one node to the other. For example, the distance between a node and its immediate left or right child
is 1 .The same holds in reverse: the distance between a node and its parent is 1 .In a tree of three
nodes where the root node has a left and right child, the left and right children are distance 2 from each other.
Each BinaryTree node has an integer value ,a left child node, and a right child node.
Children nodes can either be BinaryTree nodes themselves or None / null.
Note that all BinaryTree node values will be unique, and your function can return the output values in any order.
Sample Input:
tree =   1
        /  \
       2    3
      / \    \
     4   5    6
             /  \
            7    8
            target = 3
            k = 2
Sample Output:
[2, 7, 8] //These values could be ordered differently.
Hint 1 - Would it be easier to solve this problem if you had information about every node’s parent node?
Hint2 - One approach to this problem is to find the parent nodes of all nodes in the tree. With this
information you can perform a breadth-first search starting at the target node and traverse
through each neighbor (left, right, and parent node) of every node, keeping track of your
distance from the target node at each iteration. Once you reach a node that is distance k from
the target node, you can add it to your output array. You'll have to also keep track of which
nodes you've visited so as to avoid visiting the same nodes over and over again.
Hint3 - Another approach is to use a recursive depth-first-search algorithm as follows:
* Case #1:when currentNode == target , search the subtree rooted at currentNode for all nodes that are k distance from
currentNode.
* Case #2:when target is in the left subtree of currentNode at distance L + 1 ,look
for nodes that are distance k - L - 1 in the right subtree of currentNode.
* Case #3:when target is in the right subtree of currentNode at distance L + 1 ,do
the same thing as in case #2 but in the opposite subtree.
* Case #4:when target is neither in the left nor in right subtree of currentNode , stop recursing.
Optimal Space & Time Complexity - O(n) time | O(n) space - where n is the number of nodes in the tree
*/
package BinaryTrees;
import java.util.ArrayList;
public class FindNodesDistanceKSolutionII {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;
        public BinaryTree(int value) {
            this.value = value;
        }
    }
    // O(n) time | O(n) space - where n is the number of nodes in the tree
    public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k) {
        ArrayList<Integer> nodesDistanceK = new ArrayList<Integer>();
        findDistanceFromNodeToTarget(tree, target, k, nodesDistanceK);
        return nodesDistanceK;
    }
    public int findDistanceFromNodeToTarget(BinaryTree node, int target, int k, ArrayList<Integer> nodesDistanceK) {
        if (node == null) return -1;
        if (node.value == target) {
            addSubtreeNodeAtDistanceK(node, 0, k, nodesDistanceK);
            return 1;
        }
        int leftDistance = findDistanceFromNodeToTarget(node.left, target, k, nodesDistanceK);
        int rightDistance = findDistanceFromNodeToTarget(node.right, target, k, nodesDistanceK);
        if (leftDistance == k || rightDistance == k) nodesDistanceK.add(node.value);
        if (leftDistance != -1) {
            addSubtreeNodeAtDistanceK (node.right, leftDistance + 1, k, nodesDistanceK);
            return leftDistance + 1;
        }
        if (rightDistance != -1) {
            addSubtreeNodeAtDistanceK (node.left, rightDistance + 1, k, nodesDistanceK);
            return rightDistance + 1;
        }
        return -1;
    }
    void addSubtreeNodeAtDistanceK (BinaryTree node, int distance, int k, ArrayList<Integer> nodesDistanceK) {
        if (node == null) return;
        if (distance == k) {
            nodesDistanceK.add(node.value);
        }
        else {
            addSubtreeNodeAtDistanceK(node.left, distance + 1, k, nodesDistanceK);
            addSubtreeNodeAtDistanceK(node.right, distance + 1, k, nodesDistanceK);
        }
    }
}