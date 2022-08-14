import java.util.HashMap;
import java.util.HashSet;

/**
 * @author : WXY
 * @create : 2022-08-14 17:28
 * @Info : 图结构
 */
public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
       nodes = new HashMap<>();
       edges = new HashSet<>();
    }
}
