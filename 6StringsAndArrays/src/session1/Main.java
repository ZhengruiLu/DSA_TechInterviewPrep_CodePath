package session1;

import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(runLengthEncoding("wwwwaaadexxxxxx"));
    }

    /*
    581. Shortest Unsorted Continuous Subarray
    https://leetcode.com/problems/shortest-unsorted-continuous-subarray/

    s1:
    M: sort
    P: sort arr, comparing sorted arr with ori arr, keep track of min start and max end idx
    R: O(nlogn)
    */
    public static int findUnsortedSubarraySort(int[] nums) {
        int n = nums.length;
        int left = n - 1, right = 0;

        if (n == 1) return 0;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        // int[] sorted = Arrays.copyOf(nums, n);

        for (int i = 0; i < n; i++) {
            if (sorted[i] != nums[i]) {
                left = Math.min(left, i);
                right = Math.max(right, i);
            }
        }

        return right - left < 0 ? 0: right - left + 1;
    }

    /*
    581. Shortest Unsorted Continuous Subarray
    https://leetcode.com/problems/shortest-unsorted-continuous-subarray/

    s2
    M: stack
    P: use stack save and find min start and max end idx
    R: O(n), O(n)
    */
    public static int findUnsortedSubarrayStack(int[] nums) {
        int n = nums.length;
        int left = n - 1, right = 0;

        if (n == 1) return 0;

        //use stack save and find min start and max end idx
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]){
                left = Math.min(left, stack.pop());
            }

            stack.push(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                right = Math.max(right, stack.pop());
            }

            stack.push(i);
        }

        return right - left < 0 ? 0: right - left + 1;
    }

    /*
    Run Length Encoding
    https://www.geeksforgeeks.org/run-length-encoding/?id=discuss

    M: linear
    P: loop str, record curr char and count its freq, save in ans string
        if diff char, update
    R:
     */
    public static String runLengthEncoding(String s) {
        int n = s.length(),count;
        char curr;
        StringBuilder sb = new StringBuilder();

        if (n <= 1) return s;

        for (int i = 0; i < n; i++) {
            count = 1;

            while (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count++;
                i++;
            }

            sb.append(s.charAt(i));
            sb.append(count);
        }

        return sb.toString();
    }

    /*
    66. Plus One
    https://leetcode.com/problems/plus-one/
    M: linear
    P: loop from end, check curr num is 9, mark curr num as 0; else curr num ++, return nums
        if all 9, add one more digit at start as 1
        O(N)
    */
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i]++;

                return digits;
            }
        }

        //if all 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
}
