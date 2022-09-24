package cn.edu.uestc.search;

public class BinarySearch {

    public static void main(String[] args) {
        //二分法一定要注意是已经排序好的数组！！！！
        int[] arrs = {1,2,3,4,5,6,7,8,9};

        //肯定使用到递归，用到递归，那就要有left、right并且注意最好习惯是左闭右闭
        int first = 0;
        int last = arrs.length - 1;//右闭
        int target = 3;
        int result = binarySearch(arrs,first,last,target);

        for (int data :
                arrs) {
            System.out.print(data + " ");
        }

        if (result != -1){
            System.out.println("找到目标值：" + target + "; 其下标位置为:" + result);
        }else {
            System.out.println("没有找到目标");
        }
    }

    private static int binarySearch(int[] arrs, int left, int right, int target) {

        int result = -1;
        //如果输入了一个数组没有的元素会怎么样？------>栈内存溢出
        //如何解决？----->最后一次执行的时候      left = right ,再执行下去，left就会 > right
        if (left > right){
            result = -1;
            return result;
        }

        int mid = (right + left)/2;
        if (target == arrs[mid]){
            //多次递归后，最后只要在里面的值，可能能被找到且为该次递归的mid
            //System.out.println("找到目标值：" + target + "; 其下标位置为:" + mid);
            result = mid;
            return result;
        }else if (target < arrs[mid]){
            //在左半边的数组中
            return binarySearch(arrs,left,mid-1,target);
        } else if (target > arrs[mid]) {
            //在右半边数组
            return binarySearch(arrs,mid + 1, right, target);
        }
        return result;
    }
}
