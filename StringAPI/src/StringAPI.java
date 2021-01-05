import java.sql.SQLOutput;

public class StringAPI {
    public static void main(String[] args) {
        String demoString ="Assalamu Alaikum,";
        String tempString ="Welcome to Bangladesh";

        System.out.println("demoString: " + demoString);
        System.out.println("tempString: " + tempString);

        System.out.println("demoString length: " + demoString.length());
        System.out.println("character at(index[6]) : "+demoString.charAt(6));
        System.out.println("ASCII value of index[0]: "+demoString.codePointAt(0));
        System.out.println("ASCII value before index[1]: "+demoString.codePointBefore(1));
        System.out.println("Returns the number of Unicode code points in the specified text range of this String"
                +demoString.codePointCount(0,11));


        System.out.println(demoString.compareTo(tempString));

    }
}
