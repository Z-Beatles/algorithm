package cn.waynechu.sort;

import java.util.Arrays;

/**
 * Definition for MaxHeap.
 *
 * @author waynechu
 * Created 2018-04-01 22:41
 */
public class MaxHeap {
    private int[] data;
    /**
     * 当前节点数量
     **/
    private int size;
    /**
     * 当前容量
     **/
    private int capacity;
    /**
     * 初始化容量
     **/
    private static final int DEFAULT_CAPACITY = 10;

    private static final int[] EMPTY_ELEMENTDATA = {};

    public MaxHeap() {
        this.data = EMPTY_ELEMENTDATA;
    }

    public MaxHeap(int initialCapacity) {
        if (initialCapacity > 0) {
            this.data = new int[initialCapacity + 1];
            this.capacity = initialCapacity;
        } else if (initialCapacity == 0) {
            this.data = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * 返回当前堆内节点数
     *
     * @return 堆内节点数
     */
    public int size() {
        return size;
    }

    /**
     * 判断堆是否为空
     *
     * @return 如果当前堆中没有元素返回true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加新元素到堆中
     *
     * @param item 新元素
     * @return true
     */
    public boolean insert(int item) {
        ensureCapacity(size + 1);
        // 0索引位置不存储元素，直接从1开始存储
        data[size++ + 1] = item;
        // 将末尾添加的结点向上移
        shiftUp(size);
        return true;
    }

    /**
     * 弹出根节点
     *
     * @return 根节点的值
     */
    public int extractMax() {
        if (size > 0) {
            int result = data[1];
            swap(1, size--);
            // 将头结点向下移
            shiftDown(1);
            return result;
        }
        throw new IndexOutOfBoundsException("堆为空!");
    }

    /**
     * 扩容
     *
     * @param minCapacity 要求最小容量
     */
    private void ensureCapacity(int minCapacity) {
        if (data == EMPTY_ELEMENTDATA) {
            this.capacity = DEFAULT_CAPACITY;
            this.data = new int[capacity + 1];
        } else if (size + 1 >= capacity) {
            int newCapacity = capacity + (capacity >> 1);
            capacity = newCapacity;
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    /**
     * 尾节点上移
     *
     * @param i 新插入元素位置(尾节点)
     */
    private void shiftUp(int i) {
        while (data[i / 2] < data[i] && i > 1) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    /**
     * 头结点下移
     *
     * @param i 取出的元素的位置(头结点)
     */
    private void shiftDown(int i) {
        // 有子节点（左孩子）
        while (2 * i <= size) {
            int j = 2 * i;
            // 右孩子大于左孩子
            if (j + 1 <= size && data[j + 1] > data[j]) {
                j += 1;
            }
            // 父节点大于两个子节点
            if (data[i] >= data[j]) {
                break;
            }
            swap(i, j);
            // 考察下一节点
            i = j;
        }
    }

    private void swap(int index1, int index2) {
        int tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;
    }
}
