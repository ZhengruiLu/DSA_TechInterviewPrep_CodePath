package hashtable;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        int[] nums = {1,1,1,2,2,3};
//        int k = 2;
//        System.out.println(Arrays.toString(topKFrequent(nums, k)));

//        int[] arr = {1, 9, 3, 10, 4, 20 , 2};
//        System.out.println(longestConsecutiveSubsequence(arr));

//        String s = "badc", t = "baba";
//        System.out.println(isIsomorphic(s, t));

        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }
    /*
    Session 2
    13. Roman to Integer https://leetcode.com/problems/roman-to-integer/
    understand:
        I/O: Str/return int
        constraints:
            len of str >= 1?
            range of the str's numeral range
            str only contains these chars?
    match:
        hashing
    plan:
        1.hashmap: k-char, v-int num
        2.keep n-1th as after, set total equals to after, then loop the str arr from n - 2th to 0,
            comparing curr vs after, if curr >= after, total plus; else <, total minus curr;
            update the after with curr
        O(n), O(1)
     */
    public static int romanToInt(String s) {
        //map store roman and corresponding num
        HashMap<Character, Integer> values = new HashMap<>();
        values.put('M', 1000);
        values.put('D', 500);
        values.put('C', 100);
        values.put('L', 50);
        values.put('X', 10);
        values.put('V', 5);
        values.put('I', 1);

        int n = s.length();
        //edge
        if (n == 1) return values.get(s.charAt(0));

        int after = values.get(s.charAt(n - 1)), total = after;
        for (int i = n - 2; i >= 0; i--) {
            int curr = values.get(s.charAt(i));
            if (curr >= after) total += curr;
            else total -= curr;
            //update after
            after = curr;
        }

        return total;
    }

    /*
    1436. Destination City https://leetcode.com/problems/destination-city/
    understand:
        I/O: list of paths/return String dest
        constraints: len >= 1, A != B
        edge: if n = 1, return the list.get(0).get(1)
    match:
        hashing
    plan:
        1.set store all start city
        2.check which city is not in set, it will be the dest
    */
    public String destCity(List<List<String>> paths) {
        int n = paths.size();
        Set<String> set = new HashSet<>();

        //use set store all start city
        for(List<String> path: paths){
            set.add(path.get(0));
        }

        //find which city the set not contains
        for(List<String> path: paths){
            if (!set.contains(path.get(1))){
                return path.get(1);
            }
        }

        return null;
    }

    /*
    Session 1
    347. Top K Frequent Elements https://leetcode.com/problems/top-k-frequent-elements/
    understand:
        I/O: arr, int k/return arr(k)
        constraints: len of arr >= 1, k >= 1, the answer is unique
            better than O(nlogn)
        edge: if arr len is 1, k = 1, return arr
    match:
        hashmap
    plan:
        1. set a map, store num as k, its freq as value,
        2. use a priorityqueue, as k size, keep top k freq num
        time: O(nlogn), space: O(n)
     */

    public static int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        if (n == 1){
            return nums;
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        //stores k-num, v-num's freq
        for (int i = 0; i < n; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        //use priorityque k-size
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> (map.get(a) - map.get(b)));
        //loop map key, add into pq, poll when pq's range bigger than k
        for (Integer num: map.keySet()){
            pq.add(num);
            if (pq.size() > k){
                pq.poll();
            }
        }

        //add pq num into ans arr
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll();
        }

        return ans;
    }

    /*
    Longest Consecutive Subsequence https://www.geeksforgeeks.org/longest-consecutive-subsequence/
    Given an unsorted array of integers, we want to find the length of the longest subsequence
    such that elements in the subsequence are consecutive integers. The consecutive numbers can be
    in any order.
    [1, 9, 3, 10, 4, 20 , 2]
     |
     set save all ele, remove du

     loop arr:
        if set not contains arr[i] - 1:
            //start from arr[i]
            curr = arr[i]
            use while loop find if set contains next num:
                curr++
            update the ans: max(ans, curr - arr[i])

    understand:
        I/O: int[]/ return int[], any order
        constraints: n <= 1, return input arr
        edge: n <= 1
    match:
        hashing
    plan:
        1.use set store all ele
        2.loop arr, if set not contains ith ele - 1, set curr is ith ele,
            use while loop check if set contains curr, curr++
            update the max ans with curr - arr[i]
    time: O(n), space: O(n)
     */
    private static int longestConsecutiveSubsequence(int[] arr){
        int n = arr.length;
        if (n <= 1) return n;
        int ans = 1;

        //1.use set store arr's eles
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }

        //2.loop arr, check if set contains
        for (int i = 0; i < n; i++) {
            if (!set.contains(arr[i] - 1)){
                int curr = arr[i];
                while (set.contains(curr)){
                    curr++;
                }
                ans = Math.max(ans,curr - arr[i]);
            }
        }

        return ans;
    }

    public static boolean isIsomorphic(String s, String t) {
        //edge: if both 1, true
        int n = s.length(), m = t.length();
        if (n == 1 && m == 1) return true;

        //use map, k-ith char at s, v-ith char at t
        HashMap<Character, Character> map = new HashMap<>();

        for (int i = 0; i < n; i++){
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (map.containsKey(c1)){
                if (map.get(c1) != c2) return false;
            }
            else{
                if (map.containsValue(c2)){
                    if (map.get(c1) != c2) return false;
                }
                map.put(c1, c2);
            }

        }

        return true;
    }





}
