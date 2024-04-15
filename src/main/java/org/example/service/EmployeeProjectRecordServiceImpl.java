package org.example.service;

import org.example.model.Employee;
import org.example.model.EmployeeProjectRecord;
import org.example.model.EmployeeProjectRecordPair;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class EmployeeProjectRecordServiceImpl implements EmployeeProjectRecordService {

    @Override
    public List<String> findLongestWorkingPair(List<Employee> employees) {
        List <String> outputList = new ArrayList<>();
        int longestDurationTemp = 0;

        // Iterate through the list of employees
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            List <EmployeeProjectRecord> employeeProjectRecords = employee.getEmployeeProjectRecords();

            for (int j = i + 1; j < employees.size(); j++) {
                Employee employeeToCompare = employees.get(j);
                List <EmployeeProjectRecord> employeeProjectRecordsToCompare = employeeToCompare.getEmployeeProjectRecords();

                List <EmployeeProjectRecordPair> employeeProjectRecordsWithSameProject = employeeProjectRecords.stream()
                        .flatMap(entry1 -> employeeProjectRecordsToCompare.stream()
                                .filter(entry2 -> entry1.getProject().equals(entry2.getProject()))
                                .map(entry2 -> new EmployeeProjectRecordPair(entry1, entry2)))
                        .toList();

                int longestDuration = 0;
                ListIterator<EmployeeProjectRecordPair> iterator = employeeProjectRecordsWithSameProject.listIterator();
                while (iterator.hasNext()){

                    int overlapDuration = calculateOverlapDuration(iterator.next());

                    if (overlapDuration >= longestDuration && overlapDuration >= longestDurationTemp) {
                        longestDurationTemp = overlapDuration;
                        String outputValue = employee.getId() +
                                ", " +
                                employeeToCompare.getId() +
                                ", " +
                                overlapDuration;
                        outputList.add(outputValue);
                    }
                }
            }
        }
        return outputList;
    }

    @Override
    public int calculateOverlapDuration(EmployeeProjectRecordPair employeeProjectRecordPair) {
        EmployeeProjectRecord employeeProjectRecordFirstEmployee = employeeProjectRecordPair.getEmployeeProjectRecord1();
        EmployeeProjectRecord employeeProjectRecordSecondEmployee = employeeProjectRecordPair.getEmployeeProjectRecord2();

        long startDateFirstEmployee = employeeProjectRecordFirstEmployee.getStartDate().getTime();
        long endDateFirstEmployee = employeeProjectRecordFirstEmployee.getEndDate() == null ? Date.valueOf(LocalDate.now()).getTime() : employeeProjectRecordFirstEmployee.getEndDate().getTime();

        long startDateSecondEmployee = employeeProjectRecordSecondEmployee.getStartDate().getTime();
        long endDateSecondEmployee = employeeProjectRecordSecondEmployee.getEndDate() == null ? Date.valueOf(LocalDate.now()).getTime() : employeeProjectRecordSecondEmployee.getEndDate().getTime();

        long overlapStart = Math.max(startDateFirstEmployee, startDateSecondEmployee);
        long overlapEnd = Math.min(endDateFirstEmployee, endDateSecondEmployee);

        if (overlapEnd >= overlapStart) {
            return (int) ((overlapEnd - overlapStart) / (1000 * 60 * 60 * 24)); // Convert milliseconds to days
        } else {
            return 0; // No overlap
        }
    }
}

