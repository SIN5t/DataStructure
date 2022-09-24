

/**
 *  迷宫问题，用到递归、回溯
 *
 *  git: 改了代码先提交本地库，commit changes
 *
 */
public class Maze {

    public static void main(String[] args) {
        //使用二维数组模拟迷宫
        //地图，二维数组中 1 表示墙
        int[][] map = new int[8][7];
        for (int i = 0; i < map.length; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }

        for (int i = 1; i < map[0].length-1; i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //设置挡板, 1 表示
        map[3][1] = 1;
        map[3][2] = 1;
        //显示一下原来的迷宫
        list(map);

        //进行
        boolean result = setWay(map, 1, 1);
        System.out.println(result);
        list(map);


    }
    //遍历迷宫的方法
    //回忆二维数组，int[][] arr = {{1,2,5},{7,10,15},{2,34,56,0}}; arr.length=3，就是里面的三个元素！arr[i].length是具体的
    public static void list(int[][] map){
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*使用递归回溯来给小球找路
    //说明
    1. map 表示地图
    2. i,j 表示从地图的哪个位置开始出发 (1,1)
    3. 如果小球能到 map[6][5] 位置，则说明通路找到.
    4. 约定： 当map[i][j] 为 0 表示该点没有走过 当为 1 表示墙  ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
    5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
    */

    /**
     *
     * @param map 迷宫地图
     * @param i i,j 表示从地图的哪个位置开始出发 (1,1)
     * @param j
     * @return 如果找到通路，就返回true, 否则返回false，
     *      return的值正好用于if条件判断，如果遇到墙、障碍物，if(setWay(map, i, j))直接不满足，进行下一个方向的判断，都不满足，就是最后的return false
     */
    public static boolean setWay(int[][] map, int i, int j){
        if (map[4][5] == 2){
            //方法是一个个点去按约定走，如果能走通，该点置2.
            // 一开始是初始值0，如果map[6][5]=2，说明已经走了一条通路一直到map[6][5]了
            return true;
        }else {
            //如果这个点是没走过的点，那就走一轮试试,走过的点就不要再走了，要不然会出问题
            if (map[i][j]==0){
                //先假定该点是通的，如果不通，后面再重新置一个值
                map[i][j] = 2;
                //按照策略 下->右->上->左  走
                //一路都在置2，直到map[6][5] == 2 置到这个的时候是处于栈顶, 说明走通了

                if (setWay(map,i+1,j)){
                    //一开始一直向下走，以为按顺序执行。这是第一条
                    return true;
                }else if (setWay(map,i,j+1)){
                    //一路下走碰壁后，上面的if不满足开始右走一次
                    return true;
                }else if (setWay(map, i-1, j)){
                    return true;
                }else if (setWay(map, i, j-1)){
                    return true;
                }else {
                    //这个点是走不通的，已经走过了，撤销前面的置2，置为3
                    map[i][j] = 3;
                    return false;
                }
            }else {// 如果map[i][j] != 0 , 可能是 1， 2， 3

                return false;
            }
        }
    }

}
