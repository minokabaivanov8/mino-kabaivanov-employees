package org.example.model.csv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeProjectRecord {

    private Long id;
    private Long employeeId;
    private Long projectId;
    private String startDate;
    private String endDate;



    public EmployeeProjectRecord(long employeeId, long projectId, Date startDate, Date endDate) {
    }
}