package com.cts.redplastring.redplastringapplication.response;

import lombok.Data;

import java.util.List;

@Data
public class TimeTrackingListResponse extends AbstractResponse{
    private List<TimeTrackingResponse> timeTrackingList;
    private List<TagDetail> tagDetailList;
    private List<LocationDetail> locationDetailList;
    private List<JobTypeDetail> jobTypeList;
    private List<GetEmployeeResponse> employeeList;
}
