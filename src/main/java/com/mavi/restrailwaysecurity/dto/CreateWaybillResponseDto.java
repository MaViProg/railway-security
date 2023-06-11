package com.mavi.restrailwaysecurity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWaybillResponseDto {

    private Long id;

    private Double cargoWeight;

    private Double wagonWeight;

    private Integer serialNumber;

    private String wagonNumber;

    private Long cargoId;
}
