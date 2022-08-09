import javax.sound.sampled.Line;

/**
 * @author : WXY
 * @create : 2022-08-09 22:30
 * @Info : 是否是平衡二叉树
 */
public class Code01_IsBalanced {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBalance1(Node head) {
        boolean ans;
        ans = true;
        process1(head, ans);
        return ans;
    }

    private static int process1(Node head, boolean ans) {
        if (head == null || !ans) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans = false;
        }
        return Math.max(leftHeight,rightHeight) + 1;
    }

    public static class Info{
        public boolean isBalaced;
        public int height;

        public Info(boolean isBalaced, int height) {
            this.isBalaced = isBalaced;
            this.height = height;
        }
    }

    public static boolean isBalanced2(Node head) {
        return process2(head).isBalaced;
    }


    private static Info process2(Node x) {
        if (x == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process2(x.left);
        Info rightInfo = process2(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftInfo.isBalaced || !rightInfo.isBalaced || Math.abs(leftInfo.height - rightInfo.height) > 1) {
          isBalanced = false;
        }
        return new Info(isBalanced, height);
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = Util_class08.generateRandomBST(maxLevel, maxValue);
            if (isBalance1(head) != isBalance1(head)) {
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("Finish");
    }

}
