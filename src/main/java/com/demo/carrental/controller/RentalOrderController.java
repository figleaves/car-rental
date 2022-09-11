package com.demo.carrental.controller;


import com.demo.carrental.common.ErrorCode;
import com.demo.carrental.common.Result;
import com.demo.carrental.model.RentalRequest;
import com.demo.carrental.service.IRentalOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * car rental order table 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
@RestController
public class RentalOrderController extends BaseController {

    @Resource
    private IRentalOrderService rentalOrderService;

    @PostMapping("/rent/car")

    public Result rentACar(@RequestBody @Validated RentalRequest request){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime maxTime = now.plusMonths(1);

        LocalDateTime startTime = request.getStartTime();
        if (startTime.isBefore(now) || startTime.isAfter(maxTime)){
            return Result.fail(ErrorCode.INVALID_PARAMS, "start time should be in one month");
        }
        LocalDateTime endTime = request.getEndTime();
        if (endTime.isBefore(now) || endTime.isAfter(maxTime)){
            return Result.fail(ErrorCode.INVALID_PARAMS, "end time should be in one month");
        }

        return rentalOrderService.rentACar(
                request.getCarCategoryId(),
                request.getCustomerId(),
                startTime, endTime);

    }
}
