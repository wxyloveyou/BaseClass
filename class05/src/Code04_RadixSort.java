/**
 * @author : WXY
 * @create : 2022-08-03 21:55
 * @Description : 基数排序
 * 不基于比较的排序
 *
 */
public class Code04_RadixSort {
    //没有不良的数据
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }
    //取出value值中第digit位上的数值
    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }
    //arr[L....R] 排序，   digit(最大数的位数)
    //L....R  2 45 678     3(因为678有三位)
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;  //10进制数
        int i = 0, j = 0;
        //有多少个数，准备多少个辅助空间
        int help[] = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) {
            //10个空间，
            //count[0] 当前位(d)位是0的数字有多少个
            //count[1] 当前位(d)位是1的数字有多少个
            //count[2] 当前位(d)位是2的数字有多少个
            //count[i] 当前位(d)位是i的数字有多少个
            int[] count = new int[radix];// count[0...9]
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxSize = 100;
        int maxValue = 1000000;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = Util.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Util.copyArray(arr1);
            radixSort(arr1);
            Util.comparator(arr2);
            if (!Util.isEqual(arr1, arr2)) {
                succeed = false;
                Util.printArray(arr1);
                Util.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "BUG BUG BUG");
        int[] arr = Util.generateRandomArray(maxSize, maxValue);
        Util.printArray(arr);
        radixSort(arr);
        Util.printArray(arr);
    }
}
