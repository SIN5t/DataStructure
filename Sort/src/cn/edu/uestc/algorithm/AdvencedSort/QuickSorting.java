package cn.edu.uestc.algorithm.AdvencedSort;

public class QuickSorting {
    public static void main(String[] args, int left, int right) {
        int[] arrs = {3,9,8,5,2,7,4,1,6};
        quickSort(arrs);
        for (int i = 0; i < arrs.length; i++){
            System.out.print(arrs[i] + " ");
        }
    }

    public static void quickSort(int arrs[]){
        int left = 0;
        int right = arrs.length - 1;
        int pos = arrs.length - 1;

        while (left != right) {
            while (arrs[left] <= arrs[pos]){
                left++;
            }

            while (arrs[right] >= arrs[pos] && (left < right)){
                right--;
            }

            if (left < right){
                //还是要判断，因为只有满足这个条件才能交换，否则不交换
                int temp = arrs[left];
                arrs[left] = arrs[right];
                arrs[right] = temp;
            }
        }
        //执行到这里，已经完成了左边比基准值小，右边比基准值大
        //现在应该做的就是把改基准值放在中间,拿左指针或者右指针都行
        int temp1 = arrs[left];
        arrs[left] = arrs[pos];
        arrs[pos] = temp1;
    }
}

