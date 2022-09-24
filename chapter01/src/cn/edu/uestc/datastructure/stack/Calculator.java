package cn.edu.uestc.datastructure.stack;

public class Calculator {

    public static void main(String[] args) {
        String formula = "1+70+9/3-1*10";
        Calculator cal = new Calculator();
        cal.calculate(formula);

    }
    public void calculate(String formula){
        ArrayStack01 numStack = new ArrayStack01(10);
        ArrayStack01 operStack = new ArrayStack01(10);
        int index = 0;//用于扫描的指针
        int data1 = 0;
        int data2 = 0;
        int oper = 0;
        int result = -1;
        String splitJoint = "";//用于多位数字的拼接


        while (index < formula.length()){
        //取出公式中的每一个字符串
            char formulaChar = formula.substring(index,index+1).charAt(0);

            //1、如果是运算符：
            if (operStack.isOperator(formulaChar)){
                if (operStack.isEmpty()){
                    operStack.push(formulaChar);
                }else {
                    //符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中pop出两个数,
                    //再从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    //注意，你自己设置的，1是最高优先级，2反而比1的优先级小，这样导致下面的判断语句要倒着写，以后还是养成习惯，数字越高优先级就越高，这样后续代码好写一点
                    if (operStack.priority(formulaChar) >= operStack.priority(operStack.getOper())){
                        data1 = numStack.pop();
                        data2 = numStack.pop();
                        oper =  operStack.pop();
                        result = numStack.calculate(data1,data2,oper);
                        numStack.push(result);
                        operStack.push(formulaChar);
                    }else {
                        operStack.push(formulaChar);
                    }
                }
            }else {
            //2、如果是数字,则直接入数栈
                //numStack.push(ch - 48); //? "1+3" '1' => 1
                //分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向expression的表达式的index 后再看一位,如果是数就进行扫描，如果是符号才入栈
                //3. 因此我们需要定义一个变量 字符串，用于拼接

                //现在就学会这个思路，字符串拼接，只要用了+且一边有String，另外一边不管是int还是char...都会被转成String
                //字符串直接拼起来！也不用去考虑‘1’的值是不是47...直接拼起来再解析！
                splitJoint+=formulaChar;

                //如果已经到了最后一位，应该直接入数栈
                if (index == formula.length()-1){
                    numStack.push(Integer.parseInt(splitJoint));

                }else {
                    if (operStack.isOperator( formula.substring(index+1,index+2).charAt(0))){//判断下一位是不是运算符
                        //如果遇到的是运算符，那就应该直接入栈
                        //字符串转数字：Integer.parseInt(),现在要记住了
                        numStack.push(Integer.parseInt(splitJoint));
                        //一定要清空
                        splitJoint = "";
                     //如果下一位是数字，那就接着拼接，再进一次循环
                    }
                }
            }
            index++;
        }
        //当表达式扫描完毕，就顺序的从 数栈 和 符号栈 中pop出相应的数和符号，并运行.
        while (!operStack.isEmpty() ){
            data1 = numStack.pop();
            data2 = numStack.pop();
            oper = operStack.pop();
            result = operStack.calculate(data1,data2,oper);
            numStack.push(result);
        }
        result = numStack.pop();

        //学会写printf
        System.out.printf("表达式：%s = %d",formula,result);

    }
//改进之前写的栈，增加一些新功能：判断输入的是字符串还是数字、判断字符串优先级、实现加减乘除运算
class ArrayStack01 {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈，数据就放在该数组
    private int top = -1;// top表示栈顶，初始化为-1

    //构造器
    public ArrayStack01(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     *
     * @return
     */
    public char getOper(){
        return (char) stack[top];
    }


    //出栈-pop, 将栈顶的数据返回
    public int pop() {
        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 判断输入的是否是运算符,注意char 和 int 可以相互转换，char在java中也是数字
     *     Char类型的==时候，不能使用双引号“，只能使用单引号‘，双引号是String，用equals判断
     *     注意8种基本数据类型是用==判断的！
     */
    public boolean isOperator(char operator){
        return operator=='+' || operator == '-' || operator == '*' || operator=='/';
    }



    /**
     * 判断输入运算符的优先级，自己给定规则，
     * @param priority 输入的运算符
     * @return 1是最高优先级
     */
    public int priority(int priority){
        if (priority == '+' || priority == '-'){
            return 2;
        } else if (priority == '*' || priority == '/') {
            return 1;
        }else {//假定现在只有四则运算
            return 3;
        }
    }

    //进行四则运算
    public int calculate(int data1,int data2, int operator){
        int retVal = 0;
        switch (operator){
            case '+' :
                retVal =  data1 + data2;
                break;
            case '-':
                retVal =  data2 - data1;//一定要注意顺序，注意是栈结构
                break;
            case '*':
                retVal = data1 * data2;
                break;
            case '/':
                retVal = data2/data1;//一定要注意顺序，注意是栈结构
                break;
                //如果都不符合则执行default后面的语句后跳出switch语句。
            default:
                break;
        }
        return retVal;
    }

}
}