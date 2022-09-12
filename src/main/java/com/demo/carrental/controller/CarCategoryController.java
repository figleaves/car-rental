package com.demo.carrental.controller;


import com.demo.carrental.common.Result;
import com.demo.carrental.service.ICarCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * car category table 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
@RestController
@RequestMapping(value = "/carRental")
public class CarCategoryController extends BaseController {

    @Resource
    private ICarCategoryService carCategoryService;

    @GetMapping("/carCategory/list")
    public Result getCarCategoryList(){
        return carCategoryService.getCarCategoryList();
    }
}
