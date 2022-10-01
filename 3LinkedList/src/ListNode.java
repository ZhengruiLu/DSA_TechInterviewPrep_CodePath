public class ListNode {
    int val;
    ListNode next;

    public ListNode() {
        this.val = -1;
        this.next = null;
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
