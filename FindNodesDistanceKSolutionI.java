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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
public class FindNodesDistanceKSolutionI {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
    static class Pair<U, V> {
        public final U first;
        public final V second;
        private Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }
    //O(n) time | 0(n) space - where n is the number of nodes in the tree
    public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k) {
        HashMap<Integer, BinaryTree> nodesToParents = new HashMap<Integer, BinaryTree>();
        populateNodesToParents(tree, nodesToParents, null);
        BinaryTree targetNode = getNodeFromValue(target, tree, nodesToParents);
        return breadthFirstSearchForNodesDistanceK(targetNode, nodesToParents, k);
    }
    public ArrayList<Integer> breadthFirstSearchForNodesDistanceK
            (BinaryTree targetNode, HashMap<Integer, BinaryTree> nodesToParents, int k) {
        Queue<Pair<BinaryTree, Integer>> queue = new LinkedList<Pair<BinaryTree, Integer>>();
        queue.offer(new Pair<BinaryTree, Integer>(targetNode, 0));
        HashSet<Integer> seen = new HashSet<Integer>(targetNode.value);
        seen.add(targetNode.value);
        while (queue.size() > 0) {
            Pair<BinaryTree, Integer> vals = queue.poll();
            BinaryTree currentNode = vals.first;
            int distanceFromTarget = vals.second;
            if (distanceFromTarget == k) {
                ArrayList<Integer> nodeDistanceK = new ArrayList<Integer>();
                for (Pair<BinaryTree, Integer> pair : queue) {
                    nodeDistanceK.add(pair.first.value);
                }
                nodeDistanceK.add(currentNode.value);
                return nodeDistanceK;
            }
            List<BinaryTree> connectedNodes = new ArrayList<BinaryTree>();
            connectedNodes.add(currentNode.left);
            connectedNodes.add(currentNode.right);
            connectedNodes.add(nodesToParents.get(currentNode.value));
            for (BinaryTree node : connectedNodes) {
                if (node == null) continue;
                if (seen.contains(node.value)) continue;
                queue.add(new Pair<BinaryTree, Integer>(node, distanceFromTarget + 1));
            }
        }
        return new ArrayList<Integer>();
    }
    public BinaryTree getNodeFromValue(int value, BinaryTree tree, HashMap<Integer, BinaryTree> nodesToParents) {
        if (tree.value == value) return tree;
        BinaryTree nodeParent = nodesToParents.get(value);
        if (nodeParent.left != null && nodeParent.left.value == value)
            return nodeParent.left;
        return nodeParent.right;
    }
    public void populateNodesToParents(BinaryTree node, Map<Integer, BinaryTree> nodesToParents, BinaryTree parent) {
        if (node != null) {
            nodesToParents.put(node.value, parent);
            populateNodesToParents(node.left, nodesToParents, node);
            populateNodesToParents(node.right, nodesToParents, node);
        }
    }
}