package com.demo.carrental.service;

import com.demo.carrental.common.Result;
import com.demo.carrental.entity.CarCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * car category table 服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
public interface ICarCategoryService extends IService<CarCategory> {

    Result<List<CarCategory>> getCarCategoryList();
}
