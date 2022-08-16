/**
 * @author : WXY
 * @create : 2022-08-16 20:16
 * @Info : 汉诺塔问题
 */
public class Code01_Hanoi {
    public static void hanoi_1(int n) {
        leftToRight(n);
    }

    //请把1 - N-1层的圆盘从左移动到右
    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("Move " + n + " from left to right");
        midToRight(n - 1);
    }

    private static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    private static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move " + n + " from min to left");
        rightToLeft(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move " + n + " from right to left");
        midToLeft(n - 1);
    }

    private static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToMid(n - 1);
    }


    //暴力递归
    public static void hanoi_2(int n) {
        if (n > 0) {
            fun(n, "left", "right", "mid");
        }
    }

    private static void fun(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move 1 form " + from + " to " + to);
        } else {
            fun(n - 1, from, other, to);
            System.out.println("Move " + n + " from " + from + " to " + to);
            fun(n - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        hanoi_1(n);
        System.out.println("++++++++++++++++");
        hanoi_2(n);
    }
}
