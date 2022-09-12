package com.demo.carrental.controller;


import com.demo.carrental.common.ErrorCode;
import com.demo.carrental.common.RequestHolder;
import com.demo.carrental.common.Result;
import com.demo.carrental.entity.RentalOrder;
import com.demo.carrental.model.RentalRequest;
import com.demo.carrental.service.IRentalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * car rental order table 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
@Api("Rent Car Api")
@RestController
@RequestMapping(value = "/carRental")
public class RentalOrderController extends BaseController {

    @Resource
    private IRentalOrderService rentalOrderService;

    @ApiOperation(value="Rent A Car", notes="according range datetime rent a car of the selected car category")
    @PostMapping("/rent/car")
    public Result rentACar(@RequestBody @Validated RentalRequest rentalRequest){
        LocalDateTime startTime = rentalRequest.getStartTime();
        LocalDateTime endTime = rentalRequest.getEndTime();

        if (startTime.isAfter(endTime.minusDays(1))){
            return Result.fail(ErrorCode.INVALID_PARAMS, "start time should before end time more than 1 day");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime maxTime = now.plusMonths(1);

        if (startTime.isBefore(now) || startTime.isAfter(maxTime)){
            return Result.fail(ErrorCode.INVALID_PARAMS, "start time should be in one month");
        }

        if (endTime.isBefore(now) || endTime.isAfter(maxTime)){
            return Result.fail(ErrorCode.INVALID_PARAMS, "end time should be in one month");
        }

        Integer customerId = Integer.valueOf(RequestHolder.getUserId());

        return rentalOrderService.rentACar(
                rentalRequest.getCarCategoryId(),
                customerId,
                startTime, endTime);

    }

    @GetMapping("/rent/getOrders")
    public Result<List<RentalOrder>> getOrders(){
        Integer customerId = Integer.valueOf(RequestHolder.getUserId());
        return rentalOrderService.getOrderList(customerId);
    }
}
