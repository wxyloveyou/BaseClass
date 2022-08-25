import sun.nio.cs.ext.MacDingbat;

import java.nio.file.attribute.GroupPrincipal;

/**
 * @author : WXY
 * @create : 2022-08-25 19:35
 * @Info : 背包问题
 * 动态规划实现方法
 *
 */
public class Code03_Knapsack {

    public static int getMaxValue(int[] w, int[] v, int bag) {
        return process(w, v,0, 0, bag);
    }

    //index.....最大价值
    private static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
        if (alreadyW > bag) {
            return -1;
        }
        //重量没有超
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, alreadyW, bag);
        int p2next = process(w, v, index + 1, alreadyW + w[index], bag);
        int p2 = -1;
        if (p2next != -1) {
            p2 = p2next + v[index];
        }
        return Math.max(p1, p2);
    }

    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }

    // 只剩下rest的空间了，
    // index...货物自由选择，但是不要超过rest的空间
    // 返回能够获得的最大价值
    private static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) { // base case 1
            return  -1;
        }
        //rest >= 0
        if (index == w.length) { //base case2
            return 0;
        }
        //有货物也有空间
        int p1 = process(w, v, index + 1, rest);
        int p2 = -1;
        int p2Next = process(w, v, index + 1, rest - w[index]);
        if (p2Next != -1) {
            p2 = p2Next + v[index];
        }
        return Math.max(p1, p2);
    }

    //改动态规划
    //在maxValue中，有index和rest是可变参数，所以动态规划中有一个二维表
    //index的范围是0....w.length  一共是N+1
    //rest的范围是0...bag  一共是bag+1
    public static int dpWay(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        //dp[N][....] = 0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = -2;
                if (rest - w[index] >= 0) {
                    p2 = dp[index + 1][rest -w[index]] + v[index];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }



    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
    }
}
