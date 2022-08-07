import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author : WXY
 * @create : 2022-07-30 17:32
 * @Description : 用两个队列来实现栈结构
 */
public class Code07_TwoQueueImplementStack {

    public static class TwoQueueStack {
        public Queue queue;
        public Queue help;

        public TwoQueueStack() {
            queue = new LinkedList();
            help = new LinkedList();
        }

        public void push(int value) {
            queue.offer(value);
        }

        public int poll() {
            while (queue.size() > 1) {  // 将queue中的数据转移到help中，剩下一个， 用来给放回值
                help.offer(queue.poll());
            }
            int ans = (int) queue.poll();
            Queue temp = queue;
            queue = help;
            help = temp;
            return ans;
        }

        public int peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            int ans = (int) queue.poll();
            help.offer(ans);
            Queue temp = queue;
            queue = help;
            help = temp;
            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("Test Begin!!!");
        TwoQueueStack stack = new TwoQueueStack();
        Stack test = new Stack();
        int testTime = 10000;
        int max = 1000;
        for (int i = 0; i < testTime; i++) {
            if (stack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("oops1");
                }
                int num = (int) (Math.random() * max);
                stack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    stack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!test.peek().equals(stack.peek())) {
                        System.out.println("oops2");
                    }
                } else if (Math.random() < 0.75) {
                    if (!test.pop().equals(stack.poll())) {
                        System.out.println("oops3");
                    }
                } else {
                    if (stack.isEmpty() != test.isEmpty()) {
                        System.out.println("oops4");
                    }
                }
            }

        }
        System.out.println("Test Finaish!");
    }

}
