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
     Операция приема вагонов на предприятие.
     На входе список вагонов с учетом на какой путь станции данные вагоны принимаются.
     Вагоны могут приниматься только в конец состава.
     * @param
     * @param stationTrackId
     */
    public void receiveWagons(List<Long> wagonsId, Long stationTrackId) {

        StationTrack stationTrack = stationTrackRepository.findById(stationTrackId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid station track ID"));

        // Check if wagons can be received at the end of the train
        Wagon lastWagon = wagonRepository.findTopByStationTrackOrderByPositionDesc(stationTrack);
        int position = lastWagon != null ? lastWagon.getPosition() + 1 : 0;

        // Add wagons to the end of the train
        for (Wagon wagon : wagonRepository.findAllById(wagonsId)) {
            wagon.setStationTrack(stationTrack);
            wagon.setPosition(position++);
            wagonRepository.save(wagon);
        }
    }

    /**
     * Операция перестановки вагонов внутри станции.
     * На входе список вагонов и путь на который они будут перемещены.
     * Вагоны могут быть перемещены только в начало или конец состава.
     *
     * http://localhost:8083/api/wagons/move
     * @param wagons
     * @param stationTrackId
     */
//    public void moveWagons(List<Long> wagons, Long stationTrackId, WagonMovePosition position) {
//        StationTrack stationTrack = stationTrackRepository.findById(stationTrackId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid station track ID"));
//
//        Wagon firstWagon = stationTrack.getWagons().get(0);
//        Wagon lastWagon = stationTrack.getWagons().get(stationTrack.getWagons().size()-1);
//
//        int position =
//
//        for (Wagon wagon : wagonRepository.findAllById(wagons)) {
//            if (wagon == null || wagon.getNumber() == null || wagon.getNumber().isEmpty()) {
//                throw new IllegalArgumentException("Wagon number cannot be null or empty");
//            }
//            if (wagon.getType() == null || wagon.getType().isEmpty()) {
//                throw new IllegalArgumentException("Wagon type cannot be null or empty");
//            }
//            wagon.setStationTrack(stationTrack);
//            wagon.setPosition(position--);
//            wagonRepository.save(wagon);
//        }
//    }

    /**
     * Операция убытия вагонов на сеть РЖД.
     * Вагоны могут убывать только с начала состава.
     *
     *
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
        List<Wagon> wagons = wagonRepository.findAllByWaybillOrderByPositionAsc(waybill);
        for (Wagon wagon : wagons) {
            if (wagon.getStationTrack() == null) {
                throw new IllegalStateException("Wagon " + wagon.getNumber() + " does not have a station track");
            }
        }
        wagonRepository.deleteAllByWaybill(waybill);
    }


}
