package com.riwi.talent.controller;

import com.riwi.talent.model.dao.EmployeeDao;
import com.riwi.talent.model.dao.JdbcEmployeeDao;
import com.riwi.talent.model.entity.EmployeeEntity;
import com.riwi.talent.model.report.EmployeeReport;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Task 3 - HU5: Controller layer — mediates between View and Model.
 *
 * MVC RESPONSIBILITIES:
 *   MODEL      → EmployeeEntity, EmployeeDao, JdbcEmployeeDao  (data + persistence)
 *   VIEW       → TalentHubView                                 (Scanner, println)
 *   CONTROLLER → EmployeeController (this class)               (flow coordination)
 *
 * The View NEVER touches the DAO directly.
 * The DAO NEVER talks to the View.
 * Either layer can be replaced without touching the other.
 */
public class EmployeeController {

    private final EmployeeDao dao;

    public EmployeeController() {
        this(new JdbcEmployeeDao());
    }

    public EmployeeController(EmployeeDao dao) {
        this.dao = Objects.requireNonNull(dao, "dao");
    }

    public Optional<EmployeeEntity> create(String fullName, String email, String role,
                                           double baseSalary, int score) {
        return dao.save(new EmployeeEntity(null, fullName, email, role, baseSalary, score));
    }

    public List<EmployeeEntity> getAll() { return dao.findAll(); }

    public Optional<EmployeeEntity> getById(int id) { return dao.findById(id); }

    public boolean update(int id, String fullName, String email, String role,
                          double baseSalary, int score) {
        return dao.update(new EmployeeEntity(id, fullName, email, role, baseSalary, score));
    }

    public boolean delete(int id) { return dao.delete(id); }

    /**
     * Task 4 — Uses a complex SELECT in the DAO and maps ResultSet rows directly
     * to immutable EmployeeReport records, then returns a Text Block report.
     */
    public String generateReport() {
        List<EmployeeReport> reports = dao.findEmployeeReports();
        if (reports.isEmpty()) return "No employees found in the database.";

        double totalSalary  = reports.stream().mapToDouble(EmployeeReport::baseSalary).sum();
        double totalBonuses = reports.stream().mapToDouble(EmployeeReport::promotionBonus).sum();
        double avgScore     = reports.stream().mapToInt(EmployeeReport::score).average().orElse(0);

        StringBuilder rows = new StringBuilder();
        for (EmployeeReport r : reports) {
            rows.append("  ").append(r.toFormattedRow()).append("\n");
        }

        // Task 4 — Text Block (Java 15+): readable report template in source code.
        // Java 8 equivalent would be dozens of concatenated strings with \n.
        return """
                ╔══════════════════════════════════════════════════════════════════════════════════╗
                ║              CORPORATE TALENT HUB — CONSOLIDATED DB REPORT                      ║
                ╠══════════════════════════════════════════════════════════════════════════════════╣
                ║ ID   | Full Name             | Role                   |    Base Salary | Score | Bonus        |
                ╠══════════════════════════════════════════════════════════════════════════════════╣
                """ + rows +
                String.format("""
                ╠══════════════════════════════════════════════════════════════════════════════════╣
                ║  Total employees : %-5d                                                         ║
                ║  Total salaries  : $%,14.0f                                                  ║
                ║  Total bonuses   : $%,14.0f                                                  ║
                ║  Average score   : %,.1f                                                        ║
                ╚══════════════════════════════════════════════════════════════════════════════════╝
                """, reports.size(), totalSalary, totalBonuses, avgScore);
    }
}
