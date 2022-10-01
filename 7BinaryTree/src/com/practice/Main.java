package com.practice;
import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    //q2: isSameTree
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        //base
        if (p == null && q == null) return true;
        if (checkNodeEqual(p, q) == false) return false;

        Deque<TreeNode> que1 = new LinkedList<>();
        Deque<TreeNode> que2 = new LinkedList<>();
        que1.add(p);
        que2.add(q);

        while (!que1.isEmpty()){
            TreeNode curr1 = que1.removeFirst();
            TreeNode curr2 = que2.removeFirst();

            if (!checkNodeEqual(curr1, curr2)){
                return false;
            }
            if (curr1 != null){
                if (!checkNodeEqual(curr1.left, curr2.left)) return false;

                if (curr1.left != null && curr2.left != null){
                    que1.add(curr1.left);
                    que2.add(curr2.left);
                }

                if (!checkNodeEqual(curr1.right, curr2.right)) return false;
                if (curr1.right != null && curr2.right != null){
                    que1.add(curr1.right);
                    que2.add(curr2.right);
                }
            }

        }

        return true;

        // return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }

    private static boolean checkNodeEqual(TreeNode p, TreeNode q){
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.val != q.val) return false;

        return true;
    }
/*
    p2: insertIntoBST
    understand
    I/O node, int/node root
    constraints root is null? val is unique?
    edge
        if root is null, return null
    match
            iterate
    plan
    1.binary search, find the pos where insert the val, use pre node record the previous curr node
            comparing val vs curr.val, if <, go left, if > go right, if find curr is leaf
    2.insert it based on the pre.val vs val, to decide its left child or right child

    time: O(h)
    space: O(h) if recursive, O(1) if iterative
 */

    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }
        else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    public static TreeNode insertIntoBSTInterative(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        //find the pos using binary search
        TreeNode pre = null, curr = root;

        while (curr != null){
            if (val < curr.val){
                pre = curr;
                curr = curr.left;
            }else{
                pre = curr;
                curr = curr.right;
            }
        }


        if (val < pre.val) {
            pre.left = new TreeNode(val);
        }
        else {
            pre.right = new TreeNode(val);
        }

        return root;
    }

    /*
    q3: sortedArrayToBST
    understand:
        I/O: arr asc/node root
        constraints: at least 1
        edge: if len is 1, return new node(val is nums[0])
    match:
        inorder, binary search, dfs
    plan:
        1.get the mid idx of arr, use mid ele as root
        2.let root.left = dfs(arr, left part - startIdx, endIdx), let root.right same to right part
        3.return root

        time: O(N), O(logn)
*/

    static int[] nums;

    public static TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;

        int n = nums.length;
        if (n == 1) return new TreeNode(nums[0]);


        return dfs(nums, 0, n - 1);
    }

    private TreeNode dfs(int[] nums, int left, int right){
        if (left > right) return null;
        if (left == right) return new TreeNode(nums[left]);

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = dfs(nums, left, mid - 1);
        root.right = dfs(nums, mid + 1, right);

        return root;
    }
}
