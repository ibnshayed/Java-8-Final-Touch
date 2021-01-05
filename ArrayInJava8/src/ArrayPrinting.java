import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayPrinting {
    public static void main(String[] args) {

        int[] demoArray = {2, 3, 4, 5, 1,6, 7, 9, 10,8};

        //Array Print method 1
        System.out.print("Array Print Method 1: ");
        for(int i =0; i<demoArray.length; i++){
            System.out.print(demoArray[i]+" ");
        }
        System.out.println();

        //Array Print method 2
        System.out.print("Array Print Method 2: ");
        for(int i: demoArray){
            System.out.print(i+" ");
        }
        System.out.println();

        //Array Print method 3
        System.out.print("Array Print Method 3: ");
        Arrays.stream(demoArray)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        //Array Print method 4
        System.out.print("Array Print Method 4: ");
        IntStream.of(demoArray)
                 .forEach(x -> System.out.print(x + " "));
        System.out.println();

        //Array Print method 5
        System.out.print("Array Print Method 5: ");
        IntStream.range(0,demoArray.length)
                .map( x -> demoArray[x])
                .forEach(x -> System.out.print(x + " "));
        System.out.println();



    }
}
