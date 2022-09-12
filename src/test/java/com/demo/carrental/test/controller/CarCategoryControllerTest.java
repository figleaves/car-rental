package com.demo.carrental.test.controller;

import com.demo.carrental.common.Result;
import com.demo.carrental.model.LoginRequest;
import com.demo.carrental.service.ICustomerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
public class CarCategoryControllerTest {

    @Resource
    MockMvc mockMvc;

    @Resource
    ICustomerService customerService;

    String token;

    @Before
    public void setUp() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("sveneigdoglas@exmaple.com");
        request.setPassword("eyJhbGciOi");
        Result result = customerService.login(request);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getSuccess());
        Assert.assertNotNull(result.getData());
        token = (String) result.getData();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCarCategoryList() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/carRental/carCategory/list")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("token", token)
                    ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"));
    }

}