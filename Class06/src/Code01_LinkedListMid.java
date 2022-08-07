import com.sun.org.apache.xml.internal.security.c14n.helper.AttrCompare;
import org.omg.CORBA.NO_IMPLEMENT;

import java.util.ArrayList;

/**
 * @author : WXY
 * @create : 2022-08-04 19:57
 * @Description :  单链表中点问题
 * 快慢指针：
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 */
public class Code01_LinkedListMid {
    public static class Node{
        public int value;;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    //head 头节点 ,这个方法问题，偶数个返回下中点 ,已改正
    //返回中点和上中点
    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        //链表有三个点，或者以上
        Node slow = head;
        Node fast = head;
        //以下是原代码，有问题，返回的是下中点
//        Node slow = head;
//        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    //返回中点或者下中点
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() / 2));
    }

    //奇数返回中点前一个，偶数返回上中点前一个
    public static Node midOrUpmidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node right3(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arrayList = new ArrayList<>();
        while (cur != null) {
            arrayList.add(cur);
            cur = cur.next;
        }
        return arrayList.get((arrayList.size() - 3) / 2);
    }


    //奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null ) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arrayList = new ArrayList<>();
        while (cur != null) {
            arrayList.add(cur);
            cur = cur.next;
        }
        return arrayList.get((int)(arrayList.size() / 2)  - 1);
    }


    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);
//        test.next.next.next.next.next.next.next.next.next = new Node(9);
//        test.next.next.next.next.next.next.next.next.next.next = new Node(10);
//        test.next.next.next.next.next.next.next.next.next.next.next = new Node(11);
//        test.next.next.next.next.next.next.next.next.next.next.next.next = new Node(12);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
        System.out.println("=======================================");

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
        System.out.println("=======================================");

        ans1 = midOrUpmidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
        System.out.println("=======================================");


        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
        System.out.println("=======================================");



    }
}
