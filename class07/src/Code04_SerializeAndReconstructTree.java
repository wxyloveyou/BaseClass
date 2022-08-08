import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author : WXY
 * @create : 2022-08-08 20:14
 * @Info : 二叉树的序列化和反序列化
 *
 *  二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
 *  以下代码全部实现了。
 *  但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 *  因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 *  比如如下两棵树
 *        __2
 *       /
 *       1
 *      和
 *         1__
 *            \
 *            2
 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 *
 */
public class Code04_SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //前序遍历方式序列化
    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }

    }

    //中序方式序列化
    public static Queue<String> inSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        ins(head, ans);
        return ans;
    }

    private static void ins(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        }else {
            ins(head.left, ans);
            ans.add(String.valueOf(head.value));
            ins(head.right, ans);
        }
    }

    //后序方式序列化
    public static Queue<String> posSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pos(head, ans);
        return ans;
    }

    private static void pos(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            pos(head.left, ans);
            pos(head.right, ans);
            ans.add(String.valueOf(head.value));
        }
    }

    //前序遍历反序列化
    public static Node bulidBypreQueue(Queue<String> prelist) {
        if (prelist == null || prelist.size() == 0) {
            return null;
        }
        return prebulid(prelist);
    }

    private static Node prebulid(Queue<String> prelist) {
        String value = prelist.poll();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = prebulid(prelist);
        head.right = prebulid(prelist);
        return head;
    }

    //后序遍历反序列化
    public static Node bulidByPosQueue(Queue<String> poslist) {
        if (poslist == null || poslist.size() == 0) {
            return  null;
        }
        //左右中 --- stack(中 右 左）
        Stack<String> stack = new Stack<>();
        while (!poslist.isEmpty()) {
            stack.push(poslist.poll());
        }
        return posbulid(stack);
    }

    private static Node posbulid(Stack<String> stack) {
        String value = stack.pop();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.right = posbulid(stack);
        head.left = posbulid(stack);
        return head;
    }

    //层次遍历方式序列化
    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                } else {
                    ans.add(null);
                }
                if (head.right != null) {
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    //层次遍历反序列化
    public static Node bulidByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<Node>();
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    private static Node generateNode(String poll) {
        if (poll == null) {
            return null;
        }
        return new Node(Integer.valueOf(poll));
    }

    //for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    //for test
    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    //for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    //for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree");
        printInOrder(head, 0, "H", 17);
    }

    private static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "V", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        printInOrder(head.left, height + 1, "^", len);
    }

    private static String getSpace(int num) {
        String space = " ";
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < num ; i++) {
            buffer.append(space);
        }
        return buffer.toString();
    }


    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 100000;
        System.out.println("test Begin：");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBulid = bulidBypreQueue(pre);
            Node posBulid = bulidByPosQueue(pos);
            Node levelBuil = bulidByLevelQueue(level);
            if (!isSameValueStructure(preBulid, posBulid) ) {
                System.out.println("Oops1!");
            }
            if (!isSameValueStructure(preBulid, levelBuil)) {
                System.out.println("Oops2!");
            }
        }
        System.out.println("test End!");
    }

}
