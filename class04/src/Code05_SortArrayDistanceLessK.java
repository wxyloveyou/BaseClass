import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : WXY
 * @create : 2022-08-01 21:09
 * @Description :  一个基本有序的数组，将其排序，要求每个数字移动的长度不超过k
 *
 */
public class Code05_SortArrayDistanceLessK {

    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }

        //默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index= 0;
        //0 .... k-1
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxVlaue,int k) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxVlaue + 1) * Math.random()) - (int) ((maxVlaue + 1) * Math.random());
        }
        //先排序
        Arrays.sort(arr);
        //然后开始随意交换，但是保证每个数距离不超过K
        //swap[i] == true 表示 i 位置已经参与过交换了 ，
        //swap[i] == false 表示 i 位置已经参与过交换了 ，
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (1 + k)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("Test Begin!");
        int testTime = 10000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = Util.copyArray(arr);
            int[] arr2 = Util.copyArray(arr);
            sortedArrDistanceLessK(arr1, k);
            Util.comparator(arr2);
            if (!Util.isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                Util.printArray(arr);
                Util.printArray(arr1);
                Util.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


}
