package cn.edu.uestc.algorithm.AdvencedSort;
/*
    4 归并排序
    4.1 算法描述
    归并排序是利用归并的思想实现的排序方法，该算法采用经典的分治策略即将问题分成一些小的问题然后递归求解，而治的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之.

    归并排序是稳定排序，它也是一种十分高效的排序，能利用完全二叉树特性的排序一般性能都不会太差。java中Arrays.sort()采用了一种名为TimSort的排序算法，就是归并排序的优化版本。从上文的图中可看出，每次合并操作的平均时间复杂度为O(n)，而完全二叉树的深度为|log2n|。总的平均时间复杂度为O(nlogn)。而且，归并排序的最好，最坏，平均时间复杂度均为O(nlogn)。

    归并排序核心思想是先分再治,具体算法描述如下:

    先将未排序数组/2进行分组,然后再将分好组的数组继续/2再次分组,直到无法分组,这个就是分的过程.

    然后在将之后再把两个数组大小为1的合并成一个大小为2的，再把两个大小为2的合并成4的,同时在合并的过程中完成数组的排序,最终直到全部小的数组合并起来,这个就是治的过程.

    治的过程中会为两个数组设计两个游标,和一个新的数组.

    分比比较两个游标指对应数组的元素,将小的插入到新的数组中
    然后向后移动较小的数组的游标,继续进行比较.
    反复前面两步,最终将两个数组中的元素排序合并到新的数组中
 */

public class MergeSort{
    public static void main(String[] args) {
        int[] arrs = {8,6,3,7,2,5,4,1};
        mergeSort(arrs, 0, arrs.length-1);

        for (int data : arrs) {
            System.out.print(data + " ");
        }

    }

    //实现归并排序
    public static void mergeSort(int[] arrs, int first, int last) {
        //1分：将当前数组一分为二

        if (first >= last){
            return;
        }
        int middle = (first + last)/2;
        //递归调用，一定要记得加停止条件，每次递归都有一个变量在变，当这个变量达到一定的需求的时候，就是停止条件
        mergeSort(arrs,first,middle);//理解一下，这句话的含义就是一次一次对半拆开，注意拆完有很多层
        mergeSort(arrs,middle + 1, last);
        //注意，这里的last不在是用来数组的尾部7，而是上一句代码的middle，也就是  分  了很多组以后每个组的尾部
        //归并多次调用之后，满足条件就会return，多次退出后，最后剩余的一次（递归执行的结果）就是第一次分的时候。

        //2治：合并两个数组，注意，由于递归的存在，每一轮都是对好不同的数组进行排序，因此要及时更行到原始数组

        //先定义一个数组用于临时存放数据，其实这个放在全局变量比较好，这也多次递归就不会多次生成它
        int[] temp = new int[last+1];
        //定义指针 i j t
        int t = 0;
        int i = first;
        int j = middle+1;

        //进行排序，哪个元素小，就把这个元素先放到临时数组中
        while (i <= middle && j <= last){
            //while (j <= last){不应该使用双重循环

            if (arrs[j] <= arrs[i]){
                temp[t++] = arrs[j++];
            }else {
                //第一轮只有两个元素，而之后的数组元素前面都已经排序好了，因此这样写没问题
                temp[t++] = arrs[i++];
            }
        }
        //执行到这里，要么不满足条件退出，要么j不满足条件退出
        while (i <= middle){
            temp[t++] = arrs[i++];
        }
        while (j <= last){
            temp[t++] = arrs[j++];
        }

        //将临时数组中元素复制到原始数组中去
        int z = 0;
        for (int k = first; k <= last; k++) {
            arrs[k] = temp[z++];
        }
    }
}