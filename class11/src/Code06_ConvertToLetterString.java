/**
 * @author : WXY
 * @create : 2022-08-19 19:30
 * @Info :
 * 规定1和A对应，2和B对应，3和C对应、、、
 * 那么一个数字字符串比如“111” 就可以转化为“AAA” 、 “KA” 、“AK”
 * 给定一个只有数字字符组成的字符串str，返回有多少中转换的可能
 *
 *
 */
public class Code06_ConvertToLetterString {
    private static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);

    }

    //str[0...i]已经转化好了， 固定了，
    //i之前的位置，如何转化已经做过决定了， 不用在关心
    //i...有多少种转化的结果
    private static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        if (str[i] == '1') {
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1);
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += process(str, i + 2);
            }
            return res;
        }
        return process(str, i + 1);
    }

    //非递归版本， 没有仔细的看， 直接复制的代码
    public static int dpWays2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N+1];
        dp[N] = 1;
        for(int i = N-1; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
            }
            if (str[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length) {
                    dp[i] += dp[i + 2];
                }
            }
            if (str[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                    dp[i] += dp[i + 2]; // (i和i+1)作为单独的部分，后续有多少种方法
                }
            }
        }
        return dp[0];
    }



    public static void main(String[] args) {
        System.out.println(number("1111"));

    }
}
