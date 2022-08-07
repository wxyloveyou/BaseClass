/**
 * @author : WXY
 * @create : 2022-08-04 21:47
 * @Description :  链表做partition
 * 将单向链表按某值划分成左边小，中间相等，右边大的形式
 *
 */
public class Code03_SmallerEqualBigger {
    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node listPartition1(Node head,int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartion(nodeArr, pivot);
        for (i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];

    }
    private static void arrPartion(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value > pivot) {
                swap(nodeArr, --big, index);
            } else {
                index++;
            }
        }

    }
    private static void swap(Node[] nodeArr, int i, int j) {
        Node temp = nodeArr[i];
        nodeArr[i] = nodeArr[j];
        nodeArr[j] = temp;
    }


    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;  //小于的head
        Node sT = null;  //小于的tail
        Node eH = null;  //相等的head
        Node eT = null;  //相等的tail
        Node mH = null;  //大于的head
        Node mT = null;  //大于的tail
        Node next = null; //保留下一个节点

        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        //将三个部分串在一起，
        //小于区域的尾巴连接等于区域的头，等于区域的尾巴连接大于区域的头
        if (sT != null) {
            sT.next = eH;
            eT = (eT != null)? eT : sT;
        }
        //上的if有没有执行，接下来连接的都是eT;
        if (eT != null) {
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(Node node) {
        System.out.println("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);

//        head1 = listPartition1(head1, 5);
//        printLinkedList(head1);
        head1 = listPartition2(head1, 8);
        printLinkedList(head1);
    }

}
