package com.riwi.talent.model.dao;

import com.riwi.talent.model.entity.EmployeeEntity;
import com.riwi.talent.model.report.EmployeeReport;

import java.util.List;
import java.util.Optional;

/**
 * Task 2 - HU5: DAO interface — defines the CRUD contract.
 * Separates WHAT the data layer does from HOW it does it.
 */
public interface EmployeeDao {
    Optional<EmployeeEntity> save(EmployeeEntity employee);
    List<EmployeeEntity> findAll();
    Optional<EmployeeEntity> findById(int id);
    boolean update(EmployeeEntity employee);
    boolean delete(int id);

    /**
     * Task 4 - HU5: maps a complex SELECT directly to an immutable record.
     */
    List<EmployeeReport> findEmployeeReports();
}
