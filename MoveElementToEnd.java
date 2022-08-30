/* You are given an array of integers and an integer. Write a function that moves all instances of that integer
in the array to the end of the array and returns the array. The function should perform this in place(i.e., it
should mutate the input array) and does not need to maintain the order of the other integers.
Sample Input:
array= [2, 1, 2, 2, 2, 3, 4, 2]
toMove = 2
Sample Output:
[1, 3, 4, 2, 2, 2, 2, 2] // the numbers 1, 3, and 4 could be ordered differently.
Hint1 You can solve this problem in linear time.
Hint2 In view of Hint#1, you can solve this problem without sorting the input array. Try setting two pointers at
the start and end of the array, respectively, and progressively moving them inwards.
Hint3 Following Hint#2, set two pointers at the start and end of the array, respectively. Move the right pointer
inwards so long as it points to the integer to move and move the left pointer inwards so long as it doesn't point
to the integer to move. When both pointers aren't moving, swap their values in place. Repeat this process until
the pointers pass each other.
Optimal Space & Time Complexity - O(n) time | O(1) space - where n is the length of the array. */
package Arrays;
import java.util.List;
public class MoveElementToEnd {
    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        int i = 0;
        int j = array.size() - 1;
        while(i < j) {
            while (i < j && array.get(j) == toMove) {
                j--;
            }
            if (array.get(i) == toMove) {
                swap(i, j, array);
            }
            i++;
        }
        return array;
    }
    public static void swap(int i, int j, List<Integer> array) {
        int temp = array.get(j);
        array.set(j, array.get(i));
        array.set(i, temp);
    }
}
