package com.demo.carrental.test.service;

import com.demo.carrental.common.Result;
import com.demo.carrental.service.IRentalOrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("local")
@SpringBootTest
public class RentalOrderServiceImplTest {

    @Resource
    private IRentalOrderService rentalOrderService;


    @Test
    @Transactional
    public void rentACar() {
        Integer customerId = 1;
        Integer categoryId = 2;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusWeeks(3);
        LocalDateTime endTime = now.plusWeeks(4);

        Result result = rentalOrderService.rentACar(categoryId, customerId, startTime, endTime);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getSuccess());

    }

    @Test
    @Transactional
    public void getCarIdList() {
        Integer customerId = 1;
        Integer carCategoryId = 1;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.minusWeeks(1);
        LocalDateTime endTime = now.plusWeeks(2);

        List<Integer> carIds =
                rentalOrderService.getAvailableCarIdList(carCategoryId,
                        startTime, endTime);
        Assert.assertNotNull(carIds);
        assertEquals(2, carIds.size());

        Result result = rentalOrderService.rentACar(carCategoryId, customerId, startTime, endTime);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getSuccess());

        carIds = rentalOrderService.getAvailableCarIdList(carCategoryId,
                        startTime, endTime);
        Assert.assertNotNull(carIds);
        assertEquals(1, carIds.size());

        result = rentalOrderService.rentACar(carCategoryId, customerId, startTime, endTime);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getSuccess());

        carIds = rentalOrderService.getAvailableCarIdList(carCategoryId,
                        startTime, endTime);
        Assert.assertNotNull(carIds);
        assertEquals(0, carIds.size());
    }

}