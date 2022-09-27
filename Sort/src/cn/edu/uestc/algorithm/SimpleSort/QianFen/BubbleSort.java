package cn.edu.uestc.algorithm.SimpleSort.QianFen;
/*
冒泡排序
        2.1 算法描述
    首先在未排序数组的首位开始，和后面相邻的数字进行比较，如果前面一个比后面一个大那么则进行交换。
    接下来在将第二个位置的数字和后面相邻的数字进行比较，如果大那么则进行交换，直到将最大的数字交换的数组的尾部。
    然后再从排序的数组的首位开始，重复前面两部将最大的数字交换到未排序数组的尾部（交换到尾部的数字是已经拍好序的）。
    如此反复，直到排序完毕。

    冒泡和选择很大的不一样就是，冒泡直接交换，交换的算法要很熟，选择找最小元素，这个算法也要很熟
 */
public class BubbleSort {
    //关键就是相邻比较交换，每一轮都把最大的冒泡到最后，又从头开始。
    public static void main(String[] args) {
        //准备一个数组
        int[] arrs = {4,88,15,0,-152,1,4,5,6,7};

        for (int j = 0; j < arrs.length - 1; j++) {
            //模拟第一轮排序,多写几轮找规律就知道了
            for (int i = 0; i < arrs.length - 1 - j; i++) {
                //现在一定要学会看，i < arrs.length的意思就算arrs[i]会遍历到最后一个元素，那arrs[i+1]肯定越界！
                if (arrs[i] > arrs[i + 1]) {
                    int temp = arrs[i];
                    arrs[i] = arrs[i + 1];
                    arrs[i + 1] = temp;
                }
            }
        }
        for (int arr : arrs) {
            System.out.print(arr + ",");
        }
    }
}
