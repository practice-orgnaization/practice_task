import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void increaseSalary(double rate) {
        this.salary = this.salary * (1 + rate);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> findByDepartment(String department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    public Optional<Employee> findById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public void increaseDepartmentSalary(String department, double rate) {
        employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .forEach(e -> e.increaseSalary(rate));
    }

    public double getAverageSalary() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public Map<String, Double> getAverageSalaryByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
    }

    public void printAll() {
        employees.forEach(System.out::println);
    }
}

public class Main {
    public static void main(String[] args) {

        EmployeeService service = new EmployeeService();

        service.addEmployee(new Employee(1, "Tanaka", "Sales", 300000));
        service.addEmployee(new Employee(2, "Suzuki", "Sales", 320000));
        service.addEmployee(new Employee(3, "Sato", "IT", 400000));
        service.addEmployee(new Employee(4, "Kato", "IT", 450000));
        service.addEmployee(new Employee(5, "Yamada", "HR", 280000));
        service.addEmployee(new Employee(5, "Watanabe", "HR", 290000));
        service.addEmployee(new Employee(5, "Oiwa", "HR", 240000));

        System.out.println("=== All Employees ===");
        service.printAll();

        System.out.println("\n=== IT Department ===");
        service.findByDepartment("IT")
                .forEach(System.out::println);

        System.out.println("\n=== Increase IT Salary by 10% ===");
        service.increaseDepartmentSalary("IT", 0.10);
        service.printAll();

        System.out.println("\n=== Average Salary ===");
        System.out.println(service.getAverageSalary());

        System.out.println("\n=== Average Salary By Department ===");
        service.getAverageSalaryByDepartment()
                .forEach((dept, avg) ->
                        System.out.println(dept + " : " + avg));

        System.out.println("\n=== Find Employee ID=3 ===");
        service.findById(3)
                .ifPresent(System.out::println);
    }
}