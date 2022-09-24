package cn.edu.uestc.datastructure.ArrayList;

/*
 StringBuilder 上的主要操作是 append 和 insert 方法。
    每个方法都能有效地将给定的数据转换成字符串，然后将该字符串的字符添加或插入到字符串生成器中。
    append 方法始终将这些字符添加到生成器的末端；而 insert 方法则在指定的点添加字符。

    ArrayList移除元素后，剩下的元素会立即重排，他的 size() 也会立即减小，在循环过程中容易出错。
    ArrayList插入元素也是按顺序插入的

    注意：ArrayList指定位置插入元素，如果该位置前面没有元素的话ArrayList会报错！！！IndexOutOfBoundsException
        如果已经有元素的话，那就会移位！
 */

import java.io.FileNotFoundException;

/**
 * 现在使用数组来模拟实现线性表，而不是直接使用线性表！！
 */

public class MyArrayList implements MyList {
    //List list = new ArrayList<Object>();
    //List在java里是一种集合,类型为接口。其实现类中ArrayList与数组最为相似,但并非数组
    private Object[] elements;//酒店的那道题其实有写过！一定要总结，数组如何在构造方法中初始化！
    private int size; //int类型定义的数组,初始化默认是0
    public static final int INITIAL_CAPACITY = 16;

    //构造方法的作用就是初始化！ 不止是你常用的 this.
    public MyArrayList(int CAP) {

        elements = new Object[CAP];
    }
    public MyArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    //为了不忘记，先重写toString方法！这个类是你自己写的，所以想要输入引用就能遍历元素
    // 把线性表中每个元素连接起来, 遍历数组中已添加的元素


    @Override
    public String toString() {
        //练习StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++){
            sb.append(elements[i]);
            if (i < size-1){
                sb.append(",");//目的是最后一个元素结束了，结尾没逗号
            }
        }
        sb.append("]");
        return sb.toString();//是这样返回的！记一下！

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override //想要优化的话可以在text中使用三目运算符
    public boolean isEmpty() {
        return size == 0;
    }


    /*
        插入元素想要具备的思想：
            1、如果输入的i不合法或者i太大了越界，那就应该抛异常！---这是第一步考虑的！思想！
            2、如果数组已满 , 对数组扩容（直接写个扩容的方法，调用就行）---第二步的思想
            3、到这一步，就是以上情况都不存在，可以进行元素依次后移，注意！后移应该从最后一个元素开始移动,只要j>i的元素都右移一位！

     */
    @Override
    public void insert(int i, Object e) {
        if (i > INITIAL_CAPACITY || i < 0 ){
            throw new IndexOutOfBoundsException(i + "越界"); //抛异常记得new， 里面传的是字符串
        }
        if(size >= elements.length){
            expandSpace();
        }
        //执行到这，开始正常操作：
        //向后移位：
        for (int j = size-1; j >= i; j--){//好好理解：要插入的元素位置是i，应该从最后一位开始，每个元素向后移动一位
            elements[j+1] = elements[j];
        }
        //移动完毕，开始插入
        elements[i] = e;
        size++; //你的遗漏点！

    }

    /**
     * 数组扩容
     */
    private void expandSpace() {
        Object[] newElements = new Object[2*elements.length];
        //elements = newelements;   思维不够全面，不是扩容完了就行！以前的数据都要传进去！
        for (int i =0; i < elements.length; i++){
            newElements[i] = elements[i];
        }
        //让原来的数组名指向新的数组
        elements = newElements;
    }


    @Override
    public boolean contains(Object e) {
        /*for (int i = 0; i < size; i++){
            if (elements[i] == e){ //应该用equals
                return true;
            }
        }
        return false;*/
        /*
            值类型（int,char,long,bolean等）都是用=判断相等性。对象引用的话，=判断引用所指的对象是否是同一个。
            equals是Object的成员函数，有些类会覆盖（overwrite）这个方法，用于判断对象的等价性。

            因为在使用contains方法的时候，底层是用.equal进行判断的
            所以在使用Collection的时候，里面每一个元素都应该重写toString方法，否则比的就是地址

            老师写的用的是indexOf()方法，判断出第一次出现该元素的下标
         */
        return indexOf(e) >= 0 ;
    }
    //返回第一次出现该对象的下标的函数
    private int indexOf(Object e) {
        //将null考虑在内。线性表中,用户可能添加 null
        if (e == null){
            for (int i = 0; i < size; i++){
                if (elements[i] == null){  //null需要用 == 判断
                    return i;
                }
            }
        }else{
            for (int i = 0; i < size; i++){
                if (elements[i].equals(e)){ //对象比较！应该用equals,而不是 ==
                    return i;
                }
            }
        }
        return -1;
    }

    //使用indexOf实现
    @Override
    public Object remove(Object e) {
        int index = indexOf(e);
        if (index == -1){
            try {
                throw new FileNotFoundException("找不到该文件"); //
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            return null;
        }
        return remove(index); //转成下一个通过下标处理，并且返回的是当前对象Object
    }

    //删除元素的思想要完整
    @Override
    public Object remove(int i) {
        if (i >= size || i < 0){ //注意 >= ,这个等号也要加！！
            throw new IndexOutOfBoundsException(i+"下标越界");
        }
        //删除一个元素，先把这个元素保存起来，整个数组也要移位
        Object removeObj = elements[i];
        for (int j = i + 1; j < size - 1; j++ ){//一定要注意和size有关的都要想着-1，因为它就单纯是个数
            elements[j-1] = elements[j];
        }
        //最后一个元素要置 为 null!! 这个也没想到，思路不完整
        elements[size - 1] = null;
        size--; //又忘记的操作！
        return removeObj;

    }

    @Override
    public Object replace(int i, Object e) {
        Object old = elements[i];//旧元素保存起来
        elements[i] = e;
        return old;
    }

    //返回索引值为i的元素
    @Override
    public Object get(int i) {
        return elements[i];
    }

    //在线性表元素p的前面插入元素e
    @Override
    public boolean insertBefore(Object p, Object e) {
        //思路严谨，需要有前提
        int index = indexOf(p);
        if (index < 0){
            return false;
        }
        insert(index,e);
        return true;
    }

    @Override
    public boolean insertAfter(Object p, Object e) {
        int index = indexOf(p);
        if (index < 0){
            return false;  //p 元素不存在, 插入不成功
        }
        insert(index + 1,e);
        return true;
    }

}
