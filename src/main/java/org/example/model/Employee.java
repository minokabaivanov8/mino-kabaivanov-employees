package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode
public class Employee {

    private Long id;
    private List<EmployeeProjectRecord> employeeProjectRecords = new ArrayList<>();
}
