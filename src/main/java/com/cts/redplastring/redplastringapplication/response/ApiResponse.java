package com.cts.redplastring.redplastringapplication.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private String code;
    public ApiResponse(String message){
        this.message = message;
    }

    public ApiResponse(String message,String code){
        this.message = message;
        this.code = code;

    }
}
