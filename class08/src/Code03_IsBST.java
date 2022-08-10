import java.util.ArrayList;

/**
 * @author : WXY
 * @create : 2022-08-10 22:33
 * @Info : 递归套路解决是不是二叉搜索数
 */
public class Code03_IsBST {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBST_1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    private static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }


    //递归套路解决是不是二叉搜索树

    public static boolean isBST_2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.value;
        int max = head.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        boolean isBST = false;
//        if (
//                (leftInfo == null ? true : (leftInfo.isBST && leftInfo.max < head.value))
//                        &&
//                        (rightInfo == null ? true : (rightInfo.isBST && head.value < rightInfo.min))
//        ) {
//            isBST = true;
//        }
        if (leftInfo == null && rightInfo == null) {
            isBST = true;
        }
        if (leftInfo != null && rightInfo != null) {
            if (leftInfo.isBST && rightInfo.isBST) {
                if (rightInfo.min > head.value && head.value > leftInfo.max) {
                    isBST = true;
                }
            }
        }
        if (leftInfo == null && rightInfo != null) {
            if (rightInfo.isBST) {
                if (rightInfo.min > head.value ) {
                    isBST = true;
                }
            }
        }
        if (rightInfo == null && leftInfo != null) {
            if (leftInfo.isBST ) {
                if (head.value > leftInfo.max) {
                    isBST = true;
                }
            }
        }
        return new Info(isBST, min, max);
    }

    public static class Info{
        boolean isBST;
        public int min;
        public int max;

        public Info(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }
    public static void main(String[] args) {
        int maxLevel = 6;
        int maxValue = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST_1(head) != isBST_2(head)) {
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("Finish");

    }
}
