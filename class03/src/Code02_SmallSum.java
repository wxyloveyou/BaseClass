import sun.security.krb5.internal.crypto.HmacMd5ArcFourCksumType;

/**
 * @author : WXY
 * @create : 2022-07-30 22:06
 * @Description : 小和问题，
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
 * 例子:[1,3,4,2,5] 1左边比1小的数，没有; 3左边比3小的数，1; 4左
 * 边比4小的数，1、3; 2左边比2小的数，1; 5左边比5小的数，1、3、4、2;
 * 所以小和为1+1+3+1+1+3+4+2=16
 */
public class Code02_SmallSum {

    public static void main(String[] args) {
        int testTime = 1000;
        int maxSize = 100;
        int maxValue = 100;
        boolean successes = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = Util.generateTandomArray(maxSize, maxValue);
            int[] arr2 = Util.copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                successes = false;
                Util.printArray(arr1);
                Util.printArray(arr2);
                break;
            }
        }
        System.out.println(successes ? "Nice Finish!!!" : "BUG BUG BUG!!!");

    }

    private static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i ; j++) {
                if (arr[j] < arr[i]) {
                    res += arr[j];
                }
            }
        }
        return res;
    }

    private static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
       return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid)
                + process(arr, mid + 1, R)
                + merge(arr, L, mid, R);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[(r - l + 1)];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= r) {
            res += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }


}
