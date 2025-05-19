
package jdbc;
import java.util.List;

public class Main {

    public static void main(String[] args) {
    
    
// Add employees
EmployeeDAO.addEmployee("Alice Cooper", "Developer", 70000);
EmployeeDAO.addEmployee("Bob Marley","Manager", 80000);
// Update employee
EmployeeDAO.updateEmployee(1, "John Doe", "Senior Software Engineer",90000);
// Get all employees
List<Employee> employees = EmployeeDAO.getAllEmployees();
employees.forEach(System.out::println);
// Delete employee
EmployeeDAO.deleteEmployee(2);
   
        
    }
    
}
