package com.cts.redplastring.redplastringapplication.response;

import lombok.Data;

@Data
public class AdminDetial extends AbstractResponse{

    private String userId;
    private String email;
    private String name;
    private String mobile;
    private String profilePhoto;
}
