import java.util.*;

/**
 * @author : WXY
 * @create : 2022-08-14 20:30
 * @Info : Kruskal
 */
public class Code04_Kruskal {
    //并查集
    public static class UnionFind{
        // key 某一个节点， value key节点往上的节点
        private  HashMap<Node,Node> fatherMap;
        // key 某一个集合的代表节点, value key所在集合的节点个数
        private  HashMap<Node,Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node,Integer>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        private  Node findFather(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != fatherMap.get(node)) {
                stack.add(node);
                node = fatherMap.get(node);
            }
            //node来到最高的地方
            while (!stack.isEmpty()) {
                Node cur = stack.pop();
                fatherMap.put(cur,node);
            }
            return node;
        }

        public  boolean isSameSet(Node a, Node b) {
           return findFather(a) == findFather(b);
        }

        public  void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aHead = findFather(a);
            Node bHead = findFather(b);
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node big = aSetSize >= bSetSize ? aHead : bHead;
                Node small = big == aHead ? bHead : aHead;
                fatherMap.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }

        }

    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        //将每条边放入小根堆中，按照边权重的方式放
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge :
                graph.edges) {
            priorityQueue.add(edge);
        }
        HashSet<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }

}
