import java.util.ArrayList;

/**
 * @author : WXY
 * @create : 2022-08-11 20:16
 * @Info : 最大搜索二叉子树的头节点
 */
public class Code05_MaxSubBSTHead {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    private static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right,arr);
    }

    public static Node maxSubBSTHead_1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead_1(head.left);
        Node rightAns = maxSubBSTHead_1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }


    //递归套路解决
    public static class Info{
        public Node maxSubBSTHead;
        public int maxSubBSTSize;
        public int min;
        public int max;

        public Info(Node maxSubBSTHead, int maxSubBSTSize, int min, int max) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }
    }

    public static Node maxSubBSTHead_2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).maxSubBSTHead;
    }

    private static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int min = x.value;
        int max = x.value;
        int maxSubBSTSize = 0;
        Node maxSubSBSTHead = null;

        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            maxSubBSTSize = leftInfo.maxSubBSTSize;
            maxSubSBSTHead = leftInfo.maxSubBSTHead;
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            if (rightInfo.maxSubBSTSize > maxSubBSTSize) {
                maxSubBSTSize = rightInfo.maxSubBSTSize;
                maxSubSBSTHead = rightInfo.maxSubBSTHead;
            }
        }
        if (
                (leftInfo == null ? true : (leftInfo.maxSubBSTHead == x.left && leftInfo.max < x.value))
                        && (rightInfo == null ? true : (rightInfo.maxSubBSTHead == x.right && x.value < rightInfo.min))
        ) {
            maxSubSBSTHead = x;
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) +
                    (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
        }
        return new Info(maxSubSBSTHead, maxSubBSTSize, min, max);
    }

    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generatr(1, maxLevel, maxValue);
    }

    private static Node generatr(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generatr(level + 1, maxLevel, maxValue);
        head.right = generatr(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 6;
        int maxValue = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead_1(head) != maxSubBSTHead_2(head)) {
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("Finish!!!");
    }

}
