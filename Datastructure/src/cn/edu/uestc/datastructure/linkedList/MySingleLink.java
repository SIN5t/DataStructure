package cn.edu.uestc.datastructure.linkedList;

/*
    链表没有下标的概念，链表的遍历不能使用下标方式，但同样可以通过从首元结点开始，
    依次访问到最后一个结点，这必然使用到循环
 */

import cn.edu.uestc.datastructure.ArrayList.MyList;

public class MySingleLink implements MyList {
    //注意属性好习惯用private修饰
    private Node head; //头节点也是个节点，也具备和其他节点一样的属性
    private int size; //初始化为0

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    对于insert方法，需要考虑很多：
        1、插入的i是否合法？不合法抛异常
        2、插入的位置什么也没有，newNode就是head
        3、插入位置在头节点，思路是 newNode先指向head，之后在让head指向newNode，newNode变为头节点
        4、插入位置在中间：
            用一个指针节点，先去找出插入位置前一个节点，让这个节点的next现在指向newNode
            再让newNode指向i位置的节点，size++
        5、一定要记住size++
     */
    @Override
    public void insert(int i, Object e) {
        //Node newNode = new Node();

        Node newNode = new Node(e,null);

        if (i < 0 || i > size - 1){
            throw new IndexOutOfBoundsException(i + "越界"); //又没记住，这里一定要new
        }
        //头结点为null的情况,链表不存在, 刚刚添加的结点就是头结点
        if (head ==null){
            /*
            //这样复杂了,如果head == null就代表链表不存在，不需要使用size来判断
            newNode.setData(e);
            newNode.setNext(null);
            */
            head = newNode; //读作head节点 ！指向! newNode节点

        }else {
            if (i == 0){
                newNode.next = head;//新插入节点的下一个节点是head，都是在操作！引用！！
                head = newNode; //成功插入到第一个节点了，现在newNode应该是head
            }else {
                //这个else对于的仅仅是 i != 0; 同时还满足了前面的 head!=null，和越界没关系。 很细

                /*
                找到 i 位置之前一个node 的算法是这样写的！记住！
                链表没有下标的概念，链表的遍历不能使用下标方式，但同样可以通过从首元结点开始，
                依次访问到最后一个结点，这必然使用到循环
                 */

                Node pNode = head;//拿一个指针去找我们要的元素，这体现了链表的缺点：检索效率底
                for (int j = 0; j < i; j++ ){
                    pNode = pNode.next;
                }
               /* pNode.next = newNode;
                newNode.next = 不会了...*/
                //多想一下啊！倒一个顺序，就写不出来了！
                newNode.next = pNode.next;
                pNode = newNode;
            }
            size ++;//这个一般放在最后！因为上面的代码能够执行到这里说明已经成功把节点加上了
        }
    }


    /*
    注意：
        1、Object比较要用equals进行，既让用到这个，就要考虑比较对象是否是null；如果整个head==null，说明链表不存在
        2、Node里面的数据可以是null，判断是否包含该数据，也要判断是否包含null的数据
        3、判断是否包含，应该想到用indexOf（）第一次出现该元素的下标
     */
    @Override
    public boolean contains(Object e) {
        return indexOf(e) >=0 ;
    }

    private int indexOf(Object e) {
        int i = 0;
        Node pNode = head;
        while (head != null){//只要链表存在.学会使用while：当里面代码块要一直重复执行，直到满足某个条件
            //对每一个Node进行判断
            if (e == null && pNode.data == null){
                return i;
            }
            if (e != null && e.equals(pNode.data)){
                return i;
            }
            //不满足上面两个if，那就下一个Node；满足直接return，结束当前的方法！
            pNode = pNode.next;
            i++;
        }
        return -1; //可不是return i
    }

    /*
    remove（Object e）-->使用indexOf（）把Object转为index--->使用remove（int i）
    remove 删除数据之所以是Object类型的，就是在删除数据的时候要返回被删除的数据！这点一直忘记！
     */
    @Override
    public Object remove(Object e) {
        int index = indexOf(e);
        if (index == -1){
            return null;//这里应该要想到使用return，因为下面的语句块不能再接着执行，必须要停止方法
        }else {
            remove(index);
            return e;
        }
    }

    @Override
    public Object remove(int index) {
        Node pNode = head;
        if (index < 0){
            throw new IndexOutOfBoundsException(index + "越界");
        }
        if (index == 0){
            //删除头节点的要单独
            head.next = head;
            size--;//第一百次忘记！
            //一定要返回被删除的元素，否则返回值的类型怎么会是Object
            return pNode;
        }else {
            for (int i = 0; i < index; i++) {
                pNode = pNode.next;
            }
            //拿到了index对应节点的前一个节点
            Object obj = pNode.data;
            pNode.next = pNode.next.next;
            return obj;
        }
    }


    //通过循环找对应index的Node一直写太麻烦了，直接封装一个方法！
    //再来一个验证index是否正确的方法！
    @Override
    public Object replace(int index, Object e) {

        /*  Node pNode = head;

        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException( index + "越界");
        }
        for (int j = 0; j < index; j++) {
            pNode = pNode.next;
        }
        Object old = pNode.next.data;
        pNode.next.data = e;
        return old;*/

        checkIndex(index);
        Node pNode = getNode(index);
        Object old = pNode.next.data;
        pNode.next.data = e;
        return old;
    }

    /**
     * 获取index-1 位置的节点
     * @param index
     * @return
     */
    private Node getNode(int index) {
        Node pNode = head;
        if (index < 0 || index > size){
            return null;
        }
        if (index == 0){
            return pNode;
        }
        //每次都写这么直接，下面这段代码是有前提的！！！ j 的值是否合法，是否是头节点！！都是很重要的事情！
        for (int j = 0; j < index; j++) {
            pNode = pNode.next;
        }
        return pNode;

    }

    private void checkIndex(int index) {
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException( index + "越界");
        }
    }

    @Override
    public Object get(int i) {
        checkIndex(i);
        Node node = getNode(i);
        return node.next.data;
    }

    @Override
    public boolean insertBefore(Object p, Object e) {
        int i = indexOf(p);
       /* checkIndex(i);
        Node node = getNode(i);
        node.next.data = e;
        return false;*/
        //还是写的不够好！这里是boolean类型！前面写个的方法现在可以拿来用啊！
        if (i < 0 || i > size){
            return false;
        }else {
            insert(i,e);
            //size++;  不能写！因为insert方法里面就已经写了该size++
            return true;
        }
        //else可以不写
    }

    @Override
    public boolean insertAfter(Object p, Object e) {
        int index = indexOf(p);
        if (index < 0 || index > size){
            return false;
        }
        insert(index+1,e);
        return true;
    }

    //重写toString，把表中各个元素都连接起来！元素之间使用逗号分隔开！
    @Override
    public String toString() {
        Node pNode = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size + 1; i++) {
            pNode = getNode(i);
            if (pNode !=null){
                sb.append(",");
            }
            sb.append(pNode.data);
        }

        sb.append("]");
        return sb.toString();

        /* 上面的算法太麻烦。看老师写的！
        while(pNode != null) {
            sb.append(pNode.data);
            //使用逗号分隔
            if (pNode.next != null) {
                sb.append(",");
            } pNode = pNode.next;
        }
            */
    }
}



//内部类：这样写在创建对象的时候存在麻烦，所以还是老实写外部类吧，下次遇到内部类再研究
class Node{
    //一个Link节点的属性：has关系 数据，下一个节点，next是引用
    Object data;
    Node next;

    //构造方法初始化


    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Node() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
