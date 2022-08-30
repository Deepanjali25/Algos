/* Write a function that takes in an array of integers and returns an array of the same length, where each element
in the output array corresponds to the number of integers in the input array that are to the right of the relevant index and
that are strictly smaller than the integer at that index. In other words, the value at output[i] represents the number of
integers that are to the right of i and that are strictly smaller than input[i].
Sample Input: array = [8, 5, 11, -1, 3, 4, 2]
Sample Output: [5, 4, 4, 0, 1, 1, 0]
// There are 5 integers smaller than 8 to the right of it. There are 4 integers smaller than 5 to the right of it.
// There are 4 integers smaller than 11 to the right of it. Etc..
#Hint1: The naive solution to this problem involves a simple pair of nested for loops and runs in O(n^2) time, where n is the
length of the input array. This problem doesn't seem like it can be solved in linear time, so what time complexity could we
realistically achieve if we somehow optimized our algorithm?
#Hint2: The only better time complexity than the O(n^2) one of our naive solution that we could realistically achieve would be
an O(nlog(n)) complexity. What data structure has log(n) operations, and how could it help for this problem?
#Hint3: A Binary Search Tree supports log(n) insertions and has the relevant property of every left-subtree-node having a
smaller value than a given node’s value; can we construct a BST from the input array in such a way that it leads us to the
result array that we're looking for?
#Hint4: Construct a BST by inserting the input array's integers one by one, in reverse order (from right to left). At each
insertion, once a new BST node is positioned in the BST, the number of nodes in its parent node's left subtree (plus the parent
node itself, if its value is smaller than the inserted node's value) is the number of "right-smaller-than" elements for the
element being inserted.
#Hint5: Going off of #Hint4, you can construct a special type of BST that stores the size of every node's left subtree. This
value can then be used to obtain the right-smaller-than numbers for every element in the array.
Optimal Space & Time Complexity: Average case: when the created BST is balanced O(nlog(n)) time | O(n) space - where n is the
length of the array -- Worst case: when the created BST is like a linked list O(n^2) time | O(n) space
*/
package BinarySearchTress;

import java.util.ArrayList;
import java.util.List;

public class RightSmallerThanSolutionII {
    //Average case: when the created BST is balanced O(nlog(n)) time | O(n) space - where n is the length of the array --
    //Worst case: when the created BST is like a linked list O(n^2) time | O(n) space
    public static List<Integer> rightSmallerThan(List<Integer> array) {
        if (array.size() == 0) return new ArrayList<Integer>();
        int lastIdx = array.size() - 1;
        SpecialBST bst = new SpecialBST(array.get(lastIdx), lastIdx, 0);
        for (int i = array.size() - 2; i >= 0; i--) {
            bst.insert(array.get(i), i);
        }
        List<Integer> rightSmallerCounts = new ArrayList<Integer>(array);
        getRightSmallerCounts(bst, rightSmallerCounts);
        return rightSmallerCounts;
    }
    public static void getRightSmallerCounts(SpecialBST bst, List<Integer> rightSmallerCounts) {
        if (bst == null) return;
        rightSmallerCounts.set(bst.idx, bst.numSmallerAtInsertTime);
        getRightSmallerCounts(bst.left, rightSmallerCounts);
        getRightSmallerCounts(bst.right, rightSmallerCounts);
    }
    static class SpecialBST {
        public int value;
        public int idx;
        public int numSmallerAtInsertTime;
        public int leftSubtreeSize;
        public SpecialBST left;
        public SpecialBST right;
        public SpecialBST(int value, int idx, int numSmallerAtInsertTime) {
            this.value = value;
            this.idx = idx;
            this.numSmallerAtInsertTime = numSmallerAtInsertTime;
            leftSubtreeSize = 0;
            left = null;
            right = null;
        }
        public void insert(int value, int idx) {
            insertHelper(value, idx, 0);
        }
        public void insertHelper(int value, int idx, int numSmallerAtInsertTime) {
            if (value < this.value) {
                leftSubtreeSize++;
                if (left == null) {
                    left = new SpecialBST(value, idx, numSmallerAtInsertTime);
                } else {
                    left.insertHelper(value, idx, numSmallerAtInsertTime);
                }
            } else {
                numSmallerAtInsertTime += leftSubtreeSize;
                if (value > this.value) numSmallerAtInsertTime++;
                if (right == null) {
                    right = new SpecialBST(value, idx, numSmallerAtInsertTime);
                } else {
                    right.insertHelper(value, idx, numSmallerAtInsertTime);
                }
            }
        }
    }
}
