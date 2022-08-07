/**
 * @author : WXY
 * @create : 2022-08-02 21:25
 * @Description : 桶排序_计数排序
 */
public class Code03_CountSort {
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxSize = 100;
        int maxValue = 200;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = Util.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Util.copyArray(arr1);
            countSort(arr1);
            Util.comparator(arr2);
            if (!Util.isEqual(arr1, arr2)) {
                succeed = false;
                Util.printArray(arr1);
                Util.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }

}
