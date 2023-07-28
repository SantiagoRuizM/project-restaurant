package com.order.serviceorder.services;

import com.order.serviceorder.entities.EmployeeEntity;
import com.order.serviceorder.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public void createEmployee(EmployeeEntity employee) {
        repository.save(employee);
    }
}
