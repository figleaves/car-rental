package com.demo.carrental.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ApiModel
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "email address", required = true)
    @Email(message="not valid email")
    private String email;

    @ApiModelProperty(value = "plain password", required = true)
    @NotEmpty(message="password cannot be empty")
    private String password;

}
