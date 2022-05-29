package ru.bashsu.mobile.jpa.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@ApiModel(description = "Студенты")
public class Employee {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column(name = "first_name", columnDefinition = "VARCHAR(255)", nullable = false)
    @ApiModelProperty(value = "Имя студента")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(255)", nullable = false)
    @ApiModelProperty(value = "Фамилия студента")
    private String lastName;

    @Column(name = "middle_name", columnDefinition = "VARCHAR(255)")
    @ApiModelProperty(value = "Отчество студента")
    private String middleName;

    @Column(name = "email", columnDefinition = "VARCHAR(255)")
    @ApiModelProperty(value = "Почта студента")
    private String email;

    @Column(name = "login", columnDefinition = "VARCHAR(255)", nullable = false)
    @ApiModelProperty(value = "Логин студента")
    private String login;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(50)", nullable = false)
    @ApiModelProperty(value = "Логин студента")
    private String phoneNumber;

    @Column(name = "password", columnDefinition = "VARCHAR(255)", nullable = false)
    @ApiModelProperty(value = "Пароль студента")
    private String password;

    @Column(name = "stage", columnDefinition = "VARCHAR(255)", nullable = false)
    @ApiModelProperty(value = "Ступень обучения(Бакалавриат, магистратура")
    private String stage;

    @Column(name = "course", nullable = false)
    @ApiModelProperty(value = "Ступень обучения(Бакалавриат, магистратура) студента")
    private Integer course;

    @Column(name = "group", columnDefinition = "VARCHAR(255)", nullable = false)
    @ApiModelProperty(value = "Группа студента")
    private String group;
}
