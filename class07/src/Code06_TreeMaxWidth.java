import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : WXY
 * @create : 2022-08-09 20:28
 * @Info :  查找二叉树最大宽度
 * 就是找各个层上最大的节点
 */
public class Code06_TreeMaxWidth {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    public static int maxWidthUseMap(Node head){
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        //key 在哪一层 ，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1;//当前你正在统计哪一层的宽度
        int curLecelNodes = 0;//当前层是curLevel层，目前宽度是多少
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLecelNodes++;
            }else {
                max = Math.max(curLecelNodes, max);
                curLevel++;
                curLecelNodes = 1;
            }
        }
        max = Math.max(max, curLecelNodes);
        return max;
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; //当前层，最右节点是谁
        Node nextEnd = null;//下一层，最右节点是谁
        int max = 0;
        int curLevelNodes = 0;//当前层的节点数
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            Node head = Util_class07.generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Failed Failed Fialed");
            }
        }
        System.out.println("Finish!");
    }



}
