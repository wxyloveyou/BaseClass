import jdk.nashorn.internal.runtime.RewriteException;

/**
 * @author : WXY
 * @create : 2022-08-22 20:37
 * @Info : N皇后问题
 */
public class Code09_NQueens {
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        // record[0] ?  record[1]  ?  record[2]
        int[] record = new int[n];//record[i]表示i行的皇后放在了第几列
        return process1(0, record, n);
    }
    //潜台词；record[0....i-1]行的皇后， 都不共行，不共列，不共斜线
    //record[0.。。i-1] 表示之前行都放了皇后，现在从i行开始放
    //n代表整体一共有多少行，0 --- n-1 行
    //返回是，摆完所有的皇后，合理的摆法有多少中
    private static int process1(int i, int[] record, int n) {
        if (i == n) { //base case 终止行
            return 1;
        }
        //没有到终止的位置
        int res = 0;
        for (int j = 0; j < n; j++) { //当前在i行，尝试所有的列 j
            //行肯定不相等了， 列值，斜线
            //当前行是i行的皇后，放在j列上，不共行不共列不共斜线
            //如果是，认为有效
            //如果不是，认为无效
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    // record[0..i-1]你需要看，record[i...]不需要看
    // 返回i行皇后，放在了j列，是否有效
    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {//之前某K行的皇后
            if (j == record[k] || Math.abs(i - k) == Math.abs(record[k] - j)) {
                return false;
            }
        }
        return true;
    }

    //使用位运算的方式
    //不要超过32皇后问题
    public static int num2(int n){
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    //limit 划定了问题的规模，是固定的参数 例如8皇后问题，limit = 00.。0000|11111111(前面一堆0，后面8个1)
    //colLim列的限制，1的位置不能够放皇后，0的位置可以放皇后, 在3位置放了一个皇后,00000100
    //leftDiaLim左斜线的限制，1的位置不能够放皇后，0的位置可以放皇后            00001000
    //rightDiaLim右斜线的限制，1的位置不能够放皇后，0的位置可以放皇后           00000010
    private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) { //base case
            return 1;
        }
        //所有可能放皇后的位置都在pos上
        //limit | leftDiaLim | rightDiaLim --->总限制
        //~(limit | leftDiaLim | rightDiaLim)  将0变1,1变0。之前是1位置不能放皇后，0位置可以皇后
        //因为取反了，                                      现在是1位置可以放皇后，0位置不能放皇后
        //但是取反之后，前面的一堆0也变成了1，也成了干扰，所以再与limit相&，去掉前面的1
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1); //取出pos最右侧的1
            pos = pos - mostRightOne; //将pos取出的那个1从pos中删除
            res += process2(limit, (colLim | mostRightOne), (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >> 1);
        }
        return res;
    }


    public static void main(String[] args) {
        int n = 14   ;
        long start = System.currentTimeMillis();
        System.out.println(num1(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
        start = System.currentTimeMillis();
        System.out.println(num2(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }
}
