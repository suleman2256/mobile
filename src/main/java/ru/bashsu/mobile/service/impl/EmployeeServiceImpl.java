package ru.bashsu.mobile.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bashsu.mobile.jpa.entity.Employee;
import ru.bashsu.mobile.jpa.repository.EmployeeRepository;
import ru.bashsu.mobile.service.EmployeeService;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Не найден студент с ID " + id));
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Не удалось удалить студента с ID " + id, e);
            return false;
        }
    }

    @Override
    public Employee getByLogin(String login) {
        return employeeRepository.findByLogin(login);
    }
}
