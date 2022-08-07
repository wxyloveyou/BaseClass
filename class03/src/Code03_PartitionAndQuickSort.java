/**
 * @author : WXY
 * @create : 2022-07-31 9:52
 * @Description : 快排 和 荷兰国旗问题
 * 快排1.0  O(N^2):
 *   以arr[R]作为划分，小于等于的在左侧，大于arr[R]的在右侧，但不是有序的

 * 快排2.0  O(N^2):
 *  arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
 *  <arr[R]  ==arr[R]  > arr[R]

 * 快排3.0  O(N*logN)：
 * 在2.0的基础上增加了随机性，将 L...R的任意元素与arr[R]进行互换 ，然后以新的
 * arr[R]做为基准，进行荷兰国旗的划分，
 *
 */
public class Code03_PartitionAndQuickSort {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //以arr[R]作为划分，小于等于的在左侧，大于arr[R]的在右侧，但不是有序的
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, R, ++lessEqual);
        return lessEqual;
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    //  <arr[R]  ==arr[R]  > arr[R]
    public static int[] netherlandFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1; //左侧起始位置
        int more = R;  //右侧起始位置
        int index = L; //当前需要比较的位置
        while (index < more) {
            if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --more);
            } else if (arr[index] == arr[R]) {
                index++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
//        int less = L - 1;
//        int more = R + 1;
//        int indx = L;
//        int tmp = arr[R];
//        while (index < more) {
//            if (arr[index] < tmp) {
//                swap(arr, index++, ++less);
//            } else if (arr[index] > tmp) {
//                swap(arr, index, --more);
//            }else {
//                index++;
//            }
//        }
//        return new int[]{less + 1, more - 1};

    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int mid = partition(arr, L, R);
        process1(arr, L, mid - 1);
        process1(arr, mid + 1, R);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = netherlandFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1 );
        process2(arr, equalArea[1] + 1, R);
    }
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }
    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //quickSort2 process2是以R所在的元素进行划分的， 这个时间复杂度是O(N^2)
        //现在将R所在的元素与任意元素进行交换，这样具有了随机性，时间复杂度是O(N*logN)
        swap(arr, L + (int) (Math.random()*(R - L + 1)), R);
        int[] equalArea = netherlandFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1 );
        process3(arr, equalArea[1] + 1, R);
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxSize = 100;
        int maxValue = 100;
        boolean sucessed = true;
        for (int i = 0; i < 3; i++) {
            int[] arr1 = Class03_Util.generateTandomArray(maxSize, maxValue);
            int[] arr2 = Class03_Util.copyArray(arr1);
            int[] arr3 = Class03_Util.copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!Class03_Util.isEqual(arr1, arr2) || !Class03_Util.isEqual(arr3, arr2)) {
                sucessed = false;
                break;
            }
        }
        System.out.println(sucessed ? "Nice Finishing!!!" : "BUG BUG BUG");
    }



}
