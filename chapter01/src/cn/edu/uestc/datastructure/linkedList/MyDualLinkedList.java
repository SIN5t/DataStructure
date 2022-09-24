package cn.edu.uestc.datastructure.linkedList;
/*
注意：if…  else if…   else 语句
    一个 if 语句后可跟一个可选的 else if…else 语句，这可用于测试多种条件。
    当使用 if…else if…else
    语句时，以下几点需要注意：
    一个 if 后可跟零个或一个 else，else 必须在所有 else if 之后。
    一个 if 后可跟零个或多个 else if，else if 必须在 else 之前。
    一旦某个 else if 匹配成功，其他的 else if 或 else 将不会被测试。
 */

/**
 * 使用双向链表实现水浒好汉榜单
 */
public class MyDualLinkedList {
    //为了方便，可以有has a 的结构的属性！ 注意，只要没赋值，就都是null
    HeroNode head = new HeroNode(0,"","");//头节点不动，头的next作为第一个节点.直接初始化了，可以不要在构造方法中初始化，因为是死的，不会变
    int size;

    /**
     * 定义一个遍历链表的方法
     */
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;//一定不要少！
        }
        //head不能动，所以用temp
        HeroNode temp = head.next;
        while (temp != null){
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    /**
     * 在链表最后添加新的元素
     * @param hero 传入的梁山好汉
     */
    public void add(HeroNode hero){
        if (head.next == null){
            //让双向链接起来
            head.next = hero;
            hero.pre = head;
            size++;
            return;
        }
        checkId(hero.id);
        HeroNode temp = head.next;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = hero;
        hero.pre = temp;
        size++;
    }

    /**
     * 在指定位置添加元素 ，上下两个方法构成方法重载
     * @param hero
     * @param position
     */
    public void add(HeroNode hero,int position){

        //首先 如果链表有3个元素，插入位置肯定是不能>3
        if (position > size+1 || position < 0){
            throw new IndexOutOfBoundsException("输入的位置不合法");
        }

        HeroNode temp = head.next;
        if (temp == null){
            //空的
            head.next = hero;
            hero.pre = head;
            size++;
            return;//加完元素可以退了
        }
        //空表不能查，因为遍历会出错！
        checkId(hero.id);

        //插入位置是头、尾
        if (position == 1){

            HeroNode temp1 = head.next;
            temp1.pre = hero;
            hero.next = temp1;

            hero.pre = head;
            head.next = hero;
            size++;
        }
        else if (position == size+1){
            //比如size = 3 我要在第四个位置插入,那就是最后一个元素

            //先遍历得到最后一个元素
            while (temp.next != null){
                temp = temp.next;
            }
            //增加
            temp.next = hero;
            hero.pre = temp;
            size++;
        }
        //中间插入
        else{

            //先遍历得到对应元素
            for (int i = 0; i < position; i++) {
                temp = temp.next;
            }
            HeroNode temp1 = temp;
            temp1.pre.next = hero;
            hero.pre = temp1;

            hero.next = temp.next;
            temp.next.pre = hero;
        }




    }

    /**
     * 根据编号id修改链表内容,注意，修改的是name或者nickname,都是根据id来改的！！
     */
    public void update(HeroNode hero, int id){
        if (size == 0){
            System.out.println("当前数据为空，无法修改");
            return;
        }
        if (id <= 0){
            throw new IndexOutOfBoundsException("输入的id有误");
        }
        HeroNode temp = head.next;
        //先找到目标修改节点，匹配上id
        while (true){
            if (temp.id == id ){
                //找到目标
                break;
            }
            if (temp.next == null && temp.id != id)
            {
                System.out.println("输入id不在列表中，更新失败！");
                return;
            }
            temp = temp.next;
        }
        temp.name = hero.name;
        temp.nickName = hero.nickName;
        //temp = hero;这么写不对，因为hero当前只有id，name，nickName三个属性，没有定义pre和next属性
    }

    /**
     * 根据id删除节点
     * @param id
     */
    public void del(int id){
        if (size == 0){
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if (temp.id == id){
                //找到了目标
                break;
            }
            //最后一个节点还没找到对应目标 这样写更好：if（temp==null）直接这样也行
            if (temp.next == null && temp.id != id){
                System.out.println("数据中未找到该id对应的好汉！");
                return;//注意return 和 break的用法区别开
            }
            temp = temp.next;
        }

        //能执行到这说明找到了要删除的目标
        //temp = null;错误原因：temp只是一个指针，原来添加数据的时候，和这里的temp没关系，让 temp = null只是当前指针是null和数据无关！
        temp.pre.next = null;
        //区别是这样同过指向，修改了属性值：前一个指针的next

    }

    /**
     * 写一个检查id是否正确的函数,包括检查id是否重复
     * @param id
     */
    public void checkId(int id){
        if (id < 0){
            System.out.println("输入id有误，不在范围内");
            //return;推出的只是当前方法而已！
            throw  new IndexOutOfBoundsException("输入id越界");
        }
        HeroNode heroNode = head.next;
        //while (heroNode.next != null){最后一个节点的next=null，这样就跳过了最后一个节点！
        while (heroNode != null){
            if (heroNode.id == id){
                throw new IndexOutOfBoundsException("输入id重复，无法添加元素");
            }
            heroNode = heroNode.next;
        }
        System.out.println("id无重复且合法，操作继续");
    }

}


/**
 * 如果写的是内部类,内部类能够访问外部类的所有成员，包括private修饰的，但是这样new对象比较麻烦，现在还没学会....
 * 每个好汉看作是一个数据节点
 */
class HeroNode{
    //为了方便写public，如果是private后来访问都要通过set和get方法！
    public int id;

    public String name;
    public String nickName;
    public HeroNode pre;
    public HeroNode next;

    //构造方法初始化我们需要看到的参数
    public HeroNode(int id, String name,String nickName){
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}



