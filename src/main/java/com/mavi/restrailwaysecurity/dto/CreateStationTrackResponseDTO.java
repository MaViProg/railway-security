package com.mavi.restrailwaysecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStationTrackResponseDTO {
    private Long id;
    private String name;
    private Long stationModelId;

}
