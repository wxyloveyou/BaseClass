/**
 * @author : WXY
 * @create : 2022-08-25 20:22
 * @Info : class11_code06动态规划版本
 *
 * 规定1和A对应，2和B对应，3和C对应、、、
 * 那么一个数字字符串比如“111” 就可以转化为“AAA” 、 “KA” 、“AK”
 * 给定一个只有数字字符组成的字符串str，返回有多少中转换的可能
 */
public class ConvertToLetterString {
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    private static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        // 遇到1 有两种情况，1单独取A，或者1和后面一个数字一起取一个字母
        if (str[i] == '1') {
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                res += process(str, i + 2);
            }
            return res;
        }
         //遇到2 也有两种情况， 2单独取B，或者2和后面的一个数字取一个字母，前提是这个数字小于6，超过26没办法组成字母
        if (str[i] == '2') {
            int res = process(str, i + 1);
            if (i + 1 < str.length && (str[i + 1] > '0' && str[i + 1] < '6')) {
                res += process(str, i + 2);
            }
            return res;
        }
        return process(str, i + 1);
    }

    //改动态规划方法
    //在number方法中，只有i这一个可变参数，所以只有一个一维表来表示
    public static int dpWay(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;

        for (int i = N - 1; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
            } else if (str[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length) {
                    dp[i] += dp[i + 2];
                }
            } else if (str[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length && (str[i + 1] > '0' && str[i + 1] < '6')) {
                    dp[i] += dp[i + 2];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("165413432121"));
        System.out.println(dpWay("165413432121"));
    }
}
