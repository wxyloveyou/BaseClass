import com.sun.javafx.geom.Edge;

import java.util.ArrayList;

/**
 * @author : WXY
 * @create : 2022-08-14 17:15
 * @Info : 点结构
 */
//点结构的描述 A  0
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
