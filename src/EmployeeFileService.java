import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmployeeFileService {

    private static String FILE_PATH = "./employee_management_system.csv";
    private File file;

    public static int GetRows() {
        try {
            File file = new File(EmployeeFileService.FILE_PATH);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            int lines = 0;
            while (br.readLine() != null)
                lines++;
            br.close();
            return lines;
        } catch (Exception e) {
            throw new Error("Error", null);
        }

    }

    public EmployeeFileService() {
        file = new File(FILE_PATH);
    }

    public static void init() {
        try {
            File file = new File(EmployeeFileService.FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Employee[] FindAll() {
        try {
            int rows = EmployeeFileService.GetRows();
            Employee[] employee = new Employee[rows];
            FileReader fr2 = new FileReader(file);
            BufferedReader br2 = new BufferedReader(fr2);
            String line = "";
            String splitBy = ",";
            int i = 0;
            while ((line = br2.readLine()) != null) {
                String[] tempArr = line.split(splitBy);
                employee[i] = new Employee();
                employee[i].setId(tempArr[0]);
                employee[i].setName(tempArr[1]);
                employee[i].setAge(Integer.parseInt(tempArr[2]));
                employee[i].setSalary(Double.parseDouble(tempArr[3]));
                employee[i].setPosition(tempArr[4]);
                i++;
            }
            br2.close();
            return employee;
        } catch (Exception e) {
            System.out.println("error ::" + e.getMessage());
            return null;
        }
    }

    public boolean Create(Employee employee) {
        try {
            File file = new File(EmployeeFileService.FILE_PATH);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String splitBy = ",";
            bw.append(employee.getId() + splitBy
                    + employee.getName() + splitBy
                    + employee.getAge() + splitBy
                    + employee.getSalary() + splitBy
                    + employee.getPosition());
            bw.newLine();
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("error ::" + e.getMessage());
            return false;
        }
    }

    public boolean BulkCreate(List<Employee> employee) {
        try {
            File file = new File(EmployeeFileService.FILE_PATH);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String splitBy = ",";
            for (Employee employee2 : employee) {
                bw.write(employee2.getId() + splitBy
                        + employee2.getName() + splitBy
                        + employee2.getAge() + splitBy
                        + employee2.getSalary() + splitBy
                        + employee2.getPosition());
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("error ::" + e.getMessage());
            return false;
        }

    }

}
