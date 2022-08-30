/* Imagine that you're a teacher who's just graded the final exam in a class. You have a list of student scores on
the final exam in a particular order (not necessarily sorted), and you want to reward your students. You decide to
do so fairly by giving them arbitrary rewards following two rules: 1. All students must receive at least one reward.
2. Any given student must receive strictly more rewards than an adjacent student(a student immediately to the left or
to the right) with a lower score and must receive strictly fewer rewards than an adjacent student with a higher score
Write a function that takes in a list of scores and returns the minimum number of rewards that you must give out to
students to satisfy the two rules. You can assume that all students have different scores; in other words the scores
are all unique.
Sample Input:
scores = [8, 4, 2, 1, 3, 6, 7, 9, 5]
Sample Output:
25 // you would give out the following rewards: [4, 3, 2, 1, 2, 3, 4, 5, 1]
Hint1 You could try iterating through the input list of scores and incrementing the number of rewards you give to
each student if they have a greater score than the previous student's score. However, if you reach a student with a
smaller score than the previous student's score, you'll have to backtrack through the array to fix previous reward
assignments. During this backtrack, is it correct to simply increment the reward of a student whose score is
greater than the next student's score?
Hint2 Notice that there are local mins and local maxes in the input list of scores: scores that are smaller than
both scores next to them and scores that are greater than both scores next to them. Find the local mins, and try
expanding away from them until you reach local maxes, assigning (and incrementing) rewards as you go.
HInt3 Do you actually need to find the local mins mentioned in Hint #2? Can you simply do two sweeps of the input
list of scores, one from left to right, and one from right to left?
Optimal space & time complexity: O(n) time | O(n) space - where n is the length of the input array */
package Arrays;
import java.util.*;
import java.util.stream.IntStream;

public class MinRewardsSolutionI {
    //O(n^2) time | O(n) space - where n is the length of the input array
    public static int minRewards(int[] scores) {
        int[] rewards = new int[scores.length];
        Arrays.fill(rewards, 1);
        for (int i = 1; i < scores.length; i++) {
            int j = i - 1;
            if (scores[i] > scores[j]) {
                rewards[i] = rewards[j] + 1;
            } else {
                while (j >= 0 & scores[j] > scores[j + 1]) {
                    rewards[j] = Math.max(rewards[j], rewards[j + 1] + 1);
                    j--;
                }
            }
        }
        return IntStream.of(rewards).sum();
    }
}