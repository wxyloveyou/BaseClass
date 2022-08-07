import jdk.nashorn.internal.IntDeque;

import javax.print.DocFlavor;
import java.util.Stack;

/**
 * @author : WXY
 * @create : 2022-07-30 16:31
 * @Description :获取添加到栈中的最小值，
 */
public class Code05_GetMinStack {

    public static class MyStack1{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;


        public MyStack1(){
            stackData = new Stack<Integer>();
            stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (stackMin.isEmpty()) {
                stackMin.push(newNum);
            } else if (newNum <= getmin()) {
                stackMin.push(newNum);
            }
            stackData.push(newNum);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("栈空了，不能再取了");
            }
            int value = stackData.pop();
            if (value == getmin()) {
                stackMin.pop();
            }
            return value;
        }

        public int getmin(){
            if (stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return stackMin.peek();
        }
    }

    public static class MyStack2{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2(){
            stackData = new Stack<Integer>();
            stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (stackMin.isEmpty()) {
                stackMin.push(newNum);
            } else if (newNum < getmin()) {
                stackMin.push(newNum);
            } else {
                //peek()函数，取出栈顶元素，但是不出栈。
                //这代码的意思是：如果即将入栈的数比已经入栈的最小值大，
                //那么，将satckMin栈的top元素(也就是最小值)再加入到min栈中，
                //保持data栈和min栈平衡，就是元素的个数相同
                int newMin = stackMin.peek();
                stackMin.push(newMin);
            }
            stackData.push(newNum);
        }

        public int pop(){
            if (stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            stackMin.pop();
            return stackData.pop();
        }

        public int getmin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(2);
        System.out.println(stack1.getmin());
        stack1.push(3);
        System.out.println(stack1.getmin());
        stack1.push(1);
        System.out.println(stack1.getmin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getmin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getmin());
        stack2.push(4);
        System.out.println(stack2.getmin());
        stack2.push(1);
        System.out.println(stack2.getmin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getmin());
    }
}
