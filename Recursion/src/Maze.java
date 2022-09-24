

/**
 *  迷宫问题，用到递归、回溯
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

        list(map);


    }
    //遍历迷宫的方法
    //回忆二维数组，int[][] arr = {{1,2,5},{7,10,15},{2,34,56,0}}; arr.length=3，就是里面的三个元素！arr[i].length是具体的
    public static void list(int[][] map){
        for (int i = 0; i < map.length; i++){
            System.out.println();
            for (int j = 0; j < map[i].length; j++){
                System.out.print(map[i][j] + " ");
            }
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
     * @return 如果找到通路，就返回true, 否则返回false
     */
    public boolean setWay(int[][] map, int i, int j){
        if (map[6][5] == 2){
            //方法是一个个点去按约定走，如果能走通，该点置2.
            // 一开始是初始值0，如果map[6][5]=2，说明已经走了一条通路一直到map[6][5]了
            return true;
        }else {

        }
    }

}
