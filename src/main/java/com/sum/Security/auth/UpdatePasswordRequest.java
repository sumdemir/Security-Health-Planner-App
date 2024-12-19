package com.sum.Security.auth;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePasswordRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String newPassword;
}
