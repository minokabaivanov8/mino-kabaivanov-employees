package org.example.model.csv;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode
public class EmployeeProjectRecordCsv {

    private Long employeeId;
    private Long projectId;
    private String startDate;
    private String endDate;

    public EmployeeProjectRecordCsv(long l, long l1, String s, String s1) {
    }

    public EmployeeProjectRecordCsv() {

    }
}