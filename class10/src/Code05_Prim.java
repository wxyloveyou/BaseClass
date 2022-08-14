import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author : WXY
 * @create : 2022-08-14 21:26
 * @Info : Prim
 */
public class Code05_Prim {
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        //解锁的边进入一个小根堆中
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        //解锁的点进入到一个set中
        HashSet<Node> nodeSet = new HashSet<>();
        //依次挑选的边在result里面，最后返回的
        HashSet<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            //node 是开始点
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge :
                        node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    Edge cur = priorityQueue.poll();//弹出解锁边中最小的一个边
                    Node toNode = cur.to;//可能的一个新的点(待解锁的)
                    if (!nodeSet.contains(toNode)) {
                        nodeSet.add(toNode);
                        result.add(cur);
                        for (Edge edge :
                                toNode.edges) {
                            priorityQueue.add(edge);
                        }
                    }
                }
            }
            break;
        }
        return result;
    }


    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和


}
