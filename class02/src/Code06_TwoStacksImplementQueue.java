import com.sun.jmx.remote.internal.ClientCommunicatorAdmin;

import java.util.Stack;

/**
 * @author : WXY
 * @create : 2022-07-30 17:04
 * @Description : 用两个栈来实现队列操作
 *
 */
public class Code06_TwoStacksImplementQueue {

    public static class TwostacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        //构造函数
        public TwostacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        //push栈向pop栈倒入数据
        public void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int pushInt) {
            stackPush.push(pushInt);
            pushToPop();
        }

        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
        TwostacksQueue test = new TwostacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
