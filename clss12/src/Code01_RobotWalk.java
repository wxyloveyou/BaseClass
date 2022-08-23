import java.util.ConcurrentModificationException;

/**
 * @author : WXY
 * @create : 2022-08-23 21:34
 * @Info : 机器人走路问题
 * N M K P
 * 一共有N个位置，从M点出发，还剩下K步，到达P位置
 * N个位置 0...N  一共有N+1个位置。
 */
public class Code01_RobotWalk {

    public static int way1(int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        return walk1(N, M, K, P);
    }

    //N：位置为1~N，固定参数
    //cur: 当前在cur位置，可变参数
    //rest:还剩下rest步没有走，可变参数
    //P：最终的目标位置是P，固定参数
    //函数的含义：只能在1~N这些位置上移动，当前位置是cur,走完rest步后到达P位置的方法数作为返回值
    private static int walk1(int N, int cur, int rest, int P) {
        //如果没有了步数了， 当前位置cur就是最后的位置
        //如果最后的位置停在了P位置，那么之前走的移动都是有效的，
        //如果最后的位置不是P位置，那么之前走的移动都是无效的。
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        //如果还有rest步要走，而当前位置是1，那么只能从1走向2
        if (cur == 1) {
            return walk1(N, 2, rest - 1, P);
        }
        //如果还有rest步要走，而当前位置是N，那么只能从N-1走向N-2
        if (cur == N) {
            return walk1(N, N - 1, rest - 1, P);
        }
        //如果还有rest步要走，而当前位置是cur在中间位置,那么当前可以走向左，或者走向右
        //走向左之后的后续过程是，来到cur-1位置，还剩下rest-1步走
        //走向右之后的后续过程是，来到cur-1位置，还剩下rest-1步走
        //走向左和走向右是截然不同的方法，所以总方法数都要算上
        return walk1(N, cur - 1, rest - 1, P) + walk1(N, cur + 1, rest - 1, P);
    }

    //前提，有足够的重复计算，否则没有改的必要
    //记忆性搜索，就是用一个结构将每次递归的结果存储下来，下次再重复递归这个结果可以直接从这个结构中取值，
    public static int way2(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= K; col++) {
                dp[row][col] = -1;
            }
        }
        return walk2_Cache(N, M, K, P, dp);
    }
    // HashMap<String, Integer>   (19,100)  "19_100"
    // 我想把所有cur和rest的组合，返回的结果，加入到缓存里
    private static int walk2_Cache(int N, int cur, int rest, int P, int[][] dp) {
        //dp中有的数据可以直接返回，不需要再进行递归查找了，
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        if (rest == 0) {
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }
        if (cur == 1) {
            dp[cur][rest] = walk2_Cache(N, 2, rest - 1, P,dp);
            return dp[cur][rest];
        }
        if (cur == N) {
            dp[cur][rest] = walk2_Cache(N, N - 1, rest - 1, P,dp);
            return dp[cur][rest];
        }
        dp[cur][rest] = walk2_Cache(N, cur - 1, rest - 1, P, dp) +
                walk2_Cache(N, cur + 1, rest - 1, P, dp);
        return dp[cur][rest];
    }

    //将暴力递归改动态规划
    public static int way3(int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1]; //初始化的时候都是0,
        dp[P][0] = 1;
//        if (rest == 0) {
//            dp[cur][rest] = cur == P ? 1 : 0;    递归中这个0,不用单独设置
//            return dp[cur][rest];
//        }
        for (int row = 0; row <= N; row++) {
            for (int col = 1; col <= K; col++) {
                if (row == 1) {
                    dp[row][col] = dp[2][col - 1];
                }
                if (row == N) {
                    dp[row][col] = dp[N - 1][col - 1];
                }
                if (row != 0 && row != N) {
                    dp[row][col] = dp[row + 1][col - 1] + dp[row - 1][col - 1];
                }
            }
        }

//
//        if (rest == 0) {
//            return cur == P ? 1 : 0;
//        }
//        //如果还有rest步要走，而当前位置是1，那么只能从1走向2
//        if (cur == 1) {
//            return walk1(N, 2, rest - 1, P);
//        }
//        //如果还有rest步要走，而当前位置是N，那么只能从N-1走向N-2
//        if (cur == N) {
//            return walk1(N, N - 1, rest - 1, P);
//        }
//        //如果还有rest步要走，而当前位置是cur在中间位置,那么当前可以走向左，或者走向右
//        //走向左之后的后续过程是，来到cur-1位置，还剩下rest-1步走
//        //走向右之后的后续过程是，来到cur-1位置，还剩下rest-1步走
//        //走向左和走向右是截然不同的方法，所以总方法数都要算上
//        return walk1(N, cur - 1, rest - 1, P) + walk1(N, cur + 1, rest - 1, P);
        return dp[M][K];


    }





    public static void main(String[] args) {
        System.out.println(way1(7, 4, 9, 5));
        System.out.println(way2(7, 4, 9, 5));
        System.out.println(way3(7, 4, 9, 5));
    }

}

