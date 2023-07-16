import java.util.Scanner;

public class Employee {
    private String id;
    private String name;
    private int age;
    private String position;
    private double salary;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public static Employee getInputEmployee(Scanner sc) {
        Employee employee1 = new Employee();
        if (sc.hasNextLine()) {
            System.out.print("Please enter Employee Id :: ");
            employee1.setId(sc.next());
        }
        if (sc.hasNextLine()) {
            System.out.print("Please enter Employee Name :: ");
            employee1.setName(sc.next());
        }

        if (sc.hasNextLine()) {
            System.out.print("Please enter Employee age :: ");
            int age;
            do {
                while (!sc.hasNextInt()) {
                    System.out.print("Invalid number, Please enter valid age ::");
                    sc.next();
                }
                age = sc.nextInt();
                if (age <= 0) {
                    System.out.println("negative number, Please emter positive age :: ");
                }
            } while (age <= 0);
            employee1.setAge(age);
        }
        if (sc.hasNextLine()) {
            System.out.print("Enter Employee poistion :: ");
            employee1.setPosition(sc.next());
        }
        if (sc.hasNextLine()) {
            System.out.print("Enter Employee salary :: ");
            double salary;
            do {
                while (!sc.hasNextInt()) {
                    System.out.print("Invalid number, Please enter valid Salary :: ");
                    sc.next();
                }
                salary = sc.nextInt();
            } while (salary < 0);
            employee1.setSalary(salary);
        }
        return employee1;
    }

}
