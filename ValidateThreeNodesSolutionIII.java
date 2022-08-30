/* You're given three nodes that are contained in the same Binary Search Tree: nodeOne , nodeTwo ,and nodeThree.
Write a function that returns a boolean representing whether one of nodeOne or nodeThree is an ancestor of nodeTwo and the
other node is a descendant of nodeTwo . For example, if your function determines that nodeOne is an ancestor of nodeTwo , then
it needs to see if nodeThree is a descendant of nodeTwo. If your function determines that nodeThree is an ancestor, then it
needs to see if nodeOne is a descendant. A descendant of a node N is defined as a node contained in the tree rooted at N.
A node N is an ancestor of another node M if M is a descendant of N. It isn't guaranteed that nodeOne or nodeThree will be
ancestors or descendants of nodeTwo , but it is guaranteed that all three nodes will be unique and will never be None/null.
In other words, you'll be given valid input nodes. Each BST node has an integer value, a left child node,and a right child node.
A node is said to be a valid BST node if and only if it satisfies the BST property: its value is strictly greater than the
values of every node to its left; its value is less than or equal to the values of every node to its right; and its children
nodes are either valid BST nodes themselves or None/null.
Sample Input: tree =    5
                      /   \
                     2     7
                  /  \    /  \
                 1    4  6    8
                /    /
               0    3
// This tree won't actually be passed as an input; it is here to help you visualise the problem.
nodeOne = 5 // The actual node with value 5.
nodeTwo = 2 // The actual node with value 2.
nodeThree = 3 // The actual node with value 3.
Sample Output: true // nodeOne is an ancestor of nodeTwo, and nodeThree is a descendant of nodeTwo.

#Hint1: Keep in mind that the nodes passed to you are contained in a Binary Search Tree—not just a normal Binary Tree. How
might this help you traverse the tree faster?

#Hint2: There are multiple ways to solve this problem, but the simplest is to just check the possible relationships between
the nodes. Since you're looking for a descendant and an ancestor, simply check if nodeOne is a descendant of nodeTwo , and if
it is, then check if nodeThree is an ancestor of nodeTwo . If the previous checks come out negative, check if nodeThree is a
descendant of nodeTwo, and if it is, then check if nodeOne is an ancestor of nodeTwo .

#Hint3: Although the approach mentioned in Hint #2 is fairly efficient (it runs in 0(h) time, where h is the height of the tree),
there's a way to solve this problem faster. It involves realizing that, when searching for nodeTwo from either nodeOne or
nodeThree, if you ever reach nodeThree from nodeOne or nodeOne from nodeThree before reaching nodeTwo, then you can immediately
stop the algorithm, because nodeTwo cannot be between these nodes.

Optimal Space & Time Complexity: 0(d) time | O(1) space - where d is the distance between nodeOne and nodeThree.
*/
package BinarySearchTress;
public class ValidateThreeNodesSolutionIII {
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;
        public BST(int value) {
            this.value = value;
        }
    }
    //O(d) time | O(1) space - where d is the distance between nodeOne and nodeThree
    public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
        BST searchOne = nodeOne;
        BST searchTwo = nodeThree;
        while (true) {
            boolean foundThreeFromOne = searchOne == nodeThree;
            boolean foundOneFromThree = searchTwo == nodeOne;
            boolean foundNodeTwo = (searchOne == nodeTwo) || (searchTwo == nodeTwo);
            boolean finishedSearching = (searchOne == null) && (searchTwo == null);
            if (foundThreeFromOne || foundOneFromThree || foundNodeTwo || finishedSearching) {
                break;
            }
            if (searchOne != null) {
                searchOne = (searchOne.value > nodeTwo.value) ? searchOne.left : searchOne.right;
            }
            if (searchTwo != null) {
                searchTwo = (searchTwo.value > nodeTwo.value) ? searchTwo.left : searchTwo.right;
            }
        }
        boolean foundNodeFromOther = (searchOne == nodeThree) || (searchTwo == nodeOne);
        boolean foundNodeTwo = (searchOne == nodeTwo) || (searchTwo == nodeTwo);
        if (!foundNodeTwo || foundNodeFromOther) {
            return false;
        }
        return searchForTarget(nodeTwo, (searchOne==nodeTwo)?nodeThree:nodeOne);
    }
    public boolean searchForTarget(BST node, BST target) {
        while (node != null && node != target) {
            node = (target.value < node.value) ? node.left : node.right;
        }
        return node ==target;
    }
}