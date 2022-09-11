package com.demo.carrental.mapper;

import com.demo.carrental.entity.RentalOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * car rental order table Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
public interface RentalOrderMapper extends BaseMapper<RentalOrder> {

    List<Integer> getRangeList(@Param("categoryId")Integer categoryId,
                               @Param("startTime") LocalDateTime startTime,
                               @Param("endTime")LocalDateTime endTime);
}
