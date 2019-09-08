import java.util.OptionalDouble;
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
       // OptionalDouble optionalDouble = IntStream.rangeClosed(1, 10).average();
        //double average = optionalDouble.getAsDouble();
        double average = IntStream.rangeClosed(1, 10).average().getAsDouble();
        System.out.println("IntStream.rangeClosed(1,10).average().getAsDouble(): " + average); // 55 / 10 = 5.5

        

    }

}
