/* Write a function that takes in a "special" array and returns its product sum. A "special" array is a
non-empty array that contains either integers or other "special" arrays. The product sum of a
"special” array is the sum of its elements, where "special” arrays inside it are summed themselves and then
multiplied by their level of depth.
The depth of a "special" array is how far nested it is. For instance, the depth of [] is 1; the depth of the inner
array in [[]] is 2; the depth of the innermost array in [[[]]] is 3
Therefore, the product sum of [x, y] is x + y; the product sum of [x, [y, z] is x + 2 * (y + z); the productsum
of [x, [y, [z]]] is x + 2 * (y + 3z).

Sample Input
array = [5, 2, [7, -1], 3, [6, [-13, 8], 4]]

Sample Output
12 // calculates as: 5 + 2 + 2 * (7-1) + 3 + 2 * (6 + 3 * (-13 + 8) + 4

Hint1 Try using recursion to solve this problem.

Hint2 Initialize the product sum of the "special” array to 0. Then, iterate through all of the array's elements;
if you come across a number, add it to the product sum; if you come across another "special” array, recursively
call the productSum function on it and add the returned value to the product sum. How will you handle
multiplying the product sums at a given level of depth?

Hint3 Have the productSum function take in a second parameter: the multiplier, initialized to 1. Whenever you
recursively call the productSum function, pass in the multiplier incremented by 1.

Optimal Space & Time Complexity - O(n) time | O(d) space - where n is the total number of elements in the array,
including sub-elements, and d is the greatest depth of "special" arrays in the array. */
package Recursion;
import java.util.*;
public class ProductSum {
    // 0(n) time | 0(d) space - where n is the total number of elements in the array,
    // including sub-elements, and d is the greatest depth of "special" arrays in the array
    public static int productSum(List<Object> array) {
        return productSumHelper(array, 1);
    }
    public static int productSumHelper(List<Object> array, int multiplier) {
        int sum = 0;
        for (Object el : array) {
            if (el instanceof ArrayList) {
                @SuppressWarnings("unchecked")
                ArrayList<Object> ls = (ArrayList<Object>) el;
                sum += productSumHelper(ls, multiplier + 1);
            } else {
                sum += (int) el;
            }
        }
        return sum * multiplier;
    }
}