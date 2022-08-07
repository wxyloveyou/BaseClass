/**
 * @author WXY
 * @create 2022-07-27 21:45
 * 在一个有序的数组上，找出满足大于等于value值的最左侧的位置，
 *
 */
public class Code05_BSNearLeft {
    public static void main(String[] args) {
        int testTImes = 100;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTImes ; i++) {
            int[] arr = Until.generateRandomArray(maxSize, maxValue);
            Until.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random());
            if (test(arr, value) != nearestIndex(arr, value)) {
                Until.printArray(arr);
                System.out.println("value : " + value);
                System.out.println("test_ consult ：" + test(arr, value));
                System.out.println("nearestIndex_consult : " + nearestIndex(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }

    private static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return  -1;
    }

    private static int nearestIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1; //记录最左侧的对号
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return  index;
    }

}
