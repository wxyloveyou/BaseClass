import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author : WXY
 * @create : 2022-08-01 21:05
 * @Description :  将默认的小根堆改写成大根堆
 */
public class Test {

    public static class MyComp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }


    public static void main(String[] args) {

        //默认是小根堆，改成大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComp());
        heap.add(5);
        heap.add(7);
        heap.add(3);
        heap.add(0);
        heap.add(2);
        heap.add(5);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());

        }

    }

}
