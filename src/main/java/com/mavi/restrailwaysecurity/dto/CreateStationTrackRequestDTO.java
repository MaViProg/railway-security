package com.mavi.restrailwaysecurity.dto;

import com.mavi.restrailwaysecurity.entity.Wagon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateStationTrackRequestDTO {

    private String name;

    private Long stationModelId;

    private List<Wagon> stationTrack;
}
