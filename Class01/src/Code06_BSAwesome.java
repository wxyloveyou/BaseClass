/**
 * @author WXY
 * @create 2022-07-27 22:37
 * 问题：局部最小问题
 */
public class Code06_BSAwesome {
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;//不存在
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 2] > arr[arr.length - 1]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0 ;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int testTImes = 100;
        int maxSize = 20;
        int maxValue = 100;
//        boolean succeed = true;
        for (int i = 0; i < testTImes ; i++) {
            int[] arr = Until.generateRandomArray(maxSize, maxValue);
            int lessIndex = getLessIndex(arr);
            Until.printArray(arr);
            System.out.println("arr.length : " + (arr.length - 1) );
            System.out.println("index : " + lessIndex);
        }
    }
}
