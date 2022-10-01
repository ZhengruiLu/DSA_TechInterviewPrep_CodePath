package com.practice;

public class Main {
    public static void main(String[] args) {
//        String s = "laboratory", t = "rat";
//        System.out.println(isSubStr(s, t));
//
//        System.out.println(nextPrime(3));

        System.out.println(evenFib(3));
    }

    /*
    substring
    Input:
    laboratory,
    i

    rat
    j
    Output: true

    Input: cat, meow
    Output: false

    substr
    understand:
        I/O: str1, str2/boolean
        constraints: s1 < s2, return f; if s1 equals to s2, return true

    match:
        sliding windows
    plan:
        1.loop the s1, if ith char not equals to jth char, i plus.
        else count++. if count == len of s1, return true

        O(n + m), O(1)
     */

    private static boolean isSubStr(String s, String t){
        //edge
        int n = s.length(), m = t.length();
        int i = 0, j = 0, cnt = 0;
        if (n < m) return false;
        if (s.equals(t)) return true;

        while (i < n && j < m) {
            char curr = s.charAt(i);
            if (curr != t.charAt(j)){
                i++;
            }else {
                j++;
                cnt++;
                if (cnt == m) return true;
            }
        }

        return false;
    }

    /*
    understand:
        I/O: int(< 0 || >= 0)/int
        how to make sure it is prime?
        if num is less than or equal to 1, return 2
        plus num, and check if it is prime
    match:
        linear
    plan:
        1.edge: if num is less than or equal to 1, return 2
        2. use while loop check, plus num, and check if it is prime
            edge: if num < 2, return false;
            if this num can be mod by 2 to n * n, return false, else return true
     */
    private static int nextPrime(int num){
        if (num <= 1) return 2;

        num++;
        while (true){
            if (isPrime(num)){
                return num;
            }
            num++;
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2){
            return false;
        }

        if (num < 4) return true;

        for (int i = 2; i <= Math.sqrt(num); i++){
            if (num % i == 0) return false;
        }

        return true;
    }

    /*
    additional
    Multiples of 3 or 5
    plan:
        1.base case: if n == 0, return sum
        2.dfs if (n % 3 || n % 5) sum += n
            return dfs(n - 1)
     */
    private static int sumOfMultiple3And5Iterative(int num){//num is upper boundry
        if (num <= 0) return 0;
        int sum = 0;

        for (int i = num - 1; i > 0; i--){
            if (i % 3 == 0 || i % 5 == 0){
                sum += i;
            }
        }

        return sum;
    }

    /*
    Even fibonacci
    Link: https://www.geeksforgeeks.org/nth-even-fibonacci-number/#:~:text=The%20even%20number%20Fibonacci%20sequence,numbers%20follow%20following%20recursive%20formula.
    time: O(2 ^ n), space: O(n)
     */
    private static long evenFib(int n){
        if (n < 1) return n;
        if (n == 1) return 2;

        return 4 * evenFib(n - 1) + evenFib(n - 2);
    }

    private static long evenFibdp(int n){
        if (n < 1) return n;
        if (n == 1) return 2;

        long a = 2, b = 8, sum = 0;

        for (int i = 2; i < n; i++){
            sum = a + b;
            a = b;
            b = sum;
        }

        return sum;
    }

}

