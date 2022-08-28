import java.util.HashMap;

/**
 * @author : WXY
 * @create : 2022-08-25 19:34
 * @Info :
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。例子: str= "babac", arr = { "ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2
 * o
 * 马士兵教育http://mashibing.com
 */
public class Code02_StickersToSpellWord {
    public static int minS(String rest, String[] arr) {
        if (rest.equals("")) {
            return 0;
        }
        return 0;
//        //搞定rest的，第一张贴纸是什么
//        int next =0;
//        for (String first : arr) {
//            rest - first   ->   nextRest;
//            int cur = minS(nextRext, arr);
//            next = Math.min(next, cur);
//        }
//        return  next + 1;
    }

    public static int minStickers1(String[] stickers, String target) {
        int n = stickers.length;
        int[][] map = new int[n][26];// stickers -> [26] [26] [26]
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                map[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process1(dp, map, target);
    }

    // dp 傻缓存，如果rest已经算过了，直接返回dp中的值
    // rest 剩余的目标
    // 0..N每一个字符串所含字符的词频统计
    // 返回值是-1，map 中的贴纸  怎么都无法rest
    private static int process1(HashMap<String, Integer> dp, int[][] map, String rest) {
        if (dp.containsKey(rest)) {
            return dp.get(rest);
        }
        //以下是正规的调用递归过程
        int ans = Integer.MAX_VALUE; // 返回的 搞定rest，使用的最少贴纸数量
        int n = map.length;
        int[] tmap = new int[26]; // tmap 去替代 rest
        char[] target = rest.toCharArray();
        for (char c : target) {
            tmap[c - 'a']++;
        }


        //map --> tmap
        for (int i = 0; i < n; i++) {
            // 枚举当前第一张贴纸是谁？
            if (map[i][target[0] - 'a'] == 0) {
                continue;
            }
            /**
             * 至少贴纸中必须要保证target中的一种字符
             */
            StringBuilder sb = new StringBuilder();
            // i 贴纸， j 枚举a~z字符
            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0 ) { // j这个字符是target需要的
                    for (int k = 0; k < Math.max(0,tmap[j] - map[i][j]); k++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            // sb ->  i  搞定了i号字符，伪代码中的减法
            String s = sb.toString();
            int tmp = process1(dp, map, s);
            if (tmp != -1) {
                ans = Math.min(ans, 1 + tmp);
            }
        }
        dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }
    public static void main(String[] args) {
        String[] arr = {"aaaa","bbbaa","ccddd"};
        String str = "ee";
        System.out.println(minStickers1(arr, str));
//        System.out.println(minStickers2(arr, str));


    }

}
