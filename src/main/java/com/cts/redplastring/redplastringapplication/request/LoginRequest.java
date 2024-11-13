package com.cts.redplastring.redplastringapplication.request;

import com.cts.redplastring.redplastringapplication.constant.ValidationExceptionFEMessageConstant;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {
    @NotNull(message = ValidationExceptionFEMessageConstant.EMAIL_IS_EMPTY)
    @Email
    private String email;

    @NotNull(message = ValidationExceptionFEMessageConstant.PASSWORD_IS_EMPTY)
    private String password;



}
