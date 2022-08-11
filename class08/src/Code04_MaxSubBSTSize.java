import javax.sound.midi.SoundbankResource;
import java.util.ArrayList;

/**
 * @author : WXY
 * @create : 2022-08-11 19:09
 * @Info : 递归套路解决搜索二叉数的maxSize
 */
public class Code04_MaxSubBSTSize {
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
        in(head.right, arr);
    }


    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }


    public static class Info{
        public boolean isAllBST;
        public int maxSubBSTSize;
        public int min;
        public int max;

        public Info(boolean isAllBST, int maxSubBSTSize, int min, int max) {
            this.isAllBST = isAllBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }
    }

    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSubBSTSize;
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int min = x.value;
        int max = x.value;

        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        boolean isAllBST = false;
        int maxSubBSTSize = 0;

        if (leftInfo != null) {
            maxSubBSTSize = Math.max(maxSubBSTSize, leftInfo.maxSubBSTSize);
        }
        if (rightInfo != null) {
            maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
        }

        if (
            //左树整体是搜索二叉树
                (leftInfo == null ? true : leftInfo.isAllBST) &&
                        (rightInfo == null ? true : rightInfo.isAllBST) &&
                        //左树的max < x.max
                        (leftInfo == null ? true : leftInfo.max < x.value) &&
                        (rightInfo == null ? true : x.value < rightInfo.min)
        ) {
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) +
                    (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
            isAllBST = true;
        }
        return new Info(isAllBST, maxSubBSTSize, min, max);

    }

    //for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node node = new Node((int) (Math.random() * maxValue));
        node.left = generate(level + 1, maxLevel, maxValue);
        node.right = generate(level + 1, maxLevel, maxValue);
        return node;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTiems = 1000000;
        for (int i = 0; i < testTiems; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("Finish!");
    }



}
