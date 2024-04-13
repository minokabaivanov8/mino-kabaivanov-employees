package org.example.api;

import org.example.model.EmployeeProjectRecord;
import org.example.service.EmployeeProjectRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employee-project")
public class EmployeeProjectAPI {
    @Autowired
    private EmployeeProjectRecordService employeeProjectRecordService;

    @GetMapping
    public List<EmployeeProjectRecord> getAllEmployeeProjectRecords() throws IOException {
        return employeeProjectRecordService.getAllEmployeeProjectRecords();
    }

}
