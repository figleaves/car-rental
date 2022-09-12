package com.demo.carrental.test.controller;

import com.demo.carrental.common.ErrorCode;
import com.demo.carrental.common.Result;
import com.demo.carrental.model.LoginRequest;
import com.demo.carrental.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
public class RentalOrderControllerTest {

    @Resource
    MockMvc mockMvc;

    @Resource
    ICustomerService customerService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
    @Transactional
    public void testRentACar() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("customerId", 1);
        rootNode.put("carCategoryId", 1);
        LocalDateTime now = LocalDateTime.now();

        rootNode.put("startTime", now.plusDays(1).format(formatter));
        rootNode.put("endTime", now.plusDays(10).format(formatter));

        mockMvc.perform(MockMvcRequestBuilders.post("/carRental/rent/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("token", token)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));
    }

    @Test
    @Transactional
    public void testGetOrders() throws Exception {
        testRentACar();

        mockMvc.perform(MockMvcRequestBuilders.get("/carRental/rent/getOrders")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("token", token)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));
    }




    @Test
    @Transactional
    public void rentACarInvalidCarCategoryId() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("carCategoryId", "");
        LocalDateTime now = LocalDateTime.now();

        rootNode.put("startTime", now.plusDays(1).format(formatter));
        rootNode.put("endTime", now.plusDays(1).format(formatter));

        mockMvc.perform(MockMvcRequestBuilders.post("/carRental/rent/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("token", token)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCode.INVALID_PARAMS.getErrorCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("carCategoryId cannot be null"));
    }

    @Test
    @Transactional
    public void rentACarInvalidStartTime() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("carCategoryId", 1);
        LocalDateTime now = LocalDateTime.now();

        // using paste datetime
        rootNode.put("startTime", now.minusDays(3).format(formatter));
        rootNode.put("endTime", now.plusDays(3).format(formatter));

        mockMvc.perform(MockMvcRequestBuilders.post("/carRental/rent/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("token", token)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCode.INVALID_PARAMS.getErrorCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("start time should be in one month"));

        // using more than 30 days in future
        rootNode.put("startTime", now.plusDays(31).format(formatter));
        rootNode.put("endTime", now.plusDays(32).format(formatter));

        mockMvc.perform(MockMvcRequestBuilders.post("/carRental/rent/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("token", token)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCode.INVALID_PARAMS.getErrorCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("start time should be in one month"));

        rootNode.put("startTime", "1");
        mockMvc.perform(MockMvcRequestBuilders.post("/carRental/rent/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("token", token)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCode.ERROR.getErrorCode()));
    }

    @Test
    @Transactional
    public void rentACarInvalidEndTime() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("carCategoryId", 1);
        LocalDateTime now = LocalDateTime.now();

        rootNode.put("startTime", now.plusDays(1).format(formatter));
        rootNode.put("endTime", now.plusDays(35).format(formatter));

        mockMvc.perform(MockMvcRequestBuilders.post("/carRental/rent/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("token", token)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCode.INVALID_PARAMS.getErrorCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("end time should be in one month"));

        rootNode.put("endTime", "1");
        mockMvc.perform(MockMvcRequestBuilders.post("/carRental/rent/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("token", token)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCode.ERROR.getErrorCode()));
    }

    @Test
    @Transactional
    public void rentACarStartBiggerThanEndDatetime() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("carCategoryId", 1);
        LocalDateTime now = LocalDateTime.now();

        rootNode.put("startTime", now.plusDays(1).format(formatter));
        rootNode.put("endTime", now.plusDays(1).format(formatter));

        mockMvc.perform(MockMvcRequestBuilders.post("/carRental/rent/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("token", token)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCode.INVALID_PARAMS.getErrorCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("start time should before end time more than 1 day"));

    }
}