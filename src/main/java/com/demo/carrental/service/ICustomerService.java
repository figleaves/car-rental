package com.demo.carrental.service;

import com.demo.carrental.common.Result;
import com.demo.carrental.model.LoginRequest;
import com.demo.carrental.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * customer table 服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
public interface ICustomerService extends IService<Customer> {

    Result<String> login(LoginRequest request);
}
