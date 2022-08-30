/* Write a function that takes in a Binary Tree and returns its diameter. The diameter of a binary tree is defined as
the length of its longest path, even if that path doesn't pass through the root of the tree. A path is a collection of
connected nodes in a tree, where no node is connected to more than two other nodes. The length of a path is the number of
edges between the path's first node and its last node. Each BinaryTree node has an integer value, a left child node, and a
right child node. Children nodes can either be BinaryTree nodes themselves or None/null.
Sample Input:  tree = 1
                    /   \
                   3     2
                 /   \
                7     4
               /       \
              8         5
             /           \
            9             6
Sample Output: 6 // 9 -> 8 -> 7 -> 3 -> 4 -> 5 -> 6
// There are 6 edges between the first node and the last node of this tree's longest path.
#Hint1: How can you use the height of a binary tree and the heights of its subtrees to calculate its diameter?
#Hint2: The length of the longest path that goes through the root of a binary tree is the sum of the heights of its left and
right subtrees (left subtree height + right subtree height). The diameter of a binary tree can be calculated by taking the
maximum of: 1) the maximum subtree diameter (max(left subtree diameter, right subtree diameter)); and 2) the length of the
longest path that goes through the root (left subtree height + right subtree height).
#Hint3: Implement a variation of depth-first search that recursively keeps track of both the diameter and the height of a
each subtree in the input binary tree. Follow Hint #2 to continuously compute these diameters.

Optimal Space & Time Complexity:  Average case: when the tree is balanced O(n) time | O(h) space - where n is the number of
nodes in the Binary Tree and h is the height of the Binary Tree

 */
package BinaryTrees;
public class BinaryTreeDiameter {
    public int binaryTreeDiameter(BinaryTree tree) {
        return getTreeInfo(tree).diameter;
    }
    public TreeInfo getTreeInfo(BinaryTree tree) {
        if (tree==null) {
            return new TreeInfo(0, 0);
        }
        TreeInfo leftTreeInfo = getTreeInfo(tree.left);
        TreeInfo rightTreeInfo = getTreeInfo(tree.right);
        int longestPathThroughRoot = leftTreeInfo.height + rightTreeInfo.height;
        int maxDiameterSoFar = Math.max(leftTreeInfo.diameter, rightTreeInfo.diameter);
        int currentDiameter = Math.max(longestPathThroughRoot, maxDiameterSoFar);
        int currentHeight = 1 + Math.max(leftTreeInfo.height, rightTreeInfo.height);
        return new TreeInfo(currentDiameter, currentHeight);
    }
    static class TreeInfo{
        public int diameter;
        public int height;
        public TreeInfo(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
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
