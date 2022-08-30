/* An array of integers is said to represent the Binary Search Tree (BST) obtained by inserting each integer in the
array. Write a function that takes in two arrays of integers and determines whether these arrays represent the same BST. Note
that you're not allowed to construct any BSTs in your code.
A BST is a Binary Tree that consists only of BST nodes. A node is said to be a valid BST node if and
only if it satisfies the BST property: its value is strictly greater than the values of every node to its left;
its value is less than or equal to the values of every node to its right; and its children nodes are either
valid BST nodes themselves or None/null.

Sample Input: arrayOne = [10, 15, 8, 12, 94, 81, 5, 2, 11]  arrayTwo = [10, 8, 5, 15, 2, 12, 11, 94, 81]

Sample Output: true //both arrays represent the BST below
           10
          /  \
         8    15
        /    /  \
       5   12    94
      /   /      /
     2   11    81

#Hint1: You can immediately conclude that the input arrays don't represent the same BST if their first values aren't equal to
each other, since their first values represent the root of the BST. Similarly, you can conclude this if their lengths are
different. If their first values are equal to each other and their lengths are the same, what should your next step be?

#Hint2: Given an array of integers, all of the values in the array that are smaller than the first value in the array are
located in the left subtree of the BST that the array represents, and all of the values in the array that are greater than or
equal to the first value in the array are located in the right subtree of the BST that the array represents. Use this fact and
Hint #1 to recursively determine whether all subtrees in the BSTs represented by the arrays are equal to each other.

#Hint3: Write a recursive function that takes in two arrays of integers. If the first values of the arrays aren't equal to each
other or if the arrays don't have the same length, the arrays don't represent the same BST. If the first values and lengths
are equal to each other, respectively, perform the following actions on both arrays: gather all integers that are smaller
than the first integer; these form a new array that represents the left subtree of the relevant BST; gather all integers that
are greater than or equal to the first integer; these form a new array that represents the right subtree of the relevant BST.
Call the recursive function twice: once with the two left-subtree arrays and once with the two right-subtree arrays.

#Hint4: Do you actually need to create all of the auxiliary arrays mentioned in Hint #3?

Optimal Space & Time Complexity: 0(n^2) time | O(d) space - where n is the number of nodes in each array, respectively,
and d is the depth of the BST that they represent.
 */
package BinarySearchTress;
import java.util.List;
public class SameBSTsSolutionII {
    //0(n^2) time | 0(d) space - where n is the number of nodes in each array, respectively, and d is the depth of the BST
    // that they represent
    public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
        return areSameBsts(arrayOne, arrayTwo, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean areSameBsts(List<Integer> arrayOne, List<Integer> arrayTwo,
                                      int rootIdxOne, int rootIdxTwo, int minVal, int maxVal) {
        if (rootIdxOne == -1 || rootIdxTwo == -1) return rootIdxOne == rootIdxTwo;
        if (arrayOne.get(rootIdxOne).intValue() != arrayTwo.get(rootIdxTwo).intValue()) return false;
        int leftRootIdxOne = getIdxOfFirstSmaller(arrayOne, rootIdxOne, minVal);
        int leftRootIdxTwo = getIdxOfFirstSmaller(arrayTwo, rootIdxTwo, minVal);
        int rightRootIdxOne = getIdxOfFirstBiggerOrEqual(arrayOne, rootIdxOne, maxVal);
        int rightRootIdxTwo = getIdxOfFirstBiggerOrEqual(arrayTwo, rootIdxTwo, maxVal);
        int currentvalue = arrayOne.get(rootIdxOne);
        boolean leftAreSame = areSameBsts(arrayOne, arrayTwo, leftRootIdxOne, leftRootIdxTwo, minVal, currentvalue);
        boolean rightAreSame = areSameBsts(arrayOne, arrayTwo, rightRootIdxOne, rightRootIdxTwo, currentvalue, maxVal);
        return leftAreSame && rightAreSame;
    }

    public static int getIdxOfFirstSmaller(List<Integer> array, int startingIdx, int minval) {
// Find the index of the first smaller value after the startingIdx. Make sure that this value is greater than or equal to
// the minval, which is the value of the previous parent node in the BST. If it isn't, then that value is located in the
// left subtree of the previous parent node.
        for (int i = startingIdx + 1; i < array.size(); i++) {
            if (array.get(i).intValue() < array.get(startingIdx).intValue()
                    && array.get(i).intValue() >= minval) return i;
        }
        return -1;
    }

    public static int getIdxOfFirstBiggerOrEqual(List<Integer> array, int startingIdx, int maxval) {
// Find the index of the first bigger/equal value after the startingIdx. Make sure that this value is smaller than maxval,
// which is the value of the previous parent node in the BST. If it isn't, then that value is located in the right
// subtree of the previous parent node.
        for (int i = startingIdx + 1; i < array.size(); i++) {
            if (array.get(i).intValue() >= array.get(startingIdx).intValue()
                    && array.get(i).intValue() < maxval) return i;
        }
        return -1;
    }
}

