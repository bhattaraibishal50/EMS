import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeFileService {

    private static String FILE_PATH = "./employee_management_system.csv";
    private Employee employee;
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
        employee = new Employee();
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

}
