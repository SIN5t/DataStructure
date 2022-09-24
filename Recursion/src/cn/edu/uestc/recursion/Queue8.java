package cn.edu.uestc.recursion;

/**
 *
 * 用了一维数组表示二维数组：数组下标表示每一行，数组元素值表示具体哪一列
 * 三大方法
 *      1、写一个可以将皇后摆放位置输出的方法
 *      2、判断摆放位置是否冲突的方法
 *      3、放第n个皇后的方法，使用到递归回溯
 *
 * 另外知识点：
 *      Java中static关键字可以达到全局的效果，静态变量属于类
 *      被static修饰的成员变量或成员方法独立于该类的任何对象（实例），被所有类对象共享。如果一个实例对象对静态成员变量做了修改，其他实例对象访问该静态成员变量也会受到影响。
 */
public class Queue8 {
    int max = 8;
    int [] arrays = new int[max]; //数组下标表示是哪一个皇后（第几行），元素的值表示的是该皇后在哪一列
    static int count;
    static int judgeCount;

    public static void main(String[] args) {
        //测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法",count);
        System.out.printf("一共判断了%d次冲突",judgeCount);
    }

    /**
     * 3、放置第n个皇后，每一个皇后从该行的第一列开始放，去判断是否冲突，冲突了就放下一列，不冲突这个皇后就放好了，去放下一个
     *      如果每一列都放完了，都冲突，那这一层递归的循环语句执行结束，回溯到上一个皇后，接着找下一个不冲突的点
     * @param n 第n个皇后
     */
    private void check(int n){
        if (n == max){
            //从0-7 已经放满8个了,一共打印了多少次，就一共有多少个解法
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后 n , 放到该行的第1列
            arrays[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){
                //如果当前放置的没问题，准备放下一个。递归回溯
                check(n+1);
            }
            //如果冲突，继续执行array[n] = i;
        }
    }

    /**
     *  2、判断第n个皇后是否冲突
     *
     *      1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
     *      2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
     *          n和i本身代表是行,而array[n]或者array[i]代表的是列，分别做差表示行差了多少，列差了多少，如果正好相差一样多，那说明在同一斜线上
     * 	        n = 1  放置第 2列 1 n = 1 array[1] = 1
     * 	        Math.abs(1-0) == 1  Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
     * 	    3. 判断是否在同一行, 没有必要，n 每次都在递增
     * @param n 第几个皇后
     * @return
     */
    public boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (arrays[n] == arrays[i] || Math.abs(n-i) == Math.abs(arrays[n]-arrays[i])) {
                return false;
            }
        }
        return true;
    }




    /**
     * 1、输出皇后摆放的位置
     *
     */
    public void print(){
        count++;
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ");
        }
        //在这里换行的目的是，上面的语句输出完毕之后，下面的输出就不会在同一行，注意是在循环结束后写这条语句
        System.out.println();
    }


}
