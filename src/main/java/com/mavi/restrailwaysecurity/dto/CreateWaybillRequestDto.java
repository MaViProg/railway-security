package com.mavi.restrailwaysecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateWaybillRequestDto {

    private Double cargoWeight;

    private Double wagonWeight;

    private Integer serialNumber;

    private String wagonNumber;

    private Long cargoId;


}
