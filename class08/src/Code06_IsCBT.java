import java.util.LinkedList;

/**
 * @author : WXY
 * @create : 2022-08-11 21:09
 * @Info : 递归套路判断是不是完全二叉树
 */
public class Code06_IsCBT {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public static boolean isCBT_1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                //如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null)) || (l == null && r != null)
            ) {

                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    //递归套路
    public static class Info{
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }
    public static boolean isCBT_2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    private static Info process(Node x) {
        if (x == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;

        boolean isBCT = false;
        if (isFull) {
            isBCT = true;
        } else {
            if (leftInfo.isCBT && rightInfo.isCBT) {
                if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isBCT = true;
                }
                if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isBCT = true;
                }
                if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isBCT = true;
                }
            }
        }
        return new Info(isFull, isBCT, height);
    }

    public static Node generateRandomCBT(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTiems = 1000;
        for (int i = 0; i < testTiems; i++) {
            Node head = generateRandomCBT(maxLevel, maxValue);
            if (isCBT_1(head) != isCBT_2(head)) {
                System.out.println("BUG BUG BUG!");
            }
        }
        System.out.println("Finish!!!");
    }
}
