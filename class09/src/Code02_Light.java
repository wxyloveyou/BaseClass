import java.util.HashSet;

/**
 * @author : WXY
 * @create : 2022-08-13 17:33
 * @Info : 贪心问题之照明问题
 *
 * 给定一个字符串str,只由'X'和 '.'两种字符组成
 * 'X'表示墙，不能放灯，也不能被照亮
 * '.'表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以点亮 i-1，i，i+1三个位置
 * 如果返回点亮str中所有需要点亮的位置，至少需要几盏灯
 *
 *
 */
public class Code02_Light {

    public static int minLight(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    //str[index...]位置，自由选择放灯还是不放灯
    //str[0...index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    private static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { //结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') {//当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没有结束
            //i  X  .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str,index+1,lights);
                lights.remove(index);
            }
            return Math.min(no,yes);
        }
    }


    public static int minLight2(String road) {
        char[] str = road.toCharArray();
        int index = 0;
        int light = 0;

        while (index < str.length) {
            if (str[index] == 'X') {
                index++;
            } else {
                light++;
                if (index + 1 == str.length) {
                    break;
                } else {//i .
                    if (str[index + 1] == 'X') {
                        index = index + 2;
                    }else {
                        index = index + 3;
                    }
                }

            }
        }
        return light;
    }

    //for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            String test = randomString(len);
            int ans1 = minLight(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("Finish");

    }
}
