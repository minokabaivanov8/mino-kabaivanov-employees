package org.example.controller;

import org.example.model.Employee;
import org.example.service.EmployeeProjectRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employee-project")
public class EmployeeProjectController {

    @Autowired
    private EmployeeProjectRecordServiceImpl employeeProjectRecordService;
    @Autowired
    private CSVController csvController;

    @GetMapping
    public List<Employee> allEmployeeProjectRecords(@RequestBody String csvBody) throws IOException {
        return csvController.processCSV(csvBody);
    }

    @PostMapping
    public List<String> getLongestTimeEmployeesWorkedTogether(@RequestBody String csvBody) throws IOException {
        return employeeProjectRecordService.findLongestWorkingPair(csvController.processCSV(csvBody));
    }

}
