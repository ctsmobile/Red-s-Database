package com.cts.redplastring.redplastringapplication.response;

import lombok.Data;

@Data
public class EmployeeStateResponse extends AbstractResponse{

    private String employeeState;

    private String latitude;

    private String longitude;

    private Double rangeValue;
}
