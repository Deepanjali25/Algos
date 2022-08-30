/* Write a function that takes in a sorted array of integers as well as a target integer. The function should use
the Binary Search algorithm to determine if the target integer is contained in the array and should return its index
if it is, otherwise -1.
Sample Input:
array = [0, 1, 21, 33, 45, 45, 61, 71, 72, 73]
target =33
Sample Output:
3
Hint1 The Binary Search algorithm works by finding the number in the middle of the input array and comparing it to
the target number. Given that the array is sorted, if this middle number is smaller than the target number, then
the entire left part of the array is no longer worth exploring since the target number can no longer be in it;
similarly, if the middle number is greater than the target number, then the entire right part of the array is no
longer worth exploring. Applying this logic recursively eliminates half of the array until the number is found or
until the array runs out of numbers.
Hint2 Write a helper function that takes in two additional arguments: a left pointer and a right pointer
representing the indices at the extremities of the array (or sub-array) that you are applying Binary Search on.
The first time this helper function is called, the left pointer should be zero and the right pointer should be the
final index of the input array. To find the index of the middle number mentioned in Hint #1, simply round down the
number obtained from: (left+right)/2. Apply this logic recursively until you find the target number or until
the left pointer becomes greater than the right pointer.
Hint3 Can you implement this algorithm iteratively? Are there any advantages to doing so?
Optimal Space & Time Complexity - O(log(n)) time | O(log(n)) space - where n is the length of the input array. */
package Searching;
public class BinarySearchSolutionI {
    public static int binarySearch(int[] array, int target) {
        return binarySearch(array, target, 0, array.length - 1);
    }
    public static int binarySearch(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        int potentialMatch = array[middle];
        if (target == potentialMatch) {
            return middle;
        }
        else if(target < potentialMatch) {
            return binarySearch(array, target, left, middle - 1);
        }
        else {
            return binarySearch(array, target, middle + 1, right);
        }
    }
}
