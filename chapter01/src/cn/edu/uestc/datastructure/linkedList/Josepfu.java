package cn.edu.uestc.datastructure.linkedList;

/*
    实现单向环形链表的思路：
        创建第一个节点，first指向该节点，并形成环状。首先有一个头节点first，这个不动，如果只有一个节点，first.next = first
        设置一个当前指针current，先让current 指向first
        三部曲：1、每添加一个节点，current.next = newNode;2、newNode.next = first;3、current指针 = newNode；

    遍历该环形链表的思路：
        设置一个指针，当该指针.next=first时停止遍历

    数数跳出的思路：
        先找到第一个开始数的boy;      还是需要一个指针helper,先指向这个链表的最后一个元素
        从这个boy开始，数m-1次;     boy开始报数时。first和helper指针同时移动m-1次
        出圈                  （通过helper）让first指向的boy出圈 注意结束条件，first = helper

    注意：对某个节点进行删除，只需要让它前一个节点不指向它就行了，对于一个没有任何引用的节点，会被垃圾回收


 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(5);
        //linkedList.goThrough();
        linkedList.countBoy(1,2,5);//2-4-1-5
    }
}

class CircleSingleLinkedList{
    //属性，里面肯定是有first指针的,初始化一下，先让它没有编号（-1）
    Boy first = new Boy(-1);

    //通过添加节点创建单向环形链表的方法
    public void addBoy(int num){

        if (num < 1){
            throw new IndexOutOfBoundsException("输入有误");
        }
        else {
            Boy currentBoy = first;
            for (int i = 1; i <= num; i++) {
                Boy boy = new Boy(i);
                if (i==1){
                    //只有一个节点，自己构成环
                    first = boy;
                    //boy.setNext(boy);
                    boy.setNext(first);
                    //这句代码不能少！！因为它的作用是把上一个节点指向这个新节点用的，这里第一次就不对curren指针更新，还是指向id=-1那个节点后面会出错
                    currentBoy = boy;
                }else {
                    //三部曲,仔细想想其实currentBoy作用就是当下一个节点来的时候，用来把上一个节点指向这个新节点用的。
                    currentBoy.setNext(boy);
                    boy.setNext(first);
                    currentBoy = boy;
                }

            }
        }
    }

    /**
     * 写一个遍历该单向环型链表的方法
     */
    public void goThrough(){
        Boy temp = first;
        //忘记了这一设置
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        while (temp.getNext() != first){
            System.out.println(temp);
           //temp.setNext(temp.getNext()); 错误写法！！是让这个temp指针一直动，而不是一直修改他的next
            temp = temp.getNext();
        }
        //执行到这里，还有最后一个元素没输出！
        System.out.println(temp);


    }

    /**
     * 根据输入，计算出boy出圈的顺序
     * @param startNo 开始位置
     * @param countSize 报数的大小
     * @param nums 最初在圈内的小孩个数
     */
    public void countBoy(int startNo,int countSize,int nums){
        //首先判断参数是否合法
        if (first == null ||startNo < 1 || startNo>nums){
            throw new IndexOutOfBoundsException("输入参数有误");
        }
        Boy helper = first;
        //先将helper定位到first的前一个位置（链表最后一个），到时候方便一起移动n个位置
        while (true){
            if (helper.getNext()==first){
                break;
            }
            helper = helper.getNext();
        }
        //将helper和first都移动到报数起始点
        for (int i = 0; i < startNo-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //开始报数，本人开始，只需要报countSize-1次
        while (helper!=first){
            for (int i = 0; i < countSize-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //数到了对应的人，这时候应该出圈
            System.out.println("出圈的boy是： " + first);
            //first应该前移一个单位
            first = first.getNext();
            //下面这句话相当于中间出圈的那个元素没东西指向它了，会被垃圾回收
            helper.setNext(first);
        }
        System.out.println("最后留着圈内的小孩是： " + helper);
    }
}


class Boy{
    private Boy next;
    private int id;

    public Boy(){}
    public Boy(int id){
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "该节点的是：id = "+id+" 的男孩";
    }
}


