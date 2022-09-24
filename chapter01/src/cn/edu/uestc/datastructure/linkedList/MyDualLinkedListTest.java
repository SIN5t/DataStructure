package cn.edu.uestc.datastructure.linkedList;

public class MyDualLinkedListTest {
    public static void main(String[] args) {
        MyDualLinkedList linkedList = new MyDualLinkedList();
        linkedList.add(new HeroNode(1,"林冲","11教头"));
        linkedList.add(new HeroNode(2,"宋江","及时雨"));
        linkedList.add(new HeroNode(3,"吴用","军师"));
        linkedList.add(new HeroNode(4,"宋江江","及时雨"));
        linkedList.add(new HeroNode(8,"宋江江江1","及时雨鱼鱼"));
        linkedList.add(new HeroNode(9,"宋江江江2","及时雨鱼鱼"));
        linkedList.add(new HeroNode(10,"宋江江江3","及时雨鱼鱼"));
        //linkedList.add(new HeroNode(10,"宋江江江4","及时雨鱼鱼"));
        linkedList.list();
        System.out.println(linkedList.size);

        System.out.println("----------------------");
        linkedList.update(new HeroNode(1,"林冲","林教头"),1);
        //linkedList.update(new HeroNode(1,"6666","21321"),1);//用于测试id不在列表中
        linkedList.list();


        System.out.println("-----------------------");
        linkedList.del(10);
        linkedList.list();
        System.out.println(linkedList.size);

        System.out.println("--------指定位置添加元素测试------------");
        linkedList.add(new HeroNode(5,"宋江江(最后插入)","及时雨"),8);
        linkedList.add(new HeroNode(6,"宋江江（第一个位置插入)","及时雨"),1);
        linkedList.add(new HeroNode(7,"林冲冲（中间位置插入）","林教头"),3);
        linkedList.list();

    }
}
