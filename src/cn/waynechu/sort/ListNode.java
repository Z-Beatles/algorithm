package cn.waynechu.sort;

/**
 * Definition for singly-linked list.
 *
 * @author waynechu
 * Created 2018-03-29 23:18
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public String printList() {
        ListNode node = this;
        StringBuilder builder = new StringBuilder();
        while (node != null) {
            builder.append(node.val).append(" - ");
            node = node.next;
        }
        return builder.substring(0, builder.length() - 3);
    }
}

