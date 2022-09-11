package com.demo.carrental.service.impl;

import com.demo.carrental.entity.Car;
import com.demo.carrental.mapper.CarMapper;
import com.demo.carrental.service.ICarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * car table 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

}
