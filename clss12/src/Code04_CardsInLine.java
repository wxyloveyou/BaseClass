/**
 * @author : WXY
 * @create : 2022-08-25 21:05
 * @Info :  class11_Code08动态规划求解
 * 给定一个整形数组arr,代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都是绝顶聪明。返回获胜者的分数
 */
public class Code04_CardsInLine {
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return  0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    private static int s(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(f(arr, L + 1, R), f(arr, L, R - 1));
    }

    private static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + s(arr, L + 1, R),arr[R] + s(arr,L,R-1));
    }


    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N + 1][N + 1];
        int[][] s = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            f[i][i] = 1;
        }
        // 0,0 右下方移动
        // 0,1
        // 0,2
        // 0,N-1
        for (int col = 1; col < N; col++) {
            //从对角线0,col出发
            int L = 0;
            int R = col;
            while (L < N && R < N) {
                f[L][R] = Math.max(arr[L] + s[L + 1][R], arr[R] + s[L][R - 1]);
                s[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(f[0][N - 1], s[0][N - 1]);
    }



    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));

    }


}
