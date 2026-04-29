package com.riwi.talent.view;

import com.riwi.talent.controller.EmployeeController;
import com.riwi.talent.model.entity.EmployeeEntity;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 * Task 3 - HU5: View layer — ALL user interaction lives here.
 *
 * MVC RULE: Scanner is used ONLY in this class.
 * Controller and DAO layers never read from System.in or print menus.
 * This makes it possible to replace the console UI with a web or
 * desktop UI without touching any business or persistence logic.
 */
public class TalentHubView {

    private final EmployeeController controller;

    public TalentHubView() {
        this(new EmployeeController());
    }

    public TalentHubView(EmployeeController controller) {
        this.controller = Objects.requireNonNull(controller, "controller");
    }

    public void start(Scanner scanner) {
        int option;
        do {
            printMenu();
            option = readInt(scanner, "Choose an option: ");
            handleOption(option, scanner);
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("""
                
                ╔══════════════════════════════════╗
                ║   CORPORATE TALENT HUB — HU5     ║
                ╠══════════════════════════════════╣
                ║  1. Add employee                 ║
                ║  2. List all employees           ║
                ║  3. Find employee by ID          ║
                ║  4. Update employee              ║
                ║  5. Delete employee              ║
                ║  6. Generate full report         ║
                ║  0. Exit HU5                     ║
                ╚══════════════════════════════════╝""");
    }

    private void handleOption(int option, Scanner scanner) {
        switch (option) {
            case 1 -> addEmployee(scanner);
            case 2 -> listAll();
            case 3 -> findById(scanner);
            case 4 -> updateEmployee(scanner);
            case 5 -> deleteEmployee(scanner);
            case 6 -> System.out.println(controller.generateReport());
            case 0 -> System.out.println(">> Exiting HU5 menu.");
            default -> System.out.println(">> Invalid option.");
        }
    }

    private void addEmployee(Scanner scanner) {
        System.out.println("\n--- Add Employee ---");
        String fullName   = readString(scanner, "Full name: ");
        String email      = readString(scanner, "Email: ");
        String role       = readRole(scanner);
        double baseSalary = readDouble(scanner, "Base salary: ");
        int    score      = readIntInRange(scanner, "Score (0-10): ", 0, 10);

        Optional<EmployeeEntity> saved = controller.create(fullName, email, role, baseSalary, score);
        saved.ifPresentOrElse(
                employee -> System.out.println(">> Employee added with ID: " + employee.getId()),
                () -> System.out.println(">> Employee was not added. Check duplicated email or DB error.")
        );
    }

    private void listAll() {
        List<EmployeeEntity> list = controller.getAll();
        if (list.isEmpty()) { System.out.println(">> No employees yet."); return; }
        System.out.println("\n--- Employee List ---");
        list.forEach(System.out::println);
        System.out.println("Total: " + list.size());
    }

    private void findById(Scanner scanner) {
        int id = readInt(scanner, "\nEmployee ID: ");
        controller.getById(id).ifPresentOrElse(
                emp -> System.out.println(">> Found: " + emp),
                ()  -> System.out.println(">> Not found: ID " + id));
    }

    private void updateEmployee(Scanner scanner) {
        int id = readInt(scanner, "\nID to update: ");
        Optional<EmployeeEntity> existing = controller.getById(id);
        if (existing.isEmpty()) { System.out.println(">> Not found: ID " + id); return; }
        System.out.println(">> Current: " + existing.get());
        boolean ok = controller.update(id,
                readString(scanner, "Full name: "),
                readString(scanner, "Email: "),
                readRole(scanner),
                readDouble(scanner, "Base salary: "),
                readIntInRange(scanner, "Score (0-10): ", 0, 10));
        System.out.println(ok ? ">> Updated." : ">> Update failed.");
    }

    private void deleteEmployee(Scanner scanner) {
        int id = readInt(scanner, "\nID to delete: ");
        System.out.println(controller.delete(id)
                ? ">> Employee " + id + " deleted."
                : ">> Not found: ID " + id);
    }

    // ── Input helpers ─────────────────────────────────────────────────────

    private String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int v = scanner.nextInt();
                scanner.nextLine();
                return v;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(">> Enter a valid integer.");
            }
        }
    }

    private int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            int v = readInt(scanner, prompt);
            if (v >= min && v <= max) return v;
            System.out.println(">> Value must be between " + min + " and " + max + ".");
        }
    }

    private double readDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double v = scanner.nextDouble();
                scanner.nextLine();
                return v;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(">> Enter a valid number.");
            }
        }
    }

    private String readRole(Scanner scanner) {
        System.out.println("Role: 1=Developer  2=Manager  3=ExternalConsultant");
        return switch (readIntInRange(scanner, "Choose role: ", 1, 3)) {
            case 1  -> "Developer";
            case 2  -> "Manager";
            default -> "ExternalConsultant";
        };
    }
}
