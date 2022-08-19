import java.util.Stack;

/**
 * @author : WXY
 * @create : 2022-08-19 19:05
 * @Info :反转一个栈，使用递归方式
 */
public class Code04_ReverseStackUsingRecursive {
    //依靠递归函数有记录当前信息的能力，
    public static void reverse(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    //依靠递归函数有记录当前信息的能力
    public static int f(Stack<Integer> stack) {
        int relust = stack.pop();
        if (stack.isEmpty()) {
            return relust;
        } else {
            int last = f(stack);
            stack.push(relust);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
