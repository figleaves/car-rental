package com.demo.carrental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.carrental.common.JwtTokenUtil;
import com.demo.carrental.common.MD5PwdUtil;
import com.demo.carrental.common.Result;
import com.demo.carrental.model.LoginRequest;
import com.demo.carrental.entity.Customer;
import com.demo.carrental.mapper.CustomerMapper;
import com.demo.carrental.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * customer table 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Result login(LoginRequest request) {
        String email = request.getEmail();
        Customer customer = getCustomer(email);
        if (null == customer) {
            return Result.fail("email not exist or password wrong");
        }

        String salt = customer.getSalt();
        String password = request.getPassword();
        String encPwd = MD5PwdUtil.inputPassToDbPass(password, salt);

        if (encPwd.equals(customer.getPassword())){
            String token = jwtTokenUtil.createToken(customer);
            return Result.success(token);
        }

        return Result.fail("email not exist or password wrong");
    }

    private Customer getCustomer(String email){
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Customer::getEmail, email);
        return this.getOne(wrapper);
    }
}
