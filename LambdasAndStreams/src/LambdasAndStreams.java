import javax.crypto.spec.PSource;
import java.security.SecureRandom;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdasAndStreams {

    public static void main(String[] args) {
        /*
            Book -> Java How To Program Early Edition
            Chater -> 17 (Lambdas And Streams )
         */

        //Familiar summation using for loop
        int total = 0;
        for (int i = 1; i <= 10; i++) {
            total += i;
        }
        System.out.println("Total : " + total); // output = 55

        //IntStream.rangeClosed(1,10).sum() -> gives sum of 1+2+3+4+5+6+7+8+9+10 = 55
        int IntStreamRangeCloseSum = IntStream.rangeClosed(1, 10).sum(); // return a integer number
        System.out.println("IntStream.rangeClosed(1,10).sum() : " + IntStreamRangeCloseSum); // output = 55

        //IntStream.range(1,10).sum() -> gives sum of 1+2+3+4+5+6+7+8+9 = 45
        int IntStreamRangeSum = IntStream.range(1, 10).sum(); // return a integer number
        //System.out.println("IntStream.rangeClosed(1,10).sum() : "+IntStream.range(1,10).sum()); // output = 45
        System.out.println("IntStream.rangeClosed(1,10).sum() : " + IntStreamRangeSum); // output = 45

        /*using average function that return OptionDouble
        A container object which may or may not contain a double value. If a value is present, isPresent() will return true and getAsDouble() will return the value.
        Additional methods that depend on the presence or absence of a contained value are provided, such as orElse() (return a default value if value not present) and ifPresent() (execute a block of code if the value is present).

        This is a value-based class; use of identity-sensitive operations (including reference equality (==), identity hash code, or synchronization) on instances of OptionalDouble may have unpredictable results and should be avoided.
        See more on https://docs.oracle.com/javase/8/docs/api/java/util/OptionalDouble.html
         */

        //OptionalDouble optionalDouble = IntStream.rangeClosed(1, 10).average();
        //double average = optionalDouble.getAsDouble();
        double average = IntStream.rangeClosed(1, 10).average().getAsDouble();
        System.out.println("IntStream.rangeClosed(1,10).average().getAsDouble(): " + average); // 55 / 10 = 5.5

        //using array
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("IntStream.rangeClosed(array[0],array[9]).sum() : " + IntStream.rangeClosed(array[0], array[9]).sum()); //output -> 55
        System.out.println("IntStream.rangeClosed(array[0],array[9]).average() : " + IntStream.rangeClosed(array[0], array[9]).average().getAsDouble()); //output -> 55 / 10 = 5.5

        //IntStream Count() function that returns total element of the Stream and that's a long value
        System.out.println("IntStream.rangeClosed(1,100).count(): " + IntStream.rangeClosed(1, 100).count());

        //Casting
        System.out.println("Casting as DoubleStream : " + IntStream.rangeClosed(1, 10).asDoubleStream().sum());
        System.out.println("Casting as LongStream : " + IntStream.rangeClosed(1, 10).asLongStream().sum());

        //distinct() function use to collect only distinct value
        int[] array1 = {1, 2, 2, 3, 3, 4, 5, 6};
        array1 = IntStream.rangeClosed(array1[0], array1[array1.length - 1]).distinct().toArray();
        System.out.print("Printing array1 using Lambdas :");
        Arrays.stream(array1).forEach(x -> System.out.print(x + " ")); // output = 1 2 3 4 5 6
        System.out.println();

        //empty() function
        System.out.println("Using Empty() : " + IntStream.empty().sum()); // output = 0;

        /*
            17.3 Mapping And Lamdas

         */
        System.out.print("Sum of the even ints from 2 through 20 is : ");
        total = 0;
        for (int i = 2; i <= 20; i += 2) {
            total += i;
        }
        System.out.println(total); // total = 110

        //Same as above for loop
        System.out.println("Using IntStream--Sum of the even ints from 2 through 20 is : " +
                IntStream.rangeClosed(1, 10).map(x -> x * 2).sum()); //output = 100

       /* // Revolution of Lambdas in .map(x -> x * 2)
        //one
        int multiplyBy2(int x){
            return x * 2;
        }
        //two
            (int x) -> { return x * 2;}
        //three
            (x) -> { return x * 2;}
        //four
            (x) ->  x * 2
         //six
            x -> x * 2
        */

        //Common Intermediate Stream Operation
        // filter , distinct , limit , map , sorted

        // filter  -- known as predicate. work for filtering using condition

        System.out.print("Summation of all even * 3 of 1 to 10 is : ");
        total = 0;
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0)   // filter() operation
                total += i * 3;  // i*3 is map() operation
        }

        System.out.println(total); //total = 90

        System.out.println("Using filter -- Summation of all even * 3 of 1 to 10 is : " + IntStream.rangeClosed(1, 10)
                .filter(x -> x % 2 == 0).map(x -> x * 3).sum()); // output = 90

        //Ch:17.5 How Elements Move Through Stream Pipelines

        IntStream.rangeClosed(1, 10)
                .filter(
                        x -> {
                            System.out.println("Filter: " + x);
                            return x % 2 == 0;
                        }
                )
                .map(
                        x -> {
                            System.out.println("Map: " + x);
                            return x * 3;
                        }
                ).sum();


        //Ch: 17.6 Method References ::

        SecureRandom secureRandom = new SecureRandom();

        //Display 10 random integers
        System.out.print("Random numbers : ");
        //secureRandom.ints(10,1,7).forEach(System.out::println);  // with method references
        // secureRandom.ints(10,1,7); tolal 10 , form 1 to 6 ... not 7
        secureRandom.ints(10, 1, 7).forEach(x -> System.out.print(x + " ")); //  without method reference
        System.out.println();

        String numbers = secureRandom.ints(10, 1, 7)
                .mapToObj(String::valueOf) // objectName::instanceMethodName
                .collect(Collectors.joining(" ")); //Concatenating string with collect

        System.out.println("Random number on one lines: " + numbers); // number == "a b c d e f g ...."

        //IntStream common terminal Operation
        // forEach , average , count , max , min , reduce

        int[] array2 = {1, 5, 6, 2, 3, 4, 10, 9, 8, 7};

        // count
        System.out.println("Count(total element of array2) : " + IntStream.of(array2).count()); // output = 10

        //max and min returns optional so that getAsInt() needed
        //max
        System.out.println("Max value of array2 : " + IntStream.of(array2).max().getAsInt());// output = 10

        //min
        System.out.println("Min value of array2 : " + IntStream.of(array2).min().getAsInt());// output = 1

        //sum
        System.out.println("Summation of array2 elements: " + IntStream.of(array2).sum());// output = 55

        //average return as Optional so that getAsDouble() needed
        System.out.println("Average of array2 elements: " + IntStream.of(array2).average().getAsDouble()); // output = 5.5

        //Sum of array2 with reduce method
        System.out.println("Sum via reduce method: " + IntStream.of(array2).reduce(0, (x, y) -> x + y)); // output = 55

        //Product of array2 with reduce method
        System.out.println("Product via reduce method : " + IntStream.of(array2).reduce((x, y) -> x * y).getAsInt()); // output = 3628800

        //Sum of square of values with map and sum method
        System.out.print("Sum of square via map and sum : " +
                IntStream.of(array2)
                        .map(x -> x * x)
                        .sum());
        System.out.println();
        //Displaying the element of array2 in sorted order
        System.out.println("Sorted Array2 : " +
                IntStream.of(array2)
                        .sorted()
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")));

       /* Sorting array2 and then printing array2
        array2 = IntStream.of(array2).sorted().toArray();
        IntStream.of(array2).forEach(x -> System.out.print(x + " "));

        //another way to sort array
         Arrays.sort(array2);
       */

       /*
        17.8 Functional Interface
        BinaryOperator<T> , UnaryOperator<T> , Consumer<T> , Function<T,R> , Predicate<T> , Supplier<T>
        ** Stream<Integer> Manipulations **
        */

        // ch: 17.10
        Integer[] array3 = {4, 5, 6, 2, 1, 8, 7, 9, 3};

        //System.out.printf("Original Value: %s%n" + Arrays.asList(array3));
        System.out.println("Original Value: " + Arrays.asList(array3));

        System.out.println("Sorted value: " + Arrays.stream(array3).sorted().collect(Collectors.toList()));

        System.out.println("Sorted value greater than 4 : " + Arrays.stream(array3).filter(x -> x > 4).sorted().collect(Collectors.toList()));

        /*
         ch: 17.11
         Stream<String> Manipulations
         */

        String[] strings = {"Red", "Orange", "Yellow", "Green", "Blue", "White", "Pink"};

        //Display original string
        System.out.println("Origina string: " + Arrays.asList(strings));

        //UpperCase
        System.out.println("String in UpperCase: " + Arrays.stream(strings).map(String::toUpperCase).collect(Collectors.toList()));

        //String less than "n" (case intensitive ) sorted ascending
        System.out.println("String less than \"n\" (case intensitive ) sorted ascending: "
                + Arrays.stream(strings).filter(x -> x.compareToIgnoreCase("n") < 0)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList()));

        //String less than "n" (case intensitive ) sorted ascending
        System.out.println("String less than \"n\" (case intensitive ) sorted descending: "
                + Arrays.stream(strings).filter(x -> x.compareToIgnoreCase("n") < 0)
                .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                .collect(Collectors.toList()));



    }

}
