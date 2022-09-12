package com.demo.carrental.service.impl;

import com.demo.carrental.common.Result;
import com.demo.carrental.entity.Car;
import com.demo.carrental.mapper.CarCategoryMapper;
import com.demo.carrental.service.ICarCategoryService;
import com.demo.carrental.entity.CarCategory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * car category table 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
@Service
public class CarCategoryServiceImpl extends ServiceImpl<CarCategoryMapper, CarCategory> implements ICarCategoryService {

    @Override
    public Result<List<CarCategory>> getCarCategoryList() {
        QueryWrapper<CarCategory> queryWrapper = new QueryWrapper<>();
        List<CarCategory> list = this.baseMapper.selectList(queryWrapper);
        return Result.success(list);
    }
}
