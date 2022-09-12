package com.demo.carrental.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel
@Data
public class RentalRequest  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "car category", required = true)
    @NotNull(message="carCategoryId cannot be null")
    private Integer carCategoryId;

    @ApiModelProperty(value = "utc datetime yyyy-MM-dd HH:mm:ss", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    @NotNull(message = "startTime cannot be null")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "utc datetime yyyy-MM-dd HH:mm:ss", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    @NotNull(message = "endTime cannot be null")
    private LocalDateTime endTime;
}
