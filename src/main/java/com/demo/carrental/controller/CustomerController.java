package com.demo.carrental.controller;


import com.demo.carrental.common.Result;
import com.demo.carrental.model.LoginRequest;
import com.demo.carrental.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
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
@Api("Customer Api")
@RestController
@RequestMapping(value = "/carRental")
public class CustomerController extends BaseController {

    @Resource
    private ICustomerService customerService;

    @ApiOperation(value="Customer Login", notes="customer login get response(token)")
    @PostMapping("/customer/login")
    public Result<String> customerLogin(@RequestBody @Validated LoginRequest loginRequest){
        return customerService.login(loginRequest);
    }

}
