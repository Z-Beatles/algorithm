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

        // 将 head1 和 head2 两个有序的单链表进行归并
        ListNode head1 = mergeSortLinkedList(head);
        ListNode head2 = mergeSortLinkedList(apart);
        // return mergeTwoListsByIteration(head1, head2);
        return mergeTwoListsByRecursive(head1, head2);
    }

    /**
     * 迭代实现的合并两个有序单链表
     */
    public static ListNode mergeTwoListsByIteration(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = (l1 == null) ? l2 : l1;
        return dummy.next;
    }

    /**
     * 递归实现的合并两个有序单链表
     */
    public static ListNode mergeTwoListsByRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode mergeNode;
        if (l1.val > l2.val) {
            mergeNode = l2;
            mergeNode.next = mergeTwoListsByRecursive(l2.next, l1);
        } else {
            mergeNode = l1;
            mergeNode.next = mergeTwoListsByRecursive(l1.next, l2);
        }
        return mergeNode;
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
