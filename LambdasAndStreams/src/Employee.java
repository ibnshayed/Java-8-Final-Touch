import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.List;

public class Employee {
    private String firstName;
    private String lastName;
    private double salary;
    private String department;

    public Employee(String firstName, String lastName, double salary, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

 /*   @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return String.format("%-8s %-8s %8.2f  %s",
                getFirstName(), getLastName(), getSalary(), getDepartment());
    }

    public static void main(String[] args) {

        Employee[] employee = {
                new Employee("Jason", "Red", 5000, "IT"),
                new Employee("David", "Blue", 4000, "IT"),
                new Employee("Abdul", "Karim", 14000, "English"),
                new Employee("Jahir", "Rihan", 45000, "CSE"),
                new Employee("Khalid", "Hossain", 34000, "Accounts"),
                new Employee("Abdul", "Mazid", 40000, "Marketing"),
        };

        //Get list view of the employee
        List<Employee> employeeList = Arrays.asList(employee);
        System.out.println("Employee list: ");
        employeeList.stream().forEach(System.out::println);

        /*
            CH:17.12.2 Filtering Empployees with Salaries in a specific Range
         */


    }
}
