package cn.edu.uestc.algorithm.SimpleSort;
/*
选择排序
    1.1 算法描述
        首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
        然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
        以此类推，直到所有元素均排序完毕。
 */
public class SelectSort {
    public static void main(String[] args) {
        //定义一个未排序的数组
        int[] arrays = {99,0,54,3,5,2,44,1,31,33,44};
        //想法是先取出最小的，与最前面的元素交换位置，然后循环实现

        /*  下面的代码多写几轮，找到规律
        //找到最小值的算法要很熟练：
        int minCount = 0;
        for (int i = 1; i < arrays.length; i++ ){
            if (arrays[minCount] > arrays[i]){
                minCount = i;//一旦找到更小的，立刻将i变为那个更小的索引值
            }
        }
        int temp = arrays[0];
        arrays[0] = arrays[minCount];
        arrays[minCount] = temp;
        //通过多次写重复的找到规律然后进行循环
        */

        for (int i = 0; i < arrays.length - 1; i++){
            int minCount = i;
            for (int j = minCount + 1; j < arrays.length; j++){
                //找到这一轮循环的最小元素
                if (arrays[minCount] > arrays[j]) {
                    //只要找到比你小的，你就变成那个小的，从而找到最小
                    minCount = j;
                }
            }
            int temp = arrays[i];
            arrays[i] = arrays[minCount];
            arrays[minCount] = temp;
        }
        for (int array : arrays) {
            //想解决最后元素的逗号问题，可以用带下标的for循环，if判断是否到了最后一个元素
            System.out.print(array + ",");
        }
    }
}
