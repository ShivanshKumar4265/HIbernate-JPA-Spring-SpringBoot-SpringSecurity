package org.jmd.controller;

import org.jmd.dao.DepartmentDao;
import org.jmd.model.Department;
import org.jmd.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentController {

    public static void main(String[] args) {
//        new DepartmentController().run();

//        int departmentId = readInt(scanner, "Enter department ID: ");
        DepartmentDao departmentDao = new DepartmentDao();
        Department department = departmentDao.getDepartmenWihtEmmplyeeByDepartmentID(1);

        if (department == null) {
            System.out.println("Department not found.");
            return;
        }


        List<Employee> employees = department.getEmployees();
        if (employees == null || employees.isEmpty()) {
            System.out.println("Employees: None");
            return;
        }

        System.out.println("Department ID: " + department.getId());
        System.out.println("Department Name: " + department.getDepartmentName());

        System.out.println("Employees:");
        for (Employee employee : employees) {
            System.out.println("- ID: " + employee.getId() + ", Name: " + employee.getName() + ", Email: " + employee.getEmail() + ", Salary: " + employee.getSalary());
        }

//        printDepartment(department);
    }

    private void run() {
        System.out.println("---------------------- Department Management ----------------------");
        try (Scanner scanner = new Scanner(System.in)) {
            DepartmentDao departmentDao = new DepartmentDao();
            boolean running = true;

            while (running) {
                printMenu();
                int choice = readInt(scanner, "Enter your choice: ");

                switch (choice) {
                    case 1:
                        createDepartment(scanner, departmentDao);
                        break;
                    case 2:
                        viewDepartmentById(scanner, departmentDao);
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting department management...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println();
            }
        }
    }

    private void printMenu() {
        System.out.println("1. Add Department with Employees");
        System.out.println("2. View Department with Employees By ID");
        System.out.println("0. Exit");
    }

    private void createDepartment(Scanner scanner, DepartmentDao departmentDao) {
        System.out.println("\n--- Add Department ---");
        String departmentName = readRequired(scanner, "Enter department name: ");
        int employeeCount = readInt(scanner, "Enter number of employees: ");

        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i <= employeeCount; i++) {
            System.out.println("\nEmployee " + i + ":");
            Employee employee = new Employee();
            employee.setId(readLong(scanner, "Enter employee ID: "));
            employee.setName(readRequired(scanner, "Enter employee name: "));
            employee.setEmail(readRequired(scanner, "Enter employee email: "));
            employee.setSalary(readRequired(scanner, "Enter employee salary: "));
            employees.add(employee);
        }

        Department department = new Department();
        department.setDepartmentName(departmentName);

        Department saved = departmentDao.saveDepartment(department, employees);
        System.out.println("Saved department with ID: " + saved.getId());
    }

    public void viewDepartmentById(Scanner scanner, DepartmentDao departmentDao) {
        int departmentId = readInt(scanner, "Enter department ID: ");
        Department department = departmentDao.getDepartmenWihtEmmplyeeByDepartmentID(departmentId);

        if (department == null) {
            System.out.println("Department not found.");
            return;
        }

        printDepartment(department);
    }

    private void printDepartment(Department department) {
        System.out.println("Department ID: " + department.getId());
        System.out.println("Department Name: " + department.getDepartmentName());

        List<Employee> employees = department.getEmployees();
        if (employees == null || employees.isEmpty()) {
            System.out.println("Employees: None");
            return;
        }

        System.out.println("Employees:");
        for (Employee employee : employees) {
            System.out.println("- ID: " + employee.getId() + ", Name: " + employee.getName() + ", Email: " + employee.getEmail() + ", Salary: " + employee.getSalary());
        }
    }

    private String readRequired(Scanner scanner, String prompt) {
        while (true) {
            String value = readOptional(scanner, prompt);
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This value is required.");
        }
    }

    private String readOptional(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readOptional(scanner, prompt));
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private long readLong(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Long.parseLong(readOptional(scanner, prompt));
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid long number.");
            }
        }
    }
}
