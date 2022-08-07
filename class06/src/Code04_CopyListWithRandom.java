import javax.sound.midi.SoundbankResource;
import java.util.HashMap;

/**
 * @author : WXY
 * @create : 2022-08-06 22:02
 * @Info : 完整的复制一个链表，
 * 链表(两个指针，一个next，一个random)的另一个指针是随意指向，
 */
public class Code04_CopyListWithRandom {
    public static class Node{
        public int value;
        public Node next;
        public Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    //方法1：

    /**
     * 一个老节点克隆一个新的节点，新节点1怎么指向新节点2 ，根据老节点1指向老节点2，
     * 老节点2查map 得到新节点2，依次进行下去即可
     * @param head
     * @return
     */
    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            //cur 老  key
            //map.get(cur) 新
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        //克隆每一个节点，插入到老节点的后面
        while (cur != null) {
            //cur 老，next 老的下一个
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next; // 简单理解就是将上一步新建立的节点的next指向原来老的下一个
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        //设置克隆节点的rand指针
        // 1 -> 1` -> 2 -> 2`
        while (cur != null) {
            //cur 老节点
            //cur.neex 新节点，copy的节点
            next = cur.next.next;//记录原来老的下一个节点
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        //head head.next
        Node res = head.next;
        cur = head;
        //切断
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.println("order: ");
        while (cur != null) {
            System.out.println(cur.value );
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        while (cur != null) {
            System.out.println(cur.rand == null ? "-" : cur.rand.value + " ");
            cur = cur.rand;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4


        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
