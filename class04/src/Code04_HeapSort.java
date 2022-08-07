import java.util.PriorityQueue;

/**
 * @author : WXY
 * @create : 2022-07-31 16:59
 * @Description : 堆排序
 *
 */
public class Code04_HeapSort {

    //堆排序额外空间复杂度O(1)
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }


        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = (left + 1 < heapSize && arr[left + 1] > arr[left]) ? left + 1 : left;
            int new_largest = arr[largest] > arr[index] ? largest : index;
            if (new_largest == index) {
                break;
            }
            swap(arr, new_largest, index);
            index = new_largest;
            left = index * 2 + 1;
        }
    }

    // arr[index]刚来的数，往上
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {

        //默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(6);
        heap.add(8);
        heap.add(0);
        heap.add(2);
        heap.add(9);
        heap.add(1);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
        int testTime = 10000;
        int maxSize = 100;
        int maxValue = 100;
        boolean successed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = Util.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Util.copyArray(arr1);
            heapSort(arr1);
            Util.comparator(arr2);
            if (!Util.isEqual(arr1, arr2)) {
                successed = false;
                break;
            }
        }
        System.out.println(successed ? "Nice!" : "Fucking fucked!");

        int[] arr = Util.generateRandomArray(maxSize, maxValue);
        Util.printArray(arr);
        heapSort(arr);
        Util.printArray(arr);
    }

}
