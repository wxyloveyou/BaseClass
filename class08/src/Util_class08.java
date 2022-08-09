/**
 * @author : WXY
 * @create : 2022-08-09 21:20
 * @Info :
 */
public class Util_class08 {
    public static class Node extends Code01_IsBalanced.Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            super(value);
        }
    }
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.right = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }
}
