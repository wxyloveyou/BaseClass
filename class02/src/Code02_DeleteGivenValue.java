import javax.swing.*;

/**
 * @author WXY
 * @create 2022-07-30 11:26
 * 删除指定的值的节点
 */
public class Code02_DeleteGivenValue {
    public static class Node{
        public int value;
        public Node next;

        public Node(int date) {
           value = date;
        }
    }

    public static Node removeValue(Node head, int num) {
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int len = 30;
        int value = 10;
        int testTime = 1000;
        for (int i = 0; i < testTime; i++) {
            Node node1 = generateRandomLinkedList(len, value);
            printList(node1);
            Node node2 = removeValue(node1, 7);
            printList(node2);
        }
        System.out.println("finish!!!");
    }

    private static void printList(Node node) {
        if (node == null) {
            System.out.println("List is empty");
        }
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    private static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

}
