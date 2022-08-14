import java.util.*;

/**
 * @author : WXY
 * @create : 2022-08-14 20:22
 * @Info : 拓扑排序
 */
public class Code03_TopologySort {
    public static List<Node> sortedTopoLogy(Graph graph) {
        //key 某一个node
        //value ：剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 剩余入度为0的点，才能进这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();

        for (Node node :
                graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        //拓扑排序的结果，依次加入到result中
        ArrayList<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next :
                    cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (next.in == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
