/* Write a function that takes in an n x m two-dimensional array(that can be square-shaped when n==m) ad returns a
one-dimensional array of all the array's elements in spiral order. Spiral order starts at the top left corner of
the two-dimensional array, goes to the right, and proceeds in a spiral pattern all the way until every element
has been visited.
Sample Input:
array= [[1, 2, 3, 4], [12, 13, 14, 5], [11, 16, 15, 6], [10, 9, 8, 7],]
Sample Output:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
Hint1 You can think of the spiral that you have to traverse as a set of rectangle perimeters that progressively get
smaller (i.e., that progressively move inwards in the two-dimensional array).
Hint2 Going off of Hint #1, declare four variables: a starting row, a starting column, an ending
row, and an ending column. These four variables represent the bounds of the first
rectangle perimeter in the spiral that you have to traverse. Traverse that perimeter using
those bounds, and then move the bounds inwards. End your algorithm once the starting
row passes the ending row or the starting column passes the ending column.
Hint3 You can solve this problem both iteratively and recursively following very similar logic.
Optimal Space & Time Complexity - O(n) time | O(n) space - where n is the total number of elements in the array. */
package Arrays;
import java.util.ArrayList;
import java.util.List;
public class SpiralTraverseSolutionI {
    public static List<Integer> spiralTraverse(int[][] array) {
        if (array.length==0) {
            return new ArrayList<Integer>();
        }
        var result = new ArrayList<Integer>();
        var startRow = 0;
        var endRow = array.length - 1;
        var startCol = 0;
        var endCol = array[0].length - 1;
        while (startRow <= endRow && startCol <=endCol) {
            for (int col = startCol; col<=endCol; col++) {
                result.add(array[startRow][col]);
            }
            for (int row = startRow + 1; row <= endRow; row++) {
                result.add(array[row][endCol]);
            }
            for (int col = endCol - 1; col >= startCol; col--) {
                //Handle the edge case when there is a single row in the middle of the matrix. In this case, we
                //don't want to double count the values in this row, which we have already counted in the first
                //for loop above. See test case 8 for an example of this edge case.
                if (startRow == endRow){
                    break;
                }
                result.add(array[endRow][col]);
            }
            for (int row = endRow - 1; row > startRow; row--) {
                //Handle the edge case when there is a single col in the middle of the matrix. In this case, we
                //don't want to double-count the values of this column, which we have already counted in the
                //second for loop above. See test case 9 for an example of this edge case.
                if (startCol == endCol) {
                    break;
                }
                result.add(array[row][startCol]);
            }
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        return result;
    }
}
