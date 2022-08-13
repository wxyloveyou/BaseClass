import java.util.PriorityQueue;

/**
 * @author : WXY
 * @create : 2022-08-13 17:33
 * @Info : 贪心算法之哈夫曼树问题
 * 贪心算法的解题套路实战
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的.
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板.一群人想整分整块金条，怎么分最省铜板？
 * 例如，给定数组{10，20，30}，代表一共三个人，整块金条长度为60，金条要分成10，20,30三个部分。
 * 如果先把长度60的金条分成10和50，花费60；再把长度50的金条分成20和30，花费50；一共花费110铜板.
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20，花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价.
 */
public class Code03_LessMoneySplitGold {

    //暴力方法，没有仔细的看，直接复制的code
    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }

    //贪心算法
    public static int lessMonye2(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            pQ.add(cur);
            sum += cur;
        }
        return sum;
    }

    //for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 6;
        int maxValue = 100;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney1(arr) != lessMonye2(arr)) {
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("Finish!!!");
    }

}
