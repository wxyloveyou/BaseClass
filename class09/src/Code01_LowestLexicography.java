import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * @author : WXY
 * @create : 2022-08-13 17:33
 * @Info : 一个字符串数组， 按照什么样子的顺序，连成一串，得到的字典序最小
 *
 */
public class Code01_LowestLexicography {
    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        ArrayList<String> all = new ArrayList<>();
        HashSet<Integer> use = new HashSet<>();
        process(strs,use,"",all);
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).compareTo(lowest) < 0) {
                lowest = all.get(i);
            }
        }
        return lowest;
    }

    //strs 里面放着所有的字符串
    //已经使用过的字符串的下标，在use里面登记了，不要再使用了，
    //之前使用过的字符串，拼接成path，
    //用all收集所有的可能性
    private static void process(String[] strs, HashSet<Integer> use, String path, ArrayList<String> all) {
        if (use.size() == strs.length) {
            all.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!use.contains(i)) {
                    use.add(i);
                    process(strs, use, path + strs[i], all);
                    use.remove(i);
                }
            }
        }

    }

    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }

    //for test
    public static String generateRandomString(int strlen) {
        char[] ans = new char[(int) (Math.random() * strlen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    //for test
    public static String[] generateTandomStringArray(int arrlen, int strlen) {
        String[] ans = new String[(int) (Math.random() * arrlen) ];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strlen);
        }
        return ans;
    }
    //for test
    public static String[] conyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrlen = 6;
        int strlen = 5;
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateTandomStringArray(arrlen, strlen);
            String[] arr2 = conyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("Finish");

    }

}
