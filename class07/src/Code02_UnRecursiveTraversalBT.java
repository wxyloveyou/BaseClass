import com.sun.corba.se.spi.orbutil.closure.Closure;

import java.util.Stack;

/**
 * @author : WXY
 * @create : 2022-08-07 17:20
 * @Info : 非递归方式遍历二叉树
 *
 */
public class Code02_UnRecursiveTraversalBT {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //非递归先序遍历
    public static void pre(Node head) {
        System.out.println("pre-order : ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.add(head.right);
                }
                if (head.left != null) {
                    stack.add(head.left);
                }
            }
        }
        System.out.println();
    }


    //非递归中序遍历
    public static void in(Node head) {
        System.out.println("in-order");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.add(head);
                    head = head.left;
                } else {
                   head = stack.pop();
                   System.out.print(head.value + " ");
                   head = head.right;
                }
            }
        }
        System.out.println();
    }

    //非递归后序遍历
    public static void pos1(Node head) {
        System.out.println("pos-order");
        if (head != null) {
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.add(head);
            while (!stack1.isEmpty()) {
                head = stack1.pop();
                stack2.add(head);
                if (head.left != null) {
                    stack1.add(head.left);
                }
                if (head.right != null) {
                    stack1.add(head.right);
                }
            }
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().value + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
    }
}
