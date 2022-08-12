import sun.util.resources.cldr.bn.CurrencyNames_bn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author : WXY
 * @create : 2022-08-12 21:33
 * @Info : 递归套路解决两个节点的最低公共父节点
 */
public class Code07_lowestAncestor {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node lowestAncestor_1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        //key的父节点是value;
        HashMap<Node, Node> parnetMap = new HashMap<>();
        parnetMap.put(head, null);
        fillParentMap(head, parnetMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parnetMap.get(cur) != null) {
            cur = parnetMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parnetMap.get(cur);
        }
        return cur;

    }

    private static void fillParentMap(Node head, HashMap<Node, Node> parnetMap) {
        if (head.left != null) {
            parnetMap.put(head.left, head);
            fillParentMap(head.left, parnetMap);
        }
        if (head.right != null) {
            parnetMap.put(head.right, head);
            fillParentMap(head.right, parnetMap);
        }
    }

    public static class Info{
        public Node ans;
        public boolean findO1;
        public boolean findO2;

        public Info(Node ans, boolean findO1, boolean findO2) {
            this.ans = ans;
            this.findO1 = findO1;
            this.findO2 = findO2;
        }
    }

    public static Node lowestAncestor_2(Node head, Node o1, Node o2) {
        return process2(head, o1, o2).ans;
    }

    private static Info process2(Node x, Node o1, Node o2) {
        if (x == null) {
            return new Info(null,false,false);
        }
        Info leftInfo = process2(x.left, o1, o2);
        Info rightInfo = process2(x.right, o1, o2);

        boolean findO1 = x == o1 || leftInfo.findO1 || rightInfo.findO1;
        boolean findO2 = x == o2 || leftInfo.findO2 || rightInfo.findO2;

        //O1 和 O2 最初在哪里交汇了
        //1、在左树上提前交汇了，
        //2、在右树上提前交汇了，
        //3、没有在左树、右树上提前交汇，但是O1 和 O2全了
        Node ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        }
        if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        }
        if (ans == null) {
            if (findO1 && findO2) {
                ans = x;
            }
        }
        return new Info(ans, findO1, findO2);
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


    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }



    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor_1(head, o1, o2) != lowestAncestor_2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
