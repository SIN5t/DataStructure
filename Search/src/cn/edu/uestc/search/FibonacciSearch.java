package cn.edu.uestc.search;

/*
    斐波那契算法的理解：
        斐波那契数组的！值！  是要和目标数组的！下标！进行对应、映射的     值（fib值是有黄金分割意义的）----》下标（就是二分法需要分割的位置、权重）
        目标数组！长度！要和斐波那契数组最后一个元素的！值！一样，然后算出用斐波那契数组算出mid值，这个值对应的就是目标数组的下标！

 */

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * f[k] = (f[k-1]) + (f[k-2]) 这样就满足了斐波那契数列 f代表数列，k代表的是第几个元素。
 * 对两边都减一变为 ： f(k)- 1= （f(k-1)-1） + （f(k-2)-1） + 1   最后这个 +1 可以看作是要查询元素。
 * 因此mid = first +（f(k-1)-1）
 *
 *      如果查询数组arrs不够斐波拉契数组f(k)对应的值（是fib的值值值，他的值是有黄金分割意义的），需要则需要将查询数组
 * 扩容至⼀样的⻓度的临时数组temp，并且将多余的值使⽤查询数组最⼤值arrs[last]进⾏补全
 */


public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arrs = {1, 2, 3, 4, 5, 18, 19, 21, 22};
        int target = 22;
        int i = fibSearch(arrs, target);

        if (i!=-1){
            System.out.println("找到该target，下标为：" + i);
        }else {
            System.out.println("未找到该元素！！！");
        }

    }
    //先构造一个斐波那契数组
    public static  int[] fib(int size){
        int[] f = new int[size];
        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i < f.length; i++) {
            f[i] = f[i-1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找函数
     * @param arrs 传入待查找的数组
     * @param target 查找的目标元素
     * @return  -1说明没找到 ，其他值为返回的下标
     */
    public static int fibSearch(int[] arrs, int target){
        int result = -1;//如果没找到，就返回-1
        int[] fib = fib(50);//构造⼀个斐波那契数列，到时候具体需要多少，获取对应下标就行了
        int k = 0;//斐波那契数组的下标

        //定义起点和终点，其实是写到后面 在 判断 low < high 的时候开始再补上的
        int low = 0;
        int high = arrs.length - 1;

        //根据数组的最⼤下标获取斐波那契数列所需要使⽤的个数,主要思想就是循环遍历fib数组，只要目标数组的长度还比fib的下标大，那就让下标继续++
        while (high > fib[k] - 1){
            //注意fib[k]-1是fib数组的元素值，这个元素值是有实际物理黄金比例点意义的，所以用这个值来对应目标数组的下标
            k++;
        }

        //查询数据有可能⼩于斐波那契数列最⼤值所需要的⻓度，所以进⾏补全
        //这里用的Arrys类(注意有s)，构建一个新的数组，并指向原数组,多为位置自动补零
        int[] temp = Arrays.copyOf(arrs,fib[k]);

        //该算法中多余的不能是0，应该得是最后一个数的补充
        for (int i = high + 1; i < fib[k]; i++) {
            temp[i] = arrs[high];
        }

        //到这里已经有了和斐波那契相符合的数组了，进行类二分查找
        while (low <= high){

            ////根据斐波那契数列获取查询中间值,这个是我们最想得到的东西！mid
            int mid = low + fib[k-1] - 1;

            if (target < temp[mid]){
                //在左边的数组中
                high = mid - 1;

                //下面这个很关键！！ 因为f[i] = f[i-1] + f[i-2] 所以下次循环mid = fib[k-1-1] - 1
                k--;
            } else if (target > temp[mid]) {
                low = mid + 1;
                //为什么-2呢？还是因为f[i] = f[i-1] + f[i-2]，这时候是右边，所以取f[i-2]这么多个值就行了，后面mid = low + fib[k-1]-1
                k -= 2;
            }else {
                //执行到这里已经成功找到了
                //但是还是要确认是哪个下标，因为temp数组可能是延长后的！
                if(mid <= high){
                    //说明没取到最后面
                    result = mid;
                    return result;
                }else {
                    result = high;
                    return result;
                }
            }
        }
        return result;
    }


}
