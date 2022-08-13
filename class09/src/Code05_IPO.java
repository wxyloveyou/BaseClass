import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author : WXY
 * @create : 2022-08-13 17:34
 * @Info : 贪心算法
 * 贪心算法的解题套路实战
 * 输入:正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明:每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出:你最后获得的最大钱数。
 */
public class Code05_IPO {

    public static class Program{
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostCompartor implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitCompararot implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        //最小花费以小根堆的方式存放，以写好的方式排序存放
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostCompartor());
        //最大的利润，在大根堆里面存放，排序方式已经写好了
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitCompararot());

        //首先建立好所有的项目
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Profits[i], Capital[i]));
        }

        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.peek().p;
        }
        return W;
    }



}
