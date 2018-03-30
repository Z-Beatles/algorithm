package cn.waynechu.sort.merge;

import cn.waynechu.sort.ListNode;

/**
 * 归并排序单向链表
 *
 * @author waynechu
 * Created 2018-03-29 23:16
 */
public class MergeSortLinkedList {

    public static ListNode mergeSortLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 获取中间节点
        ListNode mid = getMid(head);
        // 拆分单链表为两部分
        ListNode apart = mid.next;
        mid.next = null;

        // 将 head1 和 head2 两部分进行归并
        ListNode head1 = mergeSortLinkedList(head);
        ListNode head2 = mergeSortLinkedList(apart);
        return merge(head1, head2);
    }

    private static ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = (a == null) ? b : a;
        return dummy.next;
    }

    private static ListNode getMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 快指针到结尾时慢指针在中间
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(8);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(7);
        ListNode node6 = new ListNode(5);
        ListNode tail = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = tail;

        System.out.println(head.printList());
        System.out.println(mergeSortLinkedList(head).printList());
    }
}
