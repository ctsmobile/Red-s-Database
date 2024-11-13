package com.cts.redplastring.redplastringapplication.response;

import lombok.Data;

import java.util.List;

@Data
public class JobTypeListResponse extends AbstractResponse{

    private List<JobTypeDetail> data;
}
