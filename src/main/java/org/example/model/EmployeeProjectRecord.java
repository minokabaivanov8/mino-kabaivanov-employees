package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode
public class EmployeeProjectRecord {
    private Project project;
    private Date startDate;
    private Date endDate;
}
