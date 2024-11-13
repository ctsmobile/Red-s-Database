package com.cts.redplastring.redplastringapplication.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResponse extends AbstractResponse{
    private String token;
    private String name;
    private String profilePhoto;
}
