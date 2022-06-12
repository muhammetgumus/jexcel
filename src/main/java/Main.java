import model.Employee;
import model.Student;
import service.ExcelWriter;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        //List
        List<Employee> employees = Arrays.asList(
                new Employee("Muhammet", "Gumus", "1"),
                new Employee("Kobe", "Bryant", "2"),
                new Employee("LeBron", "James", "3"));

        List<Student> students = Arrays.asList(
                new Student(1, "Muhammet", "Gumus", "AU"),
                new Student(2, "Ash", "Ketchum", "Tokyo University"),
                new Student(3, "Misty", "Williams", "Kyoto University"),
                new Student(4, "Brock", "Harrison", "Osaka University")
        );

        //Set
        Set<Student> studentSet = new HashSet<>() {};
        studentSet.add(students.get(0));
        studentSet.add(students.get(students.size() - 1));

        //Queue
        PriorityQueue studentQueue = new PriorityQueue();
        studentQueue.add(students.get(1));
        studentQueue.add(students.get(students.size() - 2));

        ExcelWriter writer = new ExcelWriter();
        writer.write(employees);
        writer.write(students);
        writer.write(studentSet);
        writer.write(studentQueue);
        //writer.write(List.of());
    }
}
