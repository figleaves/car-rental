package com.demo.carrental.test.service;

import com.demo.carrental.common.Result;
import com.demo.carrental.service.ICarCategoryService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("local")
@SpringBootTest
public class CarCategoryServiceImplTest {

    @Resource
    private ICarCategoryService carCategoryService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCarCategoryList() {
        Result result = carCategoryService.getCarCategoryList();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getSuccess());
        Object data = result.getData();
        Assert.assertNotNull(data);
        Assert.assertTrue(data instanceof List);
    }
}