package com.cts.redplastring.redplastringapplication.response;

import lombok.Data;

@Data
public class JobTypeDetail extends AbstractResponse{

    private Integer jobId;

    private String jobName;

    private String imageName;
}
