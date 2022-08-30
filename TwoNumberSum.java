/* write a function that takes in a non-empty array of distinct integers and an integer representing
target sum. If any two numbers in the input array sum up to the target sum, the function should return them
in an array, in any order. If no two numbers sum up to the target sum, the function should return an empty
array. Note that the target sum has to be obtained by summing two different integers in the array; you can't
add a single integer to itself in order to obtain the target sum. You can assume that there will be at most
one pair of numbers summing up to the target sum.

Sample Input
array = [3, 5, -4, 8, 11, 1, -1, 6]
targetSum = 10

Sample Output
[-1, 11] the numbers could be in reverse order

Hint1 Try using two for loops to sum all possible pairs of numbers in the input array. What are the time and
space implications of this approach?

Hint2 Realize that for every number X in the input array, you are essentially trying to find a corresponding
number Y such that X + Y = targetSum. With two variables in this equation known to you, it shouldn't be hard
to solve for Y.

Hint3 Try storing every number in a hash table, solving the equation mentioned in Hint#2 for every number,
and checking if the Y that you find is stored in the hash table. What are the time and space implications
of this approach?

Optimal Space & Time Complexity - O(n) time | O(n) space - where n is the length of the input array
 */
package Arrays;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class TwoNumberSum {
    //O(n^2) time | O(1) space
    public static int[] twoNumberSum(int[] array, int targetSum) {
        for (int i = 0; i < array.length - 1; i++) {
            int firstNum = array[i];
            for (int j = i+1; j < array.length; j++) {
                int secondNum = array[j];
                if (firstNum + secondNum == targetSum) {
                    return new int[] {firstNum, secondNum};
                }
            }
        }
        return new int[0];
    }
    //O(n) time | O(n) space
    public static int[] twoNumSum(int[] array, int targetSum) {
        Set<Integer> nums = new HashSet<>();
        for (int num : array) {
            int potentialMatch = targetSum - num;
            if (nums.contains(potentialMatch)) {
                return new int[] {potentialMatch, num};
            }
            else {
                nums.add(num);
            }
        }
        return new int[0];
    }
    //O(nlog(n)) time | O(1) space
    public static int[] tNumSum(int[] array, int targetSum) {
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        while(left < right) {
            int currentSum = array[left] + array[right];
            if (currentSum == targetSum) {
                return new int[] {array[left], array[right]};
            }
            else if(currentSum < targetSum){
                left++;
            }
            else if(currentSum > targetSum) {
                right--;
            }
        }
        return new int[0];
    }
    public static void main(String[] args) {
        int[] array = {3, 5, -4, 8, 11, 1, -1, 6};
        int targetSum = 10;
        System.out.println(Arrays.toString(twoNumberSum(array, targetSum)));
        System.out.println(Arrays.toString(twoNumSum(array, targetSum)));
        System.out.println(Arrays.toString(tNumSum(array, targetSum)));
    }
}