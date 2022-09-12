package com.demo.carrental.controller;


import com.demo.carrental.common.Result;
import com.demo.carrental.entity.CarCategory;
import com.demo.carrental.service.ICarCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * car category table 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
@Api("Car Category Api")
@RestController
@RequestMapping(value = "/carRental")
public class CarCategoryController extends BaseController {

    @Resource
    private ICarCategoryService carCategoryService;

    @ApiOperation(value="Get All Car Category", notes="get all the car category list")
    @GetMapping("/carCategory/list")
    public Result<List<CarCategory>> getCarCategoryList(){
        return carCategoryService.getCarCategoryList();
    }
}
