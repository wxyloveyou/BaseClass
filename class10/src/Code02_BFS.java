import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author : WXY
 * @create : 2022-08-14 17:55
 * @Info : 宽度优先遍历
 */
public class Code02_BFS {

    // 从node出发，进行宽度优先遍历
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next :
                    cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

}
