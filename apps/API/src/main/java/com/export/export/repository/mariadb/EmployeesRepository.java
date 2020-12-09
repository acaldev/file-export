package com.export.export.repository.mariadb;

import com.export.export.entities.mariadb.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeesRepository extends PagingAndSortingRepository<Employee, Integer> {
}
