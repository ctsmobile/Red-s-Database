package com.cts.redplastring.redplastringapplication.request;

import lombok.Data;

@Data
public class SearchRequest {
    private String date;
    private Integer jobId;
    private String tag;
    private String employeeId;
    private Integer locationId;
}
