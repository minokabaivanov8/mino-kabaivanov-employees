package org.example.service;

import org.example.model.Employee;
import org.example.model.EmployeeProjectRecordPair;

import java.util.List;

public interface EmployeeProjectRecordService {


    List<String> findLongestWorkingPair(List<Employee> employees);

    int calculateOverlapDuration(EmployeeProjectRecordPair employeeProjectRecordPair);
}
