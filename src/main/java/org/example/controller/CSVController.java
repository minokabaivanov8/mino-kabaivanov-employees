package org.example.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.mapper.EmployeeProjectRecordMapper;
import org.example.model.Employee;
import org.example.model.csv.EmployeeProjectRecordCsv;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CSVController {

    @PostMapping("/process-csv")
    public List<Employee> processCSV(@RequestBody String csvBody) {
        try {
            // Create a StringReader from the CSV content in the request body
            Reader reader = new StringReader(csvBody);

            // Parse the CSV content
            CSVParser csvParser = CSVFormat.DEFAULT.parse(reader);

            // Process each record
            List<String> rows = new ArrayList<>();
            for (CSVRecord record : csvParser) {
                StringBuilder row = new StringBuilder();
                for (String field : record) {
                    row.append(field).append(", ");
                }
                rows.add(row.toString());
            }

            List<EmployeeProjectRecordCsv> employeeProjectRecordCsvs = rows.stream()
                    .map(this::toEmployeeProjectRecordCsv)
                    .toList();

            EmployeeProjectRecordMapper employeeProjectRecordMapper =new EmployeeProjectRecordMapper();

            List<Employee> employees = new ArrayList<>();

            employees=employeeProjectRecordMapper.toEmployeeList(employeeProjectRecordCsvs);


            // Return the processed rows
            return employees;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private EmployeeProjectRecordCsv toEmployeeProjectRecordCsv(String csvBodyRow) {
        String[] parts = csvBodyRow.split(",");

        long employeeId = Long.parseLong(parts[0].trim());
        long projectId = Long.parseLong(parts[1].trim());
        String startDateString = parts[2].trim();
        String endDateString = parts[3].trim();

        return new EmployeeProjectRecordCsv()
                .setEmployeeId(employeeId)
                .setProjectId(projectId)
                .setStartDate(startDateString)
                .setEndDate(endDateString);
    }
}


