/* The Fibonacci sequence is defined as follows: the first number of the sequence is 0, the
second number is 1, and the nth number is the sum of the (n - 1)th and (n - 2)th numbers.
Write a function that takes in an integer n and returns the nth Fibonacci number.
Important note: the Fibonacci sequence is often defined with its first two numbers as F0 = 0
and F1 = 1. For the purpose of this question, the first Fibonacci number is F0; therefore,
getNthFib(1) is equal to F0, getNthFib(2) is equal to F1, etc

Sample Input
n = 2
n = 6

Sample Output
1 // 0, 1
5 // 0, 1, 1, 2, 3, 5

Hint1 The formula to generate the nth Fibonacci number can be written as follows: F(n) = F(n -
1) + F(n - 2). Think of the case(s) for which this formula doesn't apply (the base case(s))
and try to implement a simple recursive algorithm to find the nth Fibonacci number with
this formula.

Hint2 What are the runtime implications of solving this problem as is described in Hint #1? Can
you use memoization (caching) to improve the performance of your algorithm?

Hint3 Realize that to calculate any single Fibonacci number you only need to have the two
previous Fibonacci numbers. Knowing this, can you implement an iterative algorithm to
solve this question, storing only the last two Fibonacci numbers at any given time?

Optimal Space & Time Complexity - O(n) time | O(1) space - where n is the input number. */
package Recursion;
import java.util.HashMap;
import java.util.Map;
public class NthFibonacciSolution2 {
    // 0(n) time | O(n) space
    public static int getNthFib(int n) {
        Map<Integer, Integer> memoize = new HashMap<Integer, Integer>();
        memoize.put(1,0);
        memoize.put(2,1);
        return getNthFib(n, memoize);
    }
    public static int getNthFib(int n, Map<Integer, Integer> memoize) {
        if (memoize.containsKey(n)) {
            return memoize.get(n);
        }
        else {
            memoize.put(n, getNthFib(n-1, memoize) + getNthFib(n-2, memoize));
            return memoize.get(n);
        }
    }
}