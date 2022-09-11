package com.demo.carrental.service;

import com.demo.carrental.common.Result;
import com.demo.carrental.entity.Car;
import com.demo.carrental.entity.RentalOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * car rental order table 服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
public interface IRentalOrderService extends IService<RentalOrder> {

    List<Integer> getAvailableCarIdList(Integer categoryId, LocalDateTime startTime, LocalDateTime endTime);

    Result rentACar(Integer categoryId, Integer customerId, LocalDateTime startTime, LocalDateTime endTime);

    Result getOrderList(Integer customerId);
}
