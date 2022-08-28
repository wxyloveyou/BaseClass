import com.sun.prism.PresentableState;
import com.sun.xml.internal.ws.assembler.jaxws.MustUnderstandTubeFactory;

/**
 * @author : WXY
 * @create : 2022-08-25 21:49
 * @Info : 硬币问题
 * 给定一组纸币，，有若干种面额，每一种面额有无穷多张
 * 给定一个目标钱数，
 * 用这无穷多张的面值的纸币，面值有固定的几种，每种可以使用无穷张，来组成该目标钱数
 * 返回最小的使用张数
 *
 */
public class Code09_CoinsWay {

    // arr中都是正数且无重复值，返回组成aim的方法数
    public static int way1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        //rest > 0
        int way = 0;
        for (int zhang = 0; zhang * arr[index] <= rest ; zhang++) {
            way += process1(arr, index + 1, rest - (zhang * arr[index]));
        }
        return  way;
    }

    public static int way2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return  0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        // 一开始所有的过程，都没有计算呢
        // dp[..][..]  = -1
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(arr, 0, aim, dp);
    }

    //如果index和rest的参数组合，是没有算过的，dp[index][rest] == -1;
    //如果index和rest的参数组合，是算过的，dp[index][rest] > -1;
    private static int process2(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (index == arr.length) {
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }

        int way = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            way += process1(arr, index + 1, rest - (zhang * arr[index]));
        }
        dp[index][rest] = way;
        return  way;
    }

    public static int way3_1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return  0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;//dp[N][1......aim] = 0;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int way = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    way += dp[index + 1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = way;
            }
        }
        return dp[0][aim];
    }

    public static int way3_2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return  0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int way = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    way += dp[index + 1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = way;
            }
        }
        return dp[0][aim];
    }

    public static int way4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return  0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = { 5, 10,50,100 };
        int sum = 1000;
        System.out.println(way1(arr,sum));
        System.out.println(way2(arr,sum));
        System.out.println(way3_1(arr,sum)); //
        System.out.println(way3_2(arr,sum));
        System.out.println(way4(arr,sum));
    }
}


