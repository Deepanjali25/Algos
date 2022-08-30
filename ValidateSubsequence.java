/* Given two non-empty arrays of integers, write a function that determines whether the second array
is a subsequence of the first one. A subsequence of an array is a set of numbers that aren't necessarily
adjacent in the array but that are in the same order as they appear in the array. For instance, the numbers
[1, 3, 4] form a subsequence of the array [1, 2, 3, 4], and so do the numbers [2, 4]. Note that a single
number in an array and the array itself are both valid subsequences of the array.

Sample Input
array = [5, 1, 22, 25, 6, -1, 8, 10]
sequence = [1, 6, -1, 10]

Sample Output
true

Hint1 You can solve this question by iterating through the main input array once.

Hint2 Iterate through the main array, and look for the first integer in the potential subsequence. If you
find that integer, keep on iterating through the main array, but now look for the second integer in the
potential subsequence. Continue this process until you either find every integer in the potential
subsequence or you reach the end of the main array.

Hint3 To actually implement what Hint#2 describes, you will have to declare a variable holding your position
in the potential subsequence. At first, this position will be the 0th index in the sequence; as you find the
sequence's integers in the main array, you will increment the position variable until you reach the end of
the sequence.

Optimal Space & Time Complexity - O(n) time | O(1) space - where n is the length of the array
 */
package Arrays;
import java.util.*;
public class ValidateSubsequence {
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        //O(n) time | O(1) space - where n is the length of the array
        int arrIdx = 0;
        int seqIdx = 0;
        while (arrIdx < array.size() && seqIdx < sequence.size()) {
            if (array.get(arrIdx).equals(sequence.get(seqIdx))) {
                seqIdx++;
            }
            arrIdx++;
        }
        return seqIdx == sequence.size();
    }
    public static boolean isValidSubseq(List<Integer> array, List<Integer> sequence) {
        //O(n) time | O(1) space - where n is the length of the array
        int seqIdx = 0;
        for (var value: array) {
            if (seqIdx == sequence.size()) {
                break;
            }
            if (sequence.get(seqIdx).equals(value)) {
                seqIdx++;
            }
        }
        return seqIdx == sequence.size();
    }
    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        array.add(5); array.add(1); array.add(22); array.add(25);
        array.add(6); array.add(-1); array.add(8); array.add(10);
        List<Integer> sequence = new ArrayList<>();
        array.add(1); array.add(6); array.add(-1); array.add(10);
        System.out.println(isValidSubsequence(array, sequence));
        System.out.println(isValidSubseq(array, sequence));
    }
}
