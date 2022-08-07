/**
 * @author WXY
 * @create 2022-07-26 22:04
 */
public class Code03_InsertionSort {
    public static void main(String[] args) {
        int testTime = 1000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i <testTime ; i++) {
            int[] arr1 = Until.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Until.copyArray(arr1);
//            Until.printArray(arr1);
            insertionSort(arr1);
//            Until.printArray(arr1);
            Until.comparator(arr2);
            if (!Until.isEqual(arr1, arr2)) {
                // 打印arr1
                // 打印arr2
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
//                  swap(arr, j, j + 1);

            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
