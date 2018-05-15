# Preview
## 三路快排

![threeWaysQuickSort_preview.gif](https://github.com/Z-Beatles/algorithm/blob/master/threeWaysQuickSort_preview.gif)

# 项目结构
> 说明：*AlgoVisualizer.java* 为可视化效果的启动类

```
───src
    └───cn
        └───waynechu
            │   AlgoVisHelper.java    // Graphics2d 绘图工具类
            │   
            ├───bubble    // 气球碰撞效果可视化
            │       AlgoFrame.java
            │       *AlgoVisualizer.java*
            │       Circle.java
            │       
            ├───maze    // 图的遍历可视化（待补充）
            │       maze_101_101.txt
            │       
            ├───money    // 模拟分钱问题可视化
            │       AlgoFrame.java
            │       *AlgoVisualizer.java*
            │       
            ├───montecarlo    // 蒙特卡罗算法可视化
            │   ├───pi    // 计算π值
            │   │       AlgoFrame.java
            │   │       *AlgoVisualizer.java*
            │   │       Circle.java
            │   │       MonteCarloData.java
            │   │       MonteCarloExperiment.java
            │   │       
            │   ├───threegates    // 求解三门问题
            │   │       ThreeGatesExperiment.java
            │   │       
            │   └───winprize    // 求解开宝箱问题
            │           WinPrizeExperiment.java
            │           
            └───sort    // 排序算法可视化
                │   ListNode.java
                │   Main.java    // 排序算法统一测试类
                │   MaxHeap.java
                │   SortDataHelper.java
                │   
                ├───bubble    // 冒泡排序
                │       AlgoFrame.java
                │       *AlgoVisualizer.java*
                │       BubbleSortData.java
                │       BubbleSortExperiment.java
                │       
                ├───heap    // 堆排序
                │       AlgoFrame.java
                │       *AlgoVisualizer.java*
                │       HeapSortData.java
                │       HeapSortExperiment.java
                │       
                ├───insertion    // 插入排序
                │       AlgoFrame.java
                │       *AlgoVisualizer.java*
                │       InsertionSortData.java
                │       InsertionSortExperiment.java
                │       
                ├───merge    // 归并排序
                │       AlgoFrame.java
                │       *AlgoVisualizer.java*
                │       MergeSortData.java
                │       MergeSortExperiment.java
                │       MergeSortLinkedList.java
                │       
                ├───quick    // 快速排序
                │   │   QuickSortExperiment.java
                │   │   QuickSortLinkedList.java
                │   │   
                │   ├───oneway    // 快速排序-单路快排
                │   │       AlgoFrame.java
                │   │       *AlgoVisualizer.java* 
                │   │       OneWayQuickSortData.java
                │   │       
                │   ├───twoways    // 快速排序-双路快排
                │   │       AlgoFrame.java
                │   │       *AlgoVisualizer.java*
                │   │       TwoWaysQuickSortData.java
                │   │       
                │   └───threeways    // 快速排序-三路快排
                │           AlgoFrame.java
                │           *AlgoVisualizer.java*
                │           ThreeWaysQuickSortData.java
                │           
                └───selection    // 选择排序
                        AlgoFrame.java
                        *AlgoVisualizer.java*
                        SelectionSortData.java
                        SelectionSortExperiment.java
```