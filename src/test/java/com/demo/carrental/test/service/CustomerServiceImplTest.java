package com.demo.carrental.test.service;

import com.demo.carrental.common.Result;
import com.demo.carrental.model.LoginRequest;
import com.demo.carrental.service.ICustomerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("local")
@SpringBootTest
public class CustomerServiceImplTest {

    @Resource
    private ICustomerService customerService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() {
        LoginRequest request = new LoginRequest();
        request.setEmail("sveneigdoglas@exmaple.com");
        request.setPassword("eyJhbGciOi");
        Result result = customerService.login(request);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getSuccess());
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void loginFail() {
        LoginRequest request = new LoginRequest();
        request.setEmail("sveneigdoglas@xmaple.com");
        request.setPassword("eyJhbGciOi");
        Result result = customerService.login(request);
        Assert.assertNotNull(result);
        Assert.assertFalse(result.getSuccess());
        Assert.assertEquals("email not exist or password wrong", result.getMessage());
        Assert.assertNull(result.getData());
    }


}