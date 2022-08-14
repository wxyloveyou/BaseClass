/**
 * @author : WXY
 * @create : 2022-08-14 17:18
 * @Info : 边结构
 */
//边的描述
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
