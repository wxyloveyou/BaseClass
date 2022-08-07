import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author WXY
 * @create 2022-07-30 12:00
 * 双链表改装，讲双端队列改装成栈
 */
public class Code03_DoubleEndsQueueToStackAndQueue {
    public static class Node { //可以改泛型 public static class Node<T>
        public int value; //public <T> value  泛型的写法，将int改<T> 下面同理
        public Node last; //上一个的，前节点
        public Node next; //下一个，后节点

        public Node(int value) {
            this.value = value;
        }
    }
    //双链表组成双端队列
    public static class DoubleEndsQueue{
        public Node head;  //头节点
        public Node tail;  //尾节点

        public void addFromHead(int value) {
            Node cur = new Node(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }
        //从尾部加数据
        public void addFromTail(int value) {
            Node cur = new Node(value);
            if (head == tail) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        //从头部出队列
        public int popFromHead() {
            if (head == null) {
                return -1;
            }
            Node cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            return cur.value;
        }

        //从尾部出队列
        public int popFromTail() {
            if (head == null) {
                return -1;
            }
            Node cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                cur.last = null;
                tail.next = null;
            }
            return cur.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    //由双链表模拟栈结构
    public static class MyStack {
        private DoubleEndsQueue queue;

        public MyStack() {
            queue = new DoubleEndsQueue();
        }

        public void push(int value) {
            queue.addFromHead(value);
        }

        public int pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    //用双链表模拟队列结构
    public static class MyQueue {
        private DoubleEndsQueue queue;

        public MyQueue(){
            queue = new DoubleEndsQueue();
        }

        public void push(int value) {
            queue.addFromHead(value);
        }

        public int poll() {
            return queue.popFromTail();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            MyStack myStack = new MyStack();
            MyQueue myQueue = new MyQueue();
            Stack stack = new Stack();
            Queue queue = new LinkedList();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * (value));
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                }else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!stack.pop().equals(myStack.pop())) {
                            System.out.println("oops");
                        }
                    }
                }

                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                          if (!queue.poll().equals(myQueue.poll())){
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
