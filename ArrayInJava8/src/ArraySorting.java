import java.util.Arrays;

public class ArraySorting {

    public static void main(String[] args) {
        int[] demoArray = {2, 3, 4, 5, 1,6, 7, 9, 10,8};

        //
        Arrays.sort(demoArray);

        //Array  method 1
        System.out.print("Array Sorting Method 3: ");
        Arrays.stream(demoArray)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

    }
}
