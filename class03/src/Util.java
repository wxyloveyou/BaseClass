import javax.sound.midi.SoundbankResource;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 * @author : WXY
 * @create : 2022-07-30 21:34
 * @Description : 工具类，写对数器中用到的一些常用的方法
 *
 */
public class Util {
    //产生随机数组
    public static int[] generateTandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] =  (int) ((maxValue + 1)*Math.random()) - (int)((maxValue+1)*Math.random());
        }
        return arr;
    }

    //
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length ; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
