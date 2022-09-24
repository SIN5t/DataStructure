package cn.edu.uestc.algorithm.SimpleSort;
/*
3 插入排序
3.1 算法描述
    1从第一个元素开始，该元素可以认为已经被排序；
    2取出下一个元素，在已经排序的元素序列中从后向前扫描；
    3如果该元素（已排序）大于新元素，将该元素移到下一位置；
    4重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
    5将新元素插入到该位置后；
    6重复步骤2~5。

    简单的说就是，第一个元素看作是已经排序好的（因为就一个元数）
    后面依次拿元素和前面（排序好的）的比，去寻找插入点，如果前面的比这个元素大，那就换序

    和前面两者排序不同的是，这次要学到一个思想，arrs[0+1] = arrs[0]元素后移的算法，
    这样前面那个元素的位置空下来了。还有一个重要思想就算分为两部分，一部分是未排序的数组，另外是已排序的
 */
public class InsertSort {
    public static void main(String[] args) {
        //生成数组
        int[] arrs = {51,-4,-11,9,50,1,34,55,12,3,4,8,};

        /*//把51看作排序好的元素，第二个元素去寻找插入点
        int insert = arrs[1];//第二个元素是要去找插入点的那个插入元素
        if (insert < arrs[0]){
            arrs[1] = arrs[0];
            arrs[0] = insert;
        }
        //第二次比较
        insert = arrs[2];
        if (insert < arrs[1]){
            //该arrs[1]向后移动一个单位（1+1）
            arrs[1+1] = arrs[1];

            //arrs[1] = insert;这个代码不急着写，因为现在insert要插入的位置还没定,
            // arrs[0]还没比，不一定是arrs[1]
        }
        //继续找插入点
        if (insert < arrs[1-1]){
            //arrs[0]应该向后移动一个单位（0+1）
            arrs[0+1] = arrs[0];

            //插入点寻找完毕了，那可以插入了
            arrs[0] = insert;
        }*/

       /* //找到这种前移的规律

        int start = 3;
        int insert = arrs[start];
        while (start >= 0){
            if (insert < arrs[start-1]){
                //前移
                arrs[start] = arrs[start-1];
                start--;
               }else {
                   //找到了应该插入的位置！！
                   arrs[start] = insert;
               }
        }*/

       for (int start = 1; start < arrs.length; start++){
           int insert = arrs[start];
           while (start > 0){
               if (insert < arrs[start-1]){
                   arrs[start] = arrs[start-1];
                   start--;
               }else {
                   //找到了应该插入的位置！！
                   //arrs[start] = insert;
                   break;//千万别忘记
               }
           }
           //执行到这里，要么是break，要么是不满足start>0,都是找到了插入点，应该进行插入！
           arrs[start] = insert;
       }
        for (int arr : arrs) {
            System.out.print(arr + ",");
        }

    }
}
