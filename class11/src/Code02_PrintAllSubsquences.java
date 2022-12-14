import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author : WXY
 * @create : 2022-08-17 22:04
 * @Info :打印子序列
 *  打印一个字符串的全部子序列
 *  打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 *
 */
public class Code02_PrintAllSubsquences {


    public static void main(String[] args) {
        String test = "aabccd";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);
        for (String ans : ans1){
            System.out.println(ans);
        }
        System.out.println("+++++++++++++++++++++++");
        for (String ans : ans2) {
            System.out.println(ans);
        }
        System.out.println("++++++++++++++++++++++++");


    }

    private static List<String> subsNoRepeat(String test) {
        char[] str = test.toCharArray();
        HashSet<String> set = new HashSet<>();
        List<String> ans = new ArrayList<>();
        String path = "";
        process2(str, 0, set, path);
        for (String cur :
                set) {
            ans.add(cur);
        }
        return ans;
    }

    // str固定，不变
    // index此时来到的位置, 要  or 不要
    // 如果index来到了str中的终止位置，把沿途路径所形成的答案，放入ans中
    // 之前做出的选择，就是path
    private static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        String no  = path;
        process2(str, index + 1, set, no);
        String yes = path + String.valueOf(str[index]);
        process2(str, index + 1, set, yes);
    }

    private static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }
    // str固定，不变
    // index此时来到的位置, 要  or 不要
    // 如果index来到了str中的终止位置，把沿途路径所形成的答案，放入ans中
    // 之前做出的选择，就是path
    private static void process1(char[] str, int index, List<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return ;
        }
        String no = path;
        process1(str, index + 1, ans, no);
        String yes = path + String.valueOf(str[index]);
        process1(str,index +1 ,ans,yes);
    }






}
