public class Main {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));

    }

    public static ListNode deleteDuplicates(ListNode head) {
        //edge
        if (head == null || head.next == null) return head;

        //remove duplicates from start
        while (head.next != null && head.val == head.next.val)
            head = head.next;

        //check duplicates inner
        ListNode curr = head, pre = new ListNode(0);
        pre.next = curr;

        while (curr != null && curr.next != null){
            ListNode next = curr.next;
            if (curr.val != next.val){
                pre = curr;
            }else{
                pre.next = next;
            }
            curr = next;
        }

        return head;
    }

    public static boolean hasCycle(ListNode head) {
        //edges
        if (head == null || head.next == null) return false;

        //set slow and fast pointers
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }

        return false;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //edges: if both null, return null, if either null, return another one
        if (list1 == null && list2 == null) return null;

        if (list1 == null) return list2;
        if (list2 == null) return list1;

        //use 2 pointers, comparing their val, put the smaller one first
        ListNode dummy = new ListNode(-1);
        ListNode p1 = list1, p2 = list2, p = dummy;

        while (p1 != null && p2 != null){
            if (p1.val < p2.val){
                p.next = p1;
                p1 = p1.next;
            }else{
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        //add the extra part at the end
        if (p1 != null){
            p.next = p1;
        }
        if (p2 != null){
            p.next = p2;
        }

        return dummy.next;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        //edges
        if (head == null || head.next == null) return head;

        //loop and check duplicates, remove and link pre and next
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;

        while (head != null){
            if (head.next != null && head.val == head.next.val){
                while (head.next != null && head.val == head.next.val){
                    head = head.next;
                }
                pre.next = head.next;
            }else{
                pre = pre.next;
            }

            head = pre.next;
        }

        return dummy.next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //edge
        if (head.next == null && n == 1) return null;
        //let slow ptr go n steps
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy, fast = head;
        for (int i = 0; i < n; i++){
            fast = fast.next;
        }
        //then move both slow and fast while fast != null
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        //link slow and and slow.next.next
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //set dummy
        ListNode dummy = new ListNode(-1);

        //use two ptr get vals, sum up and get remain
        ListNode p1 = l1, p2 = l2, p = dummy;
        int sum = 0, remain = 0;
        while (p1 != null || p2 != null){
            //considering if p1 or p2 not exist
            int p1Val = p1 != null ? p1.val : 0;
            int p2Val = p2 != null ? p2.val : 0;

            //add both val and remain!
            sum = p1Val + p2Val + remain;
            p.next = new ListNode(sum % 10);
            remain = sum / 10;

            p = p.next;
            //considering if p1 or p2 not exist
            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }

        if (remain > 0){
            p.next = new ListNode(remain);
        }

        return dummy.next;
    }
}
