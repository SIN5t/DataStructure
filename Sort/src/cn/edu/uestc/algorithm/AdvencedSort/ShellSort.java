package cn.edu.uestc.algorithm.AdvencedSort;
/*
2 希尔排序
    2.1 算法描述
    1959年Shell发明，第一批突破O(n2)时间复杂度的排序算法，是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序

    算法核心思想是先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：

    先根据数组的长度/n，获取增量K（第一次n为2）
    按增量序列个数k进行分组，一般可以分成k组；
    根据以分好的组进行插入排序；（每组排序，根据对应的增量k来找到当前组的元素）
    当每组都排序完毕之后，回到第一步将n*2再次分组进行插入排序，直到最终k=1的时候，在执行一次插入排序完成最终的排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arrs = {99,0,54,-3,5,2,-44,1,31,-33};
        int group = arrs.length;
        //等于1的时候说明无法再分组了
        while (group!=1){//这个循环代表的是 多次分不同步长的组;其实用for循环完全一样，只不过条件写里面了
            //进行分组
            group = group/2;

            for (int i = 0; i < group; i++) {//这个循环是 相同步长的 各个组都进行排序

                int j = i;//原因是下面循环中后面j也要进行改变

                // 下面循环：如果是奇数个元素，那就会有第二次，偶数个元素的话，这个循环只执行一次
                while (j + group < arrs.length){//这个循环是对 相同组内每个不同元素进行排序

                    //下面进行插入排序(下面循环的意思是每个元素可能需要交换多次位置，但如果排序的元素是偶数个，一次交换就好了，不需要循环)

                    int insert = j + group;//需要插入的元素
                    // j = 0 时，0是第一个排好的元素，j+group是第二个需要进行排序的元素

                    while ((insert - group) >= 0){//只要没到数组最前面，没越界
                        //insert是当前要插入的元素，insert-group是它前一个元素
                        if (arrs[insert] < arrs[insert - group]){
                            //如果当前的插入元素小，就交换顺序
                            int temp = arrs[insert];
                            arrs[insert] = arrs[insert - group];
                            arrs[insert - group] = temp;

                            //第一次交换完毕之后，还可能还要交换(针对奇数个元素时第三个元素，可能需要交换两次)
                            insert = insert -group;
                        }else {
                            //找到了插入点
                            break;
                        }
                    }
                    j+=group;//对循环使用for的话，这句话应该写道for的第三个自增条件中，还是写for好
                }
            }
        }

        //输出数组：
        for (int i = 0; i < arrs.length; i++ ){
            System.out.print(arrs[i] + " ");
        }
    }

}
