package ru.bashsu.mobile.service;

import ru.bashsu.mobile.jpa.entity.Employee;

public interface EmployeeService {
    Employee getById(Long id);
    Employee save(Employee employee);
    boolean deleteById(Long id);

    Employee getByLogin(String login);
}
