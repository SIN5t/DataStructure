package cn.edu.uestc.algorithm.SimpleSort.GuiGu;

/**
 * 冒泡排序
 *      1、一共进行 数组-1 次大循环，每次大循环里面都把相邻元素进行交换，每次都把最大的、次大的....冒泡到最后面
 *      2、每一次轮大排序下，小排序次数减一（最后的已经排好了）
 *      3、如果在某趟排序中没有发生交换，可以提前结束冒泡排序，这个作为优化
 *
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] sort = {3,9,-1,10,-2,5,-2,-3,-47,-8,7};
        int[] sorting = BubbleSorting(sort);

        for (int i = 0; i < sorting.length; i++) {
            System.out.print(sorting[i] + " ");
        }
    }


    /**
     * 写冒泡的方法
     * @param sort
     * @return
     */
    public static int[] BubbleSorting(int[] sort){
        int first = 0;
        int temp = 0;
        int count = 0;

        for (int i = 0; i < sort.length-1; i++) {
            //int next = first + 1;
            while (first+1 < sort.length - i){

                if (sort[first] > sort[first + 1]){
                    //int temp = sort[first];//不要每次循环都去开辟存储空间
                    temp = sort[first];
                    sort[first] = sort[first + 1];
                    sort[first + 1] = temp;
                    count++;
                }
                first++;
            }
            /**
             * 优化
             */
            if (count == 0){
                return sort;
            }
            first = 0;
        }
        return sort;
    }
}
