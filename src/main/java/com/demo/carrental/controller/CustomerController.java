package com.demo.carrental.controller;


import com.demo.carrental.common.Result;
import com.demo.carrental.model.LoginRequest;
import com.demo.carrental.service.ICustomerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * customer table 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
@RestController
public class CustomerController extends BaseController {

    @Resource
    private ICustomerService customerService;

    @PostMapping("/customer/login")
    public Result customerLogin(@RequestBody @Validated LoginRequest request){
        return customerService.login(request);
    }

}
