/**
 * @author : WXY
 * @create : 2022-07-30 21:44
 * @Description : 归并排序,只看了递归方法实现，非递归方法没有看，
 *
 */
public class Code01_MergeSort {
    //递归方法实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    //arr[L.....R] 范围上，变成有序的
    //L...R   N   T(N) = 2*T(N/2) + O(1) ---->
    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[(R - L + 1)];
        int i = 0;// help数组专用变量
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //要么p1越界，要么p2越界，两个选其一
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    // 非递归方法实现 ,没有仔细的去看， 直接copy的code
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;// 当前有序的，左组长度
        while (mergeSize < N) { // log N
            int L = 0;
            // 0....
            while (L < N) {
                // L...M  左组（mergeSize）
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                //  L...M   M+1...R(mergeSize)
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int maxSize = 100;
        int maxValue = 100;
        boolean successed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = Util.generateTandomArray(maxSize, maxValue);
            int[] arr2 = Util.copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!Util.isEqual(arr1, arr2)) {
                successed = false;
                Util.printArray(arr1);
                Util.printArray(arr2);
                break;
            }
        }
        System.out.println(successed ? "Nice Finishing!!!" : "BUG BUG BUG！！！");
    }

}
