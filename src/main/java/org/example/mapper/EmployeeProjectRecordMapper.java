package org.example.mapper;

import org.example.model.Employee;
import org.example.model.EmployeeProjectRecord;
import org.example.model.Project;
import org.example.model.csv.EmployeeProjectRecordCsv;

import java.util.*;

import static org.example.util.DateConverter.convertStringToDate;


public class EmployeeProjectRecordMapper {

    private Map<Long, Project> projectMap = new HashMap<>();

    private Map<Long, List<EmployeeProjectRecord>> employeeProjectRecordMap = new HashMap<>();

    public List<Employee> toEmployeeList(List<EmployeeProjectRecordCsv> employeeProjectRecordsList) {
        employeeProjectRecordsList
                .forEach(this::toEmployee);

        return employeeProjectRecordMap.entrySet()
                .stream()
                .map(longListEntry -> new Employee()
                        .setId(longListEntry.getKey())
                        .setEmployeeProjectRecords(longListEntry.getValue()))
                .toList();
    }

    private void toEmployee(EmployeeProjectRecordCsv employeeProjectRecordCsv) {
        Long employeeID = employeeProjectRecordCsv.getEmployeeId();

        if (employeeProjectRecordMap.containsKey(employeeID)) {
            List<EmployeeProjectRecord> employeeProjectRecordList = employeeProjectRecordMap.get(employeeID);
            employeeProjectRecordList.add(toEmployeeProjectRecord(employeeProjectRecordCsv));
        } else {
            ArrayList employeeProjectRecordList = new ArrayList<>(Collections.singleton(toEmployeeProjectRecord(employeeProjectRecordCsv)));
            employeeProjectRecordMap.put(employeeID, employeeProjectRecordList);
        }
    }

    private Project toProject(EmployeeProjectRecordCsv employeeProjectRecordCsv) {
        Long projectId = employeeProjectRecordCsv.getProjectId();

        if (projectMap.containsKey(projectId)) {
            return projectMap.get(projectId);
        }

        Project project = new Project()
                .setId(projectId);

        projectMap.put(projectId, project);

        return project;
    }

    private EmployeeProjectRecord toEmployeeProjectRecord(EmployeeProjectRecordCsv employeeProjectRecordCsv) {
        Project project = toProject(employeeProjectRecordCsv);

        return new EmployeeProjectRecord()
                .setProject(project)
                .setStartDate(convertStringToDate(employeeProjectRecordCsv.getStartDate()))
                .setEndDate(convertStringToDate(employeeProjectRecordCsv.getEndDate()));
    }


}
