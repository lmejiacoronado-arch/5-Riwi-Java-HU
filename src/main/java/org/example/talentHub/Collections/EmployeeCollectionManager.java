package org.example.talentHub.Collections;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Task 1 - HU3: Migration to ArrayList and HashMap (Java 8/11 Legacy).
 *
 * WHY DYNAMIC COLLECTIONS?
 *   Fixed arrays (int[], Employee[]) require knowing the size upfront.
 *   ArrayList grows automatically; HashMap enables O(1) lookup by key.
 *
 * ArrayList<Employee>       → ordered, allows duplicates, dynamic size
 * HashMap<String, Employee> → key-value store, instant lookup by ID
 */
public class EmployeeCollectionManager {

    private ArrayList<Employee> employeeList;
    private HashMap<String, Employee> employeeMap;

    public EmployeeCollectionManager() {
        employeeList = new ArrayList<>();
        employeeMap  = new HashMap<>();
    }

    // Add employee to both collections
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        employeeMap.put(employee.getId(), employee);
        System.out.println(">> Employee added. Total: " + employeeList.size());
    }

    // List all employees
    public void listEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println(">> No employees registered.");
            return;
        }
        System.out.println("\n--- EMPLOYEE LIST ---");
        System.out.println("Total: " + employeeList.size() + "\n");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }

    // Find by ID using HashMap (fast O(1) lookup)
    public Employee findEmployee(String id) {
        Employee emp = employeeMap.get(id);
        if (emp == null) {
            System.out.println(">> Employee not found.");
        }
        return emp;
    }

    // Remove from both collections
    public boolean removeEmployee(String id) {
        Employee emp = employeeMap.remove(id);
        if (emp != null) {
            employeeList.remove(emp);
            System.out.println(">> Employee removed.");
            return true;
        }
        System.out.println(">> Employee not found.");
        return false;
    }

    public int getTotalEmployees() {
        return employeeList.size();
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public static void run() {
        EmployeeCollectionManager manager = new EmployeeCollectionManager();

        // Add sample employees
        manager.addEmployee(new Employee("E001", "Ana Lopez",    3_500_000, 85));
        manager.addEmployee(new Employee("E002", "Carlos Ruiz",  2_800_000, 45));
        manager.addEmployee(new Employee("E003", "Maria Gomez",  4_200_000, 92));
        manager.addEmployee(new Employee("E004", "Luis Perez",   3_100_000, 38));
        manager.addEmployee(new Employee("E005", "Sofia Ramirez",3_900_000, 60));

        manager.listEmployees();

        System.out.println("\n>> Searching for E003:");
        Employee found = manager.findEmployee("E003");
        if (found != null) System.out.println("   Found: " + found);

        System.out.println("\n>> Removing E002:");
        manager.removeEmployee("E002");
        manager.listEmployees();
    }
}
