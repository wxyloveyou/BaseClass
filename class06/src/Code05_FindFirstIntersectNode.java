/**
 * @author : WXY
 * @create : 2022-08-07 14:53
 * @Info : 两条链表相交问题
 * 不能判断链表是否有环，判断链表是否相交，
 */
public class Code05_FindFirstIntersectNode {
    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    //找到链表第一个入环节点，若无环，返回null。
    //如果有环，则只能是有一个环，因为一个节点只有一个next指针，入了环，不可能再出环
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        //n1 慢指针，n2快指针
        //快指针一次走两步，慢指针一次走一步， 如果有环，则，
        //在环内，n1 和 n2 必然相遇，因为n2跑的比n1快。第一次相遇后n2来到头部，重新开始走
        //则第二次相交的位置就是入环的位置，返回n1 n2都可以
        Node n1 = head.next;
        Node n2 = head.next.next;
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; // 快指针从新来到头部 ,一次一步的走
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    //如果两个链表都无环，返回第一个相交的节点，不相交则返回null
    //若两个链表相交，只能有一个相交的节点，必然会共用一部分的链表，因为node只有一个next指针
    public static Node noloop(Node head1, Node head2) {
        if (head1 == null || head1 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0; //得到head1 和 head2链表的差值
        while (cur1.next != null) {
            n++; // 得到cur1有多少个节点
            cur1 = cur1.next;
        }
        //cur1来到最后一个节点
        while (cur2.next != null) {
            n--; // cur1 比 cur2 长多少，还是短多少
            cur2 = cur2.next;
        }
        //cur2来到最后一个节点
        if (cur1 != cur2){
            // cur1 和 cur2 都来到了最后一个节点，要是不相等，则不然不相交。
            //因为相交则必然会从交点往后共用一段链表
            return null;
        }
        // n  :  链表1长度减去链表2长度的值
        cur1 = n > 0 ? head1 : head2;  //谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1; //谁短，谁的头变成cur2
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //两个有环的链表相交，返回第一个相交节点。不相交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        if (loop1 == null && loop2 == null) {
            return noloop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        int v = getIntersectNode(head1, head2).value;
        System.out.println(v);

    }
}
