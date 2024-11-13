package com.cts.redplastring.redplastringapplication.request;

import lombok.Data;

@Data
public class ExportRequest {
    private String dateRange;
    private Integer jobId;
    private String tag;
    private String employeeId;
    private Integer locationId;
}
