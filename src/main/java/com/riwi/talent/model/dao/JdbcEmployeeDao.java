package com.riwi.talent.model.dao;

import com.riwi.talent.model.config.DatabaseConnection;
import com.riwi.talent.model.entity.EmployeeEntity;
import com.riwi.talent.model.report.EmployeeReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Task 2 - HU5: CRUD implementation using JDBC + try-with-resources.
 *
 * SECURITY — PreparedStatement vs Statement (SQL Injection):
 *
 *   UNSAFE (Statement):
 *     "SELECT * FROM employees WHERE id = " + id
 *     If id = "1 OR 1=1" → returns ALL rows.
 *     If id = "1; DROP TABLE employees;--" → destroys the table.
 *
 *   SAFE (PreparedStatement — used exclusively here):
 *     "SELECT * FROM employees WHERE id = ?"
 *     ps.setInt(1, id);
 *     The ? is NEVER interpreted as SQL — always treated as a literal value.
 *     SQL Injection is physically impossible.
 *
 * try-with-resources closes Connection, PreparedStatement and ResultSet
 * automatically in reverse order — zero risk of memory leaks.
 */
public class JdbcEmployeeDao implements EmployeeDao {

    @Override
    public Optional<EmployeeEntity> save(EmployeeEntity e) {
        String sql = "INSERT INTO employees (full_name, email, role, base_salary, score) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getFullName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getRole());
            ps.setDouble(4, e.getBaseSalary());
            ps.setInt(5, e.getScore());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) e.setId(keys.getInt(1));
            }
            return Optional.of(e);
        } catch (SQLException ex) {
            System.err.println("[DAO ERROR] save: " + ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<EmployeeEntity> findAll() {
        String sql = "SELECT id, full_name, email, role, base_salary, score " +
                "FROM employees ORDER BY id";
        List<EmployeeEntity> result = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) result.add(mapRow(rs));
        } catch (SQLException ex) {
            System.err.println("[DAO ERROR] findAll: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public Optional<EmployeeEntity> findById(int id) {
        String sql = "SELECT id, full_name, email, role, base_salary, score " +
                "FROM employees WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        } catch (SQLException ex) {
            System.err.println("[DAO ERROR] findById: " + ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean update(EmployeeEntity e) {
        String sql = "UPDATE employees SET full_name=?, email=?, role=?, " +
                "base_salary=?, score=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getFullName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getRole());
            ps.setDouble(4, e.getBaseSalary());
            ps.setInt(5, e.getScore());
            ps.setInt(6, e.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("[DAO ERROR] update: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("[DAO ERROR] delete: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<EmployeeReport> findEmployeeReports() {
        /*
         * Task 4 - HU5: Complex SELECT mapped directly to an immutable Record.
         * The SQL CASE expression calculates the promotion bonus in the DB layer.
         * Java 8 style usually required a mutable POJO plus extra setters after
         * reading the ResultSet; with Java 17/21 Records, each row becomes a
         * compact immutable DTO that is easier to maintain.
         */
        String sql = """
                SELECT
                    id,
                    full_name,
                    role,
                    base_salary,
                    score,
                    CASE
                        WHEN role = 'Manager' THEN base_salary * 0.25
                        WHEN role = 'Developer' THEN base_salary * 0.15
                        WHEN role = 'ExternalConsultant' THEN base_salary * 0.10
                        ELSE 0
                    END AS promotion_bonus
                FROM employees
                ORDER BY id;
                """;

        List<EmployeeReport> result = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new EmployeeReport(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("role"),
                        rs.getDouble("base_salary"),
                        rs.getInt("score"),
                        rs.getDouble("promotion_bonus")));
            }
        } catch (SQLException ex) {
            System.err.println("[DAO ERROR] findEmployeeReports: " + ex.getMessage());
        }
        return result;
    }

    private EmployeeEntity mapRow(ResultSet rs) throws SQLException {
        return new EmployeeEntity(
                rs.getInt("id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getString("role"),
                rs.getDouble("base_salary"),
                rs.getInt("score"));
    }
}
