/**
 * @author WXY
 * @create 2022-07-27 22:24
 *
 *  在arr上，找满足<=value的最右位置
 */
public class Code05_BSNearRight {
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

    private static int nearestIndex(int[] arr, int value) {
        int L = 0 ;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > value) {
                R = mid - 1;
            }else {
                index = mid;
                L = mid + 1;
            }
        }
        return index;
    }

    private static int test(int[] arr, int value) {
        //arr从小到大排序,那么从最后一个开始查找比较，从大到小比较
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return -1;
    }

}
