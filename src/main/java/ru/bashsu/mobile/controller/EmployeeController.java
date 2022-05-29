package ru.bashsu.mobile.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bashsu.mobile.consts.Status;
import ru.bashsu.mobile.dto.RestResponse;
import ru.bashsu.mobile.jpa.entity.Employee;
import ru.bashsu.mobile.service.EmployeeService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
@Api(description = "API для работы со студентами")
public class EmployeeController {

    private final EmployeeService employeeService;

    @ApiOperation("Получение студента по ID")
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Employee>> getEmployeeById(
            @ApiParam("ID") @PathVariable Long id) {

        try {
            Employee result = employeeService.getById(id);
            return RestResponse.success(result);
        } catch (Exception e) {
            val msg = String.format("Ошибка получения студента по ID [%s]", id);
            log.error(msg, e);
            return RestResponse.error(HttpStatus.BAD_REQUEST, msg);
        }
    }

    @ApiOperation("Создание/изменение студента")
    @PostMapping
    public ResponseEntity<RestResponse<Employee>> save(
            @ApiParam(value = "Запрос", required = true)
            @RequestBody Employee request) {

        try {
            Employee result = employeeService.save(request);
            return RestResponse.success(result);
        } catch (Exception e) {
            val msg = "Ошибка создания/изменения студента: " + e.getMessage();
            log.error(msg, e);
            return RestResponse.error(HttpStatus.BAD_REQUEST, msg);
        }

    }

    @ApiOperation("Удаление студента по ID")
    @DeleteMapping("{id}")
    public ResponseEntity<RestResponse<Boolean>> deleteById(
            @ApiParam("ID") @PathVariable Long id) {
        try {
            boolean result = employeeService.deleteById(id);
            return RestResponse.success(result);
        } catch (Exception e) {
            val msg = String.format("Ошибка удаления студента по ID [%s]", id);
            log.error(msg, e);
            return RestResponse.error(HttpStatus.BAD_REQUEST, msg);
        }

    }

    @ApiOperation("Получение студента по Login")
    @GetMapping("/filter/{login}")
    public ResponseEntity<RestResponse<Employee>> getEmployeeByLogin(
            @ApiParam("ID") @PathVariable String login) {
        try {
            Employee result = employeeService.getByLogin(login);
            return RestResponse.success(result);
        } catch (Exception e) {
            val msg = String.format("Ошибка получения студента по Login [%s]", login);
            log.error(msg, e);
            return RestResponse.error(HttpStatus.BAD_REQUEST, msg);
        }
    }

    @ApiOperation("Авторизация студента")
    @PostMapping("/users/login")
    public ResponseEntity<RestResponse<Status>> loginUser(@RequestBody Employee employee) {
        try {
            Employee e = employeeService.getByLogin(employee.getLogin());
            if (e == null) {
                throw new EntityNotFoundException("Пользователь с логином " + employee.getLogin() + " не найден.");
            } else if (!e.getPassword().equals(employee.getPassword())) {
                throw new EntityNotFoundException("Не верный пароль для пользователя с логином " + employee.getLogin() + ".");
            }
            return RestResponse.success(Status.SUCCESS);
        } catch (Exception e) {
            val msg = String.format("Ошибка авторизации по логину [%s]", employee.getLogin());
            log.error(msg, e);
            return RestResponse.error(HttpStatus.UNAUTHORIZED, msg);
        }
    }
}
