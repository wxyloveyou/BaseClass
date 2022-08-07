import java.util.Arrays;
import java.util.Comparator;

/**
 * @author : WXY
 * @create : 2022-07-31 19:26
 * @Description : 比较器
 *   return o1 - o2
 *   返回1 ,o2在前，但是返回的是1，说明o1 比 o2 大，o2在前，又o2小，所以是从小到大排序
 *   返回-1,01在前，但是返回的是-1，说明o1比 o2 小，o1在前，又o1小，所以还是从小到大排序
 *
 *   return o2 - o1
 *   返回1  ,o2在前，但是返回的是1，说明o2 比 o1 大，o2在前，又o1小，所以是从大到小排序
 *   返回-1 ,o1在前，但是返回的是-1，说明o2 比 o1小，o1在前，又o1大，所以是从大到小排序
 */
public class Code01_Comparator {

    public static class Student{
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        public static class IdAdcendingComparator implements Comparator<Student> {

            //返回负数的时候，第一个参数排在前面
            //返回正数的时候，第二个参数排在前面
            //返回0的时候，谁在前都一样
            @Override
            public int compare(Student o1, Student o2) {

                if (o1.id > o2.id) {
                    return 1;
                } else if (o1.id < o2.id) {
                    return -1;
                }else {
                    return 0;
                }
            }

        }

        public static class IdDescendingComparator implements Comparator<Student>{

            @Override
            public int compare(Student o1, Student o2) {
                return o2.id - o1.id;
            }
        }

        public static class AgeAscendingComparator implements Comparator<Student> {

            @Override
            public int compare(Student o1, Student o2) {
                return o1.age - o2.age;
            }
        }

        public static class AgeDescendingComparator implements Comparator<Student> {
            //返回1 ，o2在前，那么o2.age > o1.age
            @Override
            public int compare(Student o1, Student o2) {
                return o2.age - o1.age;
            }
        }

        public static class AgeShengIdSheng implements Comparator<Student> {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.age != o2.age ? (o1.age - o2.age) : (o1.id - o2.id);
            }
        }

        //先按照id排序，id小的放前面，
        //id相等，age大的放前面
        public static class IdInAgeDe implements Comparator<Student> {

            @Override
            public int compare(Student o1, Student o2) {
                return (o1.id != o2.id) ? (o1.id - o2.id) : (o2.age - o1.age);
            }
        }


        public static void printStudent(Student[] students) {
            for (Student stu:students){
                System.out.println("Name : " + stu.name + ",Id : " + stu.id + ",Age : " + stu.age);
            }
        }

        public static void printArray(Integer[] arr) {
            if (arr == null) {
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        public static class MyComp implements Comparator<Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }


        public static class AComp implements Comparator<Integer> {
            //如果返回负数，认为第一个参数应该排在前面
            //如果返回正数，认为第二个参数应该排在前面
            //如果返回0，认为谁在前面都行
            @Override
            public int compare(Integer arg0, Integer arg1) {
               return arg1 - arg0;
               // return arg0 - arg1;
                // 1  arg1在前 arg0 > arg1
            }
        }

        public static void main(String[] args) {
            Integer[] arr = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
            Arrays.sort(arr,new AComp());
            for(int i = 0 ;i < arr.length;i++) {
                System.out.println(arr[i]);
            }
            System.out.println("==========================");

            Student student1 = new Student("A", 2, 20);
            Student student2 = new Student("B", 1, 50);
            Student student3 = new Student("C", 3, 10);
            Student[] students = {student1, student2, student3};
            Arrays.sort(students,new IdDescendingComparator());
            printStudent(students);
        }
    }


}
