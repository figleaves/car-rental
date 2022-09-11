package com.demo.carrental.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RentalRequest  implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message="customerId cannot be null")
    private Integer customerId;

    @NotNull(message="carCategoryId cannot be null")
    private Integer carCategoryId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    @NotNull(message = "startTime cannot be null")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    @NotNull(message = "endTime cannot be null")
    private LocalDateTime endTime;
}
