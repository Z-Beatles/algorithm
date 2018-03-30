package cn.waynechu.sort.quick;

import cn.waynechu.sort.ListNode;

/**
 * 快速排序单向链表
 *
 * @author waynechu
 * Created 2018-03-30 09:33
 */
public class QuickSortLinkedList {

    public static void quickSort(ListNode head, ListNode tail) {
        if (head == null || head == tail) {
            return;
        }

        int pivot = head.val;
        ListNode lt = head;
        ListNode i = head.next;
        // 分区 [head...lt] <= pivot; [lt.next...tail] > pivot
        while (i != tail.next) {
            if (i.val <= pivot) {
                lt = lt.next;
                int tmp = i.val;
                i.val = lt.val;
                lt.val = tmp;
            }
            i = i.next;
        }
        head.val = lt.val;
        lt.val = pivot;

        quickSort(head, lt);
        quickSort(lt.next, tail);
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
        quickSort(head, tail);
        System.out.println(head.printList());
    }
}
