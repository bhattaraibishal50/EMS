import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {

    private static void dispatchActions(Scanner sc, int option) {
        EmployeeFileService efs = new EmployeeFileService();
        switch (option) {
            case 1:

                Employee[] results = efs.FindAll();
                System.out.println("--------------------------------------");
                System.out.println("Results of find all");
                System.out.println("--------------------------------------");

                for (int row = 0; row < results.length; row++) {
                    Employee emp = results[row];
                    System.out.println("Employe Id :: " + emp.getId());
                    System.out.println("Employe Name :: " + emp.getName());
                    System.out.println("Employe Age :: " + emp.getAge());
                    System.out.println("Employe Salary :: " + emp.getSalary());
                    System.out.println("Employe Position :: " + emp.getPosition());
                    System.out.println("--------------------------------------\n");
                }
                int option1 = EmployeeManagementSystem.handleApplicationOptions(sc);
                EmployeeManagementSystem.dispatchActions(sc, option1);
                break;
            case 2:
                System.out.print("Please Enter Employee ID :: \n");
                if (sc.hasNextLine()) {
                    String employeeID = sc.next();
                    Employee[] results1 = efs.FindAll();
                    Employee employee = new Employee();
                    Boolean isExist = false;
                    for (Employee employee2 : results1) {
                        if (employee2.getId().equals(employeeID)) {
                            employee = employee2;
                            isExist = true;
                        }
                    }
                    if (isExist) {
                        System.out.println("--------------------------------------");
                        System.out.println("Results of Employee Search");
                        System.out.println("--------------------------------------");
                        System.out.println("Employe Id : " + employee.getId());
                        System.out.println("Employe Name : " + employee.getName());
                        System.out.println("Employe Age : " + employee.getAge());
                        System.out.println("Employe Salary : " + employee.getSalary());
                        System.out.println("Employe Position : " + employee.getPosition());
                        System.out.println("--------------------------------------\n");
                    } else {
                        System.out.println("No records has been found \n");
                    }
                    int option2 = EmployeeManagementSystem.handleApplicationOptions(sc);
                    EmployeeManagementSystem.dispatchActions(sc, option2);
                }
                break;
            case 3:
                System.out.println("Create Employee Record");
                Employee employee1 = Employee.getInputEmployee(sc);
                Employee[] results3 = efs.FindAll();
                List<Employee> employeLists1 = new ArrayList<>();
                for (Employee employee : results3) {
                    employeLists1.add(employee);
                }
                employeLists1.add(employee1);
                if (efs.BulkCreate(employeLists1)) {
                    System.out.println("Employee has been saved. \n");
                }
                int option3 = EmployeeManagementSystem.handleApplicationOptions(sc);
                EmployeeManagementSystem.dispatchActions(sc, option3);
                break;
            case 4:
                System.out.println("update Employee Record by Employee ID");
                if (sc.hasNextLine()) {
                    System.out.print("Please enter Employee Id :: ");
                    String employeeID = sc.next();
                    Employee[] results1 = efs.FindAll();
                    List<Employee> employeLists = new ArrayList<>();
                    for (Employee employee : results1) {
                        if (!employee.getId().equals(employeeID)) {
                            employeLists.add(employee);
                        }
                    }
                    Employee employee2 = Employee.getInputEmployee(sc);
                    employeLists.add(employee2);
                    if (efs.BulkCreate(employeLists)) {
                        System.out.println("Employee record has been updated");
                    }
                    int option4 = EmployeeManagementSystem.handleApplicationOptions(sc);
                    EmployeeManagementSystem.dispatchActions(sc, option4);
                }
                break;

            case 5:
                System.out.println("Delete Record  By Employee ID");
                if (sc.hasNextLine()) {
                    System.out.print("Please enter Employee Id :: ");
                    String employeeID = sc.next();
                    Employee[] results1 = efs.FindAll();
                    List<Employee> employeLists = new ArrayList<>();
                    for (Employee employee : results1) {
                        if (!employee.getId().equals(employeeID)) {
                            employeLists.add(employee);
                        }
                    }
                    System.out.println(employeLists);
                    if (employeLists.size() != results1.length) {
                        if (efs.BulkCreate(employeLists)) {
                            System.out.println("Employee record has been deleted");
                        }
                    } else {
                        System.out.println("No Matching records Found ");
                    }
                    int option4 = EmployeeManagementSystem.handleApplicationOptions(sc);
                    EmployeeManagementSystem.dispatchActions(sc, option4);
                }
            default:
                break;
        }

    }

    private static int handleApplicationOptions(Scanner sc) {
        System.out.println("What would you like to do ? [Please enter between the option 1-6]");
        System.out.println("1 - Show All Employee List ");
        System.out.println("2 - Find One Employee with ID");
        System.out.println("3 - Create Employee Record");
        System.out.println("4 - Update Employee Record by Employee ID");
        System.out.println("5 - Delete Record  By Employee ID");
        int number;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next();
            }
            number = sc.nextInt();
        } while (number <= 0 || number >= 6);
        return number;
    }

    public static void handle() {
        try {
            System.out.println("Bootstraping Application .........");
            EmployeeFileService.init();
            System.out.println("Welcome to  Employee Management System .........");
            Scanner sc = new Scanner(System.in);
            int option = EmployeeManagementSystem.handleApplicationOptions(sc);
            EmployeeManagementSystem.dispatchActions(sc, option);
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
