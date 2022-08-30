/* Write a function that takes in a Binary Tree and returns its max path sum.
A path is a collection of connected nodes in a tree, where no node is connected to more than two
other nodes; a path sum is the sum of the values of the nodes in a particular path.
Each BinaryTree node has an integer value ,a left child node,and a right child node.
Children nodes can either be BinaryTree nodes themselves or None/null .
Sample Input:  tree =   1
                      /   \
                     2     3
                   /  \   /  \
                  4    5 6    7
Sample Output: 18 //5+2+1+3+7
#Hint1: If you were to imagine each node in a Binary Tree as the root of the Binary Tree, temporarily eliminating all of the
nodes that come above it, how would you find the max path sum for each of these newly imagined Binary Trees? In simpler terms,
how can you find the max path sum for each subtree in the Binary Tree?

#Hint2: For every node in a Binary Tree, there are four options for the max path sum that includes its value: the node's value
alone, the node's value plus the max path sum of its left subtree, the node's value plus the max path sum of its right subtree,
or the node's value plus the max path sum of both its subtrees.

#Hint3: A recursive algorithm that computes each node's max path sum and uses it to compute its parents' nodes' max path sums
seems appropriate, but realize that you cannot have a path going through a node and both its subtrees as well as that node's
parent node. In other words, the fourth option mentioned in Hint #2 poses a challenge to implementing a recursive algorithm that
solves this problem. How can you get around it?

Optimal Space & Time Complexity: O(n) time | O(log(n)) space - where n is the number of nodes in the Binary Tree

*/
package BinaryTrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxPathSumInBinaryTree {
    public static int maxPathSum(BinaryTree tree) {
        List<Integer> maxSumArray = findMaxSum(tree);
        return maxSumArray.get(1);
    }
    public static List<Integer> findMaxSum(BinaryTree tree) {
        if (tree == null) {
            return new ArrayList<Integer>(Arrays.asList(0, Integer.MIN_VALUE));
        }
        List<Integer> leftMaxSumArray = findMaxSum(tree.left);
        Integer leftMaxSumAsBranch = leftMaxSumArray.get(0);
        Integer leftMaxPathSum = leftMaxSumArray.get(1);

        List<Integer> rightMaxSumArray = findMaxSum(tree.right);
        Integer rightMaxSumAsBranch = rightMaxSumArray.get(0);
        Integer rightMaxPathSum = rightMaxSumArray.get(1);

        Integer maxChildSumAsBranch = Math.max(leftMaxSumAsBranch, rightMaxSumAsBranch);
        Integer maxSumAsBranch = Math.max(maxChildSumAsBranch + tree.value, tree.value);
        Integer maxSumAsRootNode = Math.max(leftMaxSumAsBranch + tree.value + rightMaxSumAsBranch, maxSumAsBranch);
        int maxPathSum = Math.max(leftMaxPathSum, Math.max(rightMaxPathSum, maxSumAsRootNode));
        return new ArrayList<Integer>(Arrays.asList(maxSumAsBranch, maxPathSum));
    }
    static class BinaryTree{
        public int value;
        public BinaryTree left;
        public BinaryTree right;
        public BinaryTree(int value) {
            this.value = value;
        }
    }
}