package ta.practice;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        String s = "A man, a plan, a canal: Panama";
//        System.out.println(isPalindrome(s));

//        System.out.println(myPow(2.00000, -2147483648));
//        System.out.println(Math.pow(2, 10));//1024.00000
//        System.out.println(myPow(2.00000, 10));

        int[] nums = {1,2,3};
        printList(permute(nums));

    }

    /*
    understand
        i/o int(range 0-30)/int

    match
        recursion

    plan
        1.base case
        if n <= 1, return n;
        2. recursive call
        recursive(n - 1) + recursive(n - 2)

        time O(2 ^ n), space O(n)
        two to the power of n

    */
    public int fib(int n) {
        if (n <= 1) return n;

        return fib(n - 1) + fib(n - 2);
    }

    /*
    understand
        i/o str,upper, lower, non-a/bool palin
        constriants empty? >= 1
        edge len is 1, return true
    match
        dfs, two pointers
    plan
        1. check if the characters are non-a, convert it as lower, save into temp
        2. rec, base case: if left > right, return true; if char left not equal char at right, return false, then call rec (temp, left + 1, right - 1)
        time: O(n), O(n)
    implement
    review
    evaluate
    */
    public static boolean isPalindrome(String s) {
        //edge
        int n = s.length();
        if (n == 1) return true;

        //convert the s into lower str
        String temp = convertToLower(s);

        //call rec with two pointers
        return rec(temp, 0, temp.length() - 1);
    }

    private static boolean rec(String temp, int left, int right){
        //base case
        if (left >= right){
            return true;
        }

        if (temp.charAt(left) != temp.charAt(right)){
            return false;
        }
        left++;
        right--;

        return rec(temp, left, right);
    }

    private static String convertToLower(String s){
        String ans = "";

        for (int i = 0; i < s.length(); i++){
            char temp = s.charAt(i);
            if (Character.isLetterOrDigit(temp)){
                ans += Character.toLowerCase(temp);
            }
        }

        return ans;
    }

    /*
understand:
    i/o /return x to the pow of n
    constraints x: -100 - 100, n integer range
    edge
        long n
        two situation
        n < 0 x = 1/x, n = -n
        n = 0 ret 1
        n > 0


match
    recursion
plan
    1. two situations
    A. n < 0, x = 1/x, n = -n
    B, n >= 0

    2. recursive
    base case
        if n == 0, return 1
    define
        half = rec(x, n // 2)
    ret
        if n even, ret half * half
        else ret h * h * x

    time: O(logn), space: O(logn)

*/
    public static double myPow(double x, int n) {
        long N = n;

        if (N < 0){
            x = 1 / x;
            N = -N;
        }

        return rec(x, N);
    }

    private static double rec(double x, long n){

        if (n == 0){
            return 1;
        }

        if (n == 1){
            return x;
        }

        if (n == -1){
            return 1/x;
        }

        double half = rec (x, n / 2);

        if (n % 2 == 0){
            return half * half;
        }else{
            return half * half * x;
        }
    }

    /*
    understand
        i/o arr/list of permu
        constraints arr range 1 - 6, unique
        edge len 1, add into list, return
    m
        recursion
    p
        1. copy ori arr into temp list, rec to swap
        2. rec
            base case
                if swapIdx == nums.len, add temp list into ans list
            define
                for nums,
                    swap(nums, i, swapIdx)
                    rec(temp, ans, swapIdx + 1, nums)
                    swap(nums, i, swapIdx)
    time: k-permutation of n space, space same
    */
    public static List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new LinkedList<>();
        if (n == 1){
            ans.add(Arrays.asList(nums[0]));
            return ans;
        }

        LinkedList<Integer> temp = new LinkedList<Integer>();
        //copy ori nums into temp list, for swap rec
        for (int i = 0; i < n; i++){
            temp.add(nums[i]);
        }

        rec(nums, 0, temp, ans);

        return ans;
    }

    private static void rec(int[] nums, int startIdx, LinkedList<Integer> temp, List<List<Integer>> ans){
        //base
        int n = nums.length;
        if (startIdx == n){
            ans.add(new LinkedList<>(temp));
            return;
        }

        for (int i = startIdx; i < n; i++){
            int curr = nums[i];
            Collections.swap(temp, startIdx, i);
            rec(nums, startIdx + 1, temp, ans);
            Collections.swap(temp, startIdx, i);
        }
    }

    private static void printList(List<List<Integer>> list){
        for (List<Integer> tmp: list) {
            System.out.println(Arrays.toString(tmp.toArray()));
        }
    }

    /*
    abca
    i  j
    i + 1, j
    i, j - 1

    understand
        i/o str/bool palin
        constraints
            len >= 1
            lowercase
        edge
            len 1 or 2, return true
    match
        recursion, two pointers
    plan
        1. check if this str is palin, if yes, return true.
        2. check if i + 1, j, or if i to j - 1
        O(n), O(1)

    */
    public boolean validPalindrome(String s) {
        //edge
        int n = s.length();
        int i = 0, j = n - 1;

        if (n <= 2) return true;

        while (i <= j){
            if (s.charAt(i) != s.charAt(j)){
                return isPalin(s, i + 1, j) || isPalin(s, i, j - 1);
            }
            i++;
            j--;
        }

        return true;
    }

    private boolean isPalin(String s, int i, int j){
        while (i <= j){
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }

        return true;
    }
}
