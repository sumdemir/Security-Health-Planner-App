package com.sum.Security.auth;

import lombok.Getter;

@Getter
public class ForgotPasswordRequest {

    private String email;
    public void setEmail(String email) {
        this.email = email;
    }
}
