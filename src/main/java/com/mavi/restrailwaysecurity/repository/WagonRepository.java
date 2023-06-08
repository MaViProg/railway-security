package com.mavi.restrailwaysecurity.repository;

import com.mavi.restrailwaysecurity.entity.StationTrack;
import com.mavi.restrailwaysecurity.entity.Wagon;
import com.mavi.restrailwaysecurity.entity.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WagonRepository extends JpaRepository<Wagon, Long> {

    Wagon findTopByStationTrackOrderByPositionDesc(StationTrack stationTrack);

    Wagon findTopByStationTrackOrderByPositionAsc(StationTrack stationTrack);

    Wagon findTopByWaybillOrderByPositionAsc(Waybill waybill);

    void deleteAllByWaybill(Waybill waybill);

    List<Wagon> findByWaybillOrderByPositionAsc(Waybill waybill);

    List<Wagon> findByStationTrackOrderByPositionAsc(StationTrack stationTrack);
}


