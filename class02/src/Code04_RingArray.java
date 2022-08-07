/**
 * @author WXY
 * @create 2022-07-30 16:10
 * 环状队列操作，
 */
public class Code04_RingArray {
    public static class MyQuede{
        private int[] arr;
        private int pushi;
        private int polli;
        private int size;
        private final int limit ;

        public MyQuede(int limit) {
            arr = new int[limit];
            polli = 0;
            pushi = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("满了 ，不能再添加了！");
            }
            size++;
            arr[pushi] = value ;
            pushi = nextIndex(pushi);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("空了，不能再出了！");
            }
            size--;
            int ans = arr[polli];
            polli = nextIndex(polli);
            return ans;
        }
        public boolean isEmpty(){
            return size == 0;
        }

        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

    }
}
