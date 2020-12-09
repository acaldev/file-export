package com.export.export.service;

import com.export.export.entities.mariadb.Employee;
import com.export.export.repository.mariadb.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeesRepository repo;

    public Page<Employee> page(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return repo.findAll(pageable);
    }

    public void save(Employee product) {
        repo.save(product);
    }

    public Employee get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }


}
