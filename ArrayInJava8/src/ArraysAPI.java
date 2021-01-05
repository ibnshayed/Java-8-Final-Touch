import java.util.Arrays;

public class ArraysAPI {
    public static void main(String[] args) {
        int[] demoArray = {2, 3, 4, 5, 1,6, 7, 9, 10,8};
        int[] tempArray = new int[demoArray.length];

        System.out.print("Original Demo Array: ");
        Arrays.stream(demoArray).forEach(x -> System.out.print(x +" "));
        System.out.println();

        // Array.copyOf(array,length)
        System.out.print("Copy Index[0-4] DemoArray to TempArray: ");
        tempArray = Arrays.copyOf(demoArray,5);
        Arrays.stream(tempArray).forEach(x -> System.out.print(x +" "));
        System.out.println();

        // Arrys.copyOfRange(array,from,to)
        System.out.print("Copy Index[1-5] DemoArray to TempArray: ");
        tempArray = Arrays.copyOfRange(demoArray,1,6);
        Arrays.stream(tempArray).forEach(x -> System.out.print(x +" "));
        System.out.println();

        //Copy the full array
        System.out.print("Copy Full DemoArray to TempArray: ");
        tempArray = Arrays.copyOf(demoArray,demoArray.length);
        Arrays.stream(tempArray).forEach(x -> System.out.print(x +" "));
        System.out.println();

        // Arrays.sort()
        System.out.print("After Sorting tempArray: ");
        Arrays.sort(tempArray);
        Arrays.stream(tempArray).forEach(x -> System.out.print(x +" "));
        System.out.println();

        // Binary Search
        System.out.println("Value is found in index: "
                +Arrays.binarySearch(demoArray,3));
        Arrays.stream(demoArray).forEach(x -> System.out.print(x +" "));
        System.out.println();

        //Arrays.fill(array,value)
        Arrays.fill(tempArray,0);
        Arrays.stream(tempArray).forEach(x -> System.out.print(x +" "));
        System.out.println();

        System.out.println(Arrays.equals(demoArray,tempArray)); //flase

    }
}
