import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;

/**
 * @author : WXY
 * @create : 2022-08-19 20:21
 * @Info :背包问题
 * 背包空间bag ,每个物品都有各自的价值，怎么拿到最大的价值
 */
public class Code07_Knapsack {
    public static int getMaxVlaue(int[] w, int[] v, int bag) {
        return process(w, v, 0, 0, bag);
    }

    //不变：w[] v[] bag
    //index...最大价值
    //0...index-1上做了货物的选择，使得你已经达到的重量是多少alreadyW
    //如果返回-1，认为这个方案不行
    //如果不返回-1，认为这个值是真实的值
    private static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
        if (alreadyW > bag) {
            return  -1;
        }
        //重量没有超
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, alreadyW, bag);
        int p2next = process(w, v, index + 1, alreadyW + w[index], bag);
        int p2 = -1;
        if (p2next != -1) {
            p2 = v[index] + p2next;
        }
        return Math.max(p1, p2);
    }

    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }


    //只剩下rest的空间
    //index....货物自由选择，但是剩余空间不要小于0
    //放回index...货物能够获得的最大价值
    private static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        //rest>=0
        if (index == w.length) {
            return 0;
        }
        //有货也有空间
        int p1 = process(w, v, index + 1, rest);
        int p2next = process(w, v, index + 1, rest-w[index]);
        int p2 = -1;
        if (p2next != -1) {
            p2 = v[index] + p2next;
        }
        return Math.max(p1, p2);
    }

    //非递归写法，没有仔细的看，直接复制的code
    public static int dpWay(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= bag; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= w[index]) {
                    dp[index][rest] = Math.max(dp[index][rest], v[index] + dp[index + 1][rest - w[index]]);
                }
            }
        }
        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(getMaxVlaue(weights, values, bag));
        System.out.println(maxValue(weights,values,bag));
        System.out.println(dpWay(weights,values,bag));
    }
}
