package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeProject {
    private Project project;
    private Employee employee;
    private Date startDate;
    private Date endDate;
}
