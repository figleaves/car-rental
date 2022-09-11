package com.demo.carrental.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.After;
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
public class CustomerControllerTest {

    @Resource
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void customerLoginOK() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("email", "sveneigdoglas@exmaple.com");
        rootNode.put("password", "eyJhbGciOi");

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"));
    }

    @Test
    public void customerLoginEmptyPwd() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("email", "sveneigdoglas@exmaple.com");
        rootNode.put("password", "");

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("false"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("password cannot be empty"));
    }

    @Test
    public void customerLoginInvalidEmail() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("email", "sveneigdoglasxmaple.com");
        rootNode.put("password", "123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(rootNode.toString())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("not valid email"));
    }
}