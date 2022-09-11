package com.demo.carrental.service.impl;

import com.demo.carrental.common.ErrorCode;
import com.demo.carrental.common.Result;
import com.demo.carrental.entity.RentalOrder;
import com.demo.carrental.mapper.RentalOrderMapper;
import com.demo.carrental.service.IRentalOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * car rental order table 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
@Service
public class RentalOrderServiceImpl extends ServiceImpl<RentalOrderMapper, RentalOrder> implements IRentalOrderService {


    @Override
    public List<Integer> getAvailableCarIdList(Integer categoryId, LocalDateTime startTime, LocalDateTime endTime){
        return this.baseMapper.getRangeList(categoryId, startTime, endTime);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result rentACar(Integer categoryId, Integer customerId,
                            LocalDateTime startTime, LocalDateTime endTime){
        List<Integer> carIds = getAvailableCarIdList(categoryId, startTime, endTime);
        if (carIds.isEmpty()){
            return Result.fail("no available car of the chosen car model");
        }
        RentalOrder order = new RentalOrder();
        order.setCarId(carIds.get(0));
        order.setCategoryId(categoryId);
        order.setCustomerId(customerId);
        order.setStartTime(startTime);
        order.setEndTime(endTime);
        int res = this.baseMapper.insert(order);
        if (res > 0){
            return Result.success();
        }
        return Result.fail(ErrorCode.NORMAL_ERROR);
    }
}
