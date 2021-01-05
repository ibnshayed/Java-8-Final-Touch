import com.sun.deploy.util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ArraySorting {

    public static void main(String[] args) {
        int[] demoArray = {2, 3, 4, 5, 1,6, 7, 9, 10,8};
        System.out.print("Original Array is: ");
        Arrays.stream(demoArray)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
        //
        Arrays.sort(demoArray);

        //Array  method 1 (Ascending Order)
        System.out.print("Array Sorting Method 1: ");
        Arrays.stream(demoArray)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        //Array  method 2 (range sorting)
        demoArray = new int[]{2, 3, 4, 5, 1, 6, 7, 9, 10, 8};
        System.out.print("Array Sorting Method 2: ");
        Arrays.sort(demoArray,3,8);
        Arrays.stream(demoArray)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        System.out.println("***Descending Order***");
        //Array  method 3 (Descending Order)
        demoArray = new int[]{2, 3, 4, 5, 1, 6, 7, 9, 10, 8};
        System.out.print("Array Sorting Method 3: ");
        demoArray = Arrays.stream(demoArray).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(i -> i)
                .toArray();
        Arrays.stream(demoArray)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        //Arrays.Sort() vs Arrays.parallelSort() function
        //if array elements is >= 79744 then
        // Arrays.parallelSort() is best , it takes less time
        int len = 10000000;
        // create two array of integers of size 10 million
        int[] arr1 = new int[len];
        int[] arr2 = new int[len];

        // Assign random values to arr1[] and arr2[]
        Random r = new Random();
        for(int i = 0; i < len; i++) {
            arr1[i] = r.nextInt();
            arr2[i] = arr1[i];
        }

        long start = System.currentTimeMillis();

        // sort arr1[] using Arrays.sort()
        Arrays.sort(arr1);

        long end = System.currentTimeMillis();

        System.out.println("Arrays.sort() took " + (end - start) + " ms");


        start = System.currentTimeMillis();

        // sort arr2[] using Arrays.parallelSort()
        Arrays.parallelSort(arr2);

        end = System.currentTimeMillis();

        System.out.println("Arrays.parallelSort() took " +
                (end - start) + " ms");



    }
}
