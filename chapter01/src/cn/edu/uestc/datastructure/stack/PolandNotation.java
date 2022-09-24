package cn.edu.uestc.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/*   面试经常问的知识点：如何将中缀表达式转后缀表达式  思路：
1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
2) 从左至右扫描中缀表达式；
3) 遇到操作数时，将其压s2；
4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
5) 遇到括号时：(1) 如果是左括号“(”，则直接压入s1(2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
6) 重复步骤2至5，直到表达式的最右边
7) 将s1中剩余的运算符依次弹出并压入s2
8)  依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式

 */

//经常把字符串存到ArrayList中的原因是它比较灵活，更容易遍历
public class PolandNotation {
    public static void main(String[] args) {
        String suffixExpression = "3 4 + 5 * 6 -";
        String expression = "1+((2+3)*4)-5";//注意表达式

        PolandNotation polandNotation = new PolandNotation();
        List<String> list = polandNotation.storeNotation(suffixExpression);

        list.forEach(list1-> System.out.printf(list1));
        int result = calculate(list); //该方法是静态方法！
        System.out.println("----------------");
        System.out.println("结果是："+result);

        //测试将中缀表达式转换为集合存储在内
        List<String> infixExpression = toInfixExpressionList(expression);
        for (String data :
                infixExpression) {
            System.out.print(data + ",");
        }

        System.out.println("------------中序转后缀表达式---------------");
        List<String> suffixExpressionList = PolandNotation.parseSuffixExpressionList(infixExpression);

        for (String data :
                suffixExpressionList) {
            System.out.print(data + " ");
        }

    }



    /**
     * 当作一个小算法！总结！！！
     * 1、先将中缀表达式转成对应的List  infix:中序
     * @param s "1+((2+3)*4)-5"
     * @return
     * 写为这个发现do while(条件) 的好处是，在do中变量++，之后再判断符不符合(先让最后一个元素进行对应操作，操作完毕后i++,之后发现越界了，就自动退出)
     */
    //  s="1+((2+3)×4)-5";
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List,存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0; //这时是一个指针，用于遍历 中缀表达式字符串
        String str; // 对多位数的拼接
        char c; // 每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，我需要加入到ls
            if((c=s.charAt(i)) < 48 ||  (c=s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; //i需要后移
            } else { //如果是一个数，需要考虑多位数
                str = ""; //先将str 置成"" '0'[48]->'9'[57]
                while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;//返回
    }

    /**
     * 2、中缀表达式转后缀表达式：
     * 静态方法直接类目调用就好了，方法类
     * @param list 返回一个后缀表达式
     * @return
     */
    public static List<String>  parseSuffixExpressionList(List<String> list) {

        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();//因为s2全称不需要pop，使用ArrayList更加方便！

        //先完成数字和括号的逻辑，这个比较简单，再完成优先级的问题
        for (String data : list) {
            if (data.matches("\\d+")) {
                //遇到操作数时，将其压s2
                s2.add(data);
            } else if (data.equals("(")) {
               // 如果是左括号“(”，则直接压入s1
                s1.push(data);
            }else if (data.equals(")")){
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                //注意栈里面又peek方法，能做到只查看栈顶元素，而不弹出（pop）的作用
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                //前往别忘记删除，只要删除s1中的括号，就算把这一对括号丢弃
                s1.pop();
            }else {
                //遇到运算符时，比较其与s1栈顶运算符的优先级
                //写到这里，发现缺少这个方法，去补齐
                /*
                    1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
                    2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
                    3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较(这里就要进行循环啊，如果满足这个优先级的条件)；
                 */
                //静态方法调用：类目.方法名（），不需要new对象！
                while ( s1.size()!=0 && Operation.priority(data) <= Operation.priority(s1.peek()) ){
                    //只要s1还有内容，满足这条件的时候(s1栈顶的优先级不比当前扫描到的高)，这个是要反复做的！
                    s2.add(s1.pop());
                }
                //不满足上面的循环了，执行2
                s1.push(data);
            }



        }
        //最后还要将s1中属于的内容全部转移到s2中
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;//由于是list，返回去的时候就已经是正序的了！
    }

    /**
     * 3、将一个逆波兰表达式， 依次将数据和运算符 放入到 ArrayList中
     * @param suffixExpression 输入逆波兰表达式
     * @return 返回一个list
     */
    public List<String> storeNotation(String suffixExpression){
        //接口指向实现
        List<String> list = new ArrayList<>();
        //以空格进行分割,空格自己不记录
        String[] expression = suffixExpression.split(" ");
        for (String data:
             expression) {
            list.add(data);
        }
        return list;
    }
    //完成对逆波兰表达式的运算
        /*
            1)从左至右扫描，将3和4压入堆栈；
            2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
            3)将5入栈；
            4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
            5)将6入栈；
            6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果

         思路是遍历前面的集合，使用正则表达式判断是不是数字来进行以上入栈等操作
         */
    public static int calculate(List<String> list){
        //创建一个栈，记住这个创建方法！
        Stack<String> stack = new Stack<>();
        //正则表达式对应有String的match方法，可以看这个方法的注解
        for (String data:
             list) {
            if (data.matches("\\d+")){
                //匹配到多位数，应该入栈
                stack.push(data);
            }else {
                //匹配到运算符，应该pop两个数进行运算
                int data1 = Integer.parseInt(stack.pop());
                int data2 = Integer.parseInt(stack.pop());
                int res = 0;

                //字符串比较的大错误，之前你一直用== ！！！！！
                if (data.equals("+")){
                    res = data1 + data2;
                } else if (data.equals("-")) {
                    //一定注意顺序
                    res = data2 - data1;
                }else if (data.equals("*")){
                    res = data1 * data2;
                }else if (data.equals("/")){
                    //一定注意顺序
                    res = data2 / data1;
                }else {
                    throw new RuntimeException("输入表达式有误！！");
                }
                //是运算符，完成加减乘除操作后，应该入栈！
                stack.push(res+"");//int转String最方便的方法！
            }
        }
        return Integer.parseInt(stack.pop());
    }



}

class Operation{
    public static int ADD = 1;
    public static int DEL = 1;
    public static int MUL = 2;
    public static int DIV = 2;

    public static int priority(String operation){
        //用switch语句是最好的
        int retVal = 0;
        if (operation.equals("+")){
            retVal = ADD;
        }else if (operation.equals("-")){
            retVal = DEL;
        }else if (operation.equals("*")){
            retVal = MUL;
        }else if (operation.equals("/")){
            retVal = DIV;
        }else {
            //throw new RuntimeException("输入中的操作符有误！");
        }
        return retVal;
    }

}
