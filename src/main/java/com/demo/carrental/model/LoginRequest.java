package com.demo.carrental.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Email(message="not valid email")
    private String email;

    @NotEmpty(message="password cannot be empty")
    private String password;

}
