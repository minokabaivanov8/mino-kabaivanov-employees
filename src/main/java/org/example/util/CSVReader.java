package org.example.util;

import org.example.model.csv.EmployeeProjectRecordCsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<EmployeeProjectRecordCsv> readEmployeeProjectRecordsFromCSV(String filePath) throws IOException {
        List<EmployeeProjectRecordCsv> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                long employeeId = Long.parseLong(parts[0].trim());
                long projectId = Long.parseLong(parts[1].trim());
                String startDateString = parts[2].trim();
                String endDateString = parts[3].trim();

                records.add(convertToEmployeeProjectRecordCsv(employeeId, projectId, startDateString, endDateString));
            }
        }
        return records;
    }

    private static EmployeeProjectRecordCsv convertToEmployeeProjectRecordCsv(long employeeId, long projectId, String startDate, String endDate) {
        return new EmployeeProjectRecordCsv()
                .setEmployeeId(employeeId)
                .setProjectId(projectId)
                .setStartDate(startDate)
                .setEndDate(endDate);
    }
}
