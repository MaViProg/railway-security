package com.mavi.restrailwaysecurity.dto;

import com.mavi.restrailwaysecurity.entity.Wagon;
import com.mavi.restrailwaysecurity.entity.WagonMovePosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoveWagonsRequestDTO {

    private List<Wagon> wagons;

    private Long stationTrackId;

    private WagonMovePosition position;


}
