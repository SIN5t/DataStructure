package cn.edu.uestc.datastructure.ArrayList;

/*
    1、抽象数据类型可以对应的一个 Java 类, 数据对象与元素之间的关系 可以通过成员变量 来存储和表示;数据操作可以通过一组方法来实现
    2、数据结构的四种逻辑结构: 集合, 线性, 树形, 网状
    3、使用 Java 中的接口来表示 ADT（abstract data type） 中的数据操作, 在使用类完成抽象数据类型时,
       只要 这个类实现接口即可完成抽象数据类型中定义的操作
    4、这里定义的全都是成员方法，都未实现
    5、“List在java里是一种集合,类型为接口。其实现类中ArrayList与数组最为相似,但并非数组

*/

/**
 * 过接口定义一组线性表中的操作
 */
public interface MyList {
    int getSize();
    boolean isEmpty();
    //返回线性表中元素的个数 //判断线性表是否为空
    void insert(int i , Object e); //在线性表的i索引值添加元素e
    boolean contains(Object e); //判断线性表中是否包含元素e int indexOf(Object e );
    //返回线性表中元素e的索引值
    Object remove(Object e); Object remove(int i);
    //在线性表中第一个与e相同的元素,并返回该元素 //在线性表中索引值为i的元素,并返回该元素
    Object replace(int i , Object e); //使用元素e替换线性表中i位置的元素, 并返回旧的元素 Object get(int i);
    //返回索引值为i的元素
    Object get(int i);
    //返回索引值为i的元素
    boolean insertBefore( Object p, Object e); //在线性表元素p的前面插入元素e
    boolean insertAfter( Object p, Object e);
}

