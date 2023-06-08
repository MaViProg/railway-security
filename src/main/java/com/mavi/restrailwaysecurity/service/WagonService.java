package com.mavi.restrailwaysecurity.service;

import com.mavi.restrailwaysecurity.entity.StationTrack;
import com.mavi.restrailwaysecurity.entity.Wagon;
import com.mavi.restrailwaysecurity.entity.Waybill;
import com.mavi.restrailwaysecurity.repository.StationTrackRepository;
import com.mavi.restrailwaysecurity.repository.WagonRepository;
import com.mavi.restrailwaysecurity.repository.WaybillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WagonService {

    @Autowired
    private WagonRepository wagonRepository;

    @Autowired
    private StationTrackRepository stationTrackRepository;

    @Autowired
    private WaybillRepository waybillRepository;

    /**
     * Method receiveWagons:
     * Receives a list of wagons and a stationTrackId parameter,
     * and adds the wagons to the end of the train on the specified station track.
     * @param wagons
     * @param stationTrackId
     */
    public void receiveWagons(List<Wagon> wagons, Long stationTrackId) {
        StationTrack stationTrack = stationTrackRepository.findById(stationTrackId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid station track ID"));

        // Check if wagons can be received at the end of the train
        Wagon lastWagon = wagonRepository.findTopByStationTrackOrderByPositionDesc(stationTrack);
        int position = lastWagon != null ? lastWagon.getPosition() + 1 : 0;

        // Add wagons to the end of the train
        for (Wagon wagon : wagons) {
            wagon.setStationTrack(stationTrack);
            wagon.setPosition(position++);
            wagonRepository.save(wagon);
        }
    }

    /**
     * Method moveWagons:
     * Receives a list of wagons and a stationTrackId parameter,
     * and moves the wagons to the beginning or end of the train on the specified station track.
     * @param wagons
     * @param stationTrackId
     */
    public void moveWagons(List<Wagon> wagons, Long stationTrackId) {
        StationTrack stationTrack = stationTrackRepository.findById(stationTrackId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid station track ID"));

        // Check if wagons can be moved to the beginning or end of the train
        Wagon firstWagon = wagonRepository.findTopByStationTrackOrderByPositionAsc(stationTrack);
        Wagon lastWagon = wagonRepository.findTopByStationTrackOrderByPositionDesc(stationTrack);
        int position = firstWagon != null ? firstWagon.getPosition() - 1 : 0;

        // Move wagons to the beginning or end of the train
        for (Wagon wagon : wagons) {
            wagon.setStationTrack(stationTrack);
            wagon.setPosition(position--);
            wagonRepository.save(wagon);
        }
    }

    /**
     * Method departWagons:
     * Receives a waybillId parameter, and removes all wagons from the beginning of the train in the specified waybill.
     * @param waybillId
     */
    public void departWagons(Long waybillId) {
        Waybill waybill = waybillRepository.findById(waybillId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid waybill ID"));

        // Check if wagons can be departed from the beginning of the train
        Wagon firstWagon = wagonRepository.findTopByWaybillOrderByPositionAsc(waybill);
        if (firstWagon == null) {
            throw new IllegalStateException("No wagons found in waybill");
        }

        // Remove wagons from the beginning of the train
        wagonRepository.deleteAllByWaybill(waybill);
    }
}
