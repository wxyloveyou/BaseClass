import java.util.ArrayList;
import java.util.List;

/**
 * @author : WXY
 * @create : 2022-08-17 22:27
 * @Info : 打印全排列
 * 打印一个字符串的全部排列，
 * 打印一个字符串的全部排列，要求不要出现重复字面值的排列
 */
public class Code03_PrintAllPermutations {

    public static void main(String[] args) {
        String test = "aabbccdd";
        List<String> ans1 = permutations(test);
        List<String> ans2 = permutationsNoRepeat(test);
        for (String str : ans1) {
            System.out.println(str);

        }
        System.out.println("=======================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=======================");

    }

    private static List<String> permutationsNoRepeat(String test) {
        ArrayList<String> res = new ArrayList<>();
        if (test == null || test.length() == 0) {
            return res;
        }
        char[] ans = test.toCharArray();
        process2(ans, 0, res);
        return res;
    }

    private static void process2(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
            return;
        }
        boolean[] visit = new boolean[26];
        for (int j = i; j < str.length; j++) {
            if (!visit[str[j] - 'a']) {
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process2(str, j + 1, res);
                swap(str, i, j);
            }
        }

    }

    private static List<String> permutations(String test) {
        ArrayList<String> res = new ArrayList<>();
        if (test == null || test.length() == 0) {
            return res;
        }
        char[] str = test.toCharArray();
        process1(str, 0, res);
        return res;
    }

    // str[0..i-1]已经做好决定的
    // str[i...]都有机会来到i位置
    // i终止位置，str当前的样子，就是一种结果 -> ans
    private static void process1(char[] str, int index, ArrayList<String> res) {
        if (index == str.length) {
            res.add(String.valueOf(str));
        }
        for (int j = index; j < str.length; j++) {
            swap(str, index, j);
            process1(str, j + 1, res);
            swap(str, index, j);
        }

    }

    private static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
