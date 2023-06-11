package com.mavi.restrailwaysecurity.service;

import com.mavi.restrailwaysecurity.entity.StationModel;
import com.mavi.restrailwaysecurity.entity.StationTrack;
import com.mavi.restrailwaysecurity.repository.StationModelRepository;
import org.springframework.stereotype.Service;

@Service
public class StationService {

    private final StationModelRepository stationModelRepository;

    public StationService(StationModelRepository stationModelRepository) {
        this.stationModelRepository = stationModelRepository;
    }

    public StationModel createStationModelWithTracks() {
        StationModel stationModel = new StationModel();
        StationTrack stationTrack1 = new StationTrack("Example Station Track 1", stationModel);
        StationTrack stationTrack2 = new StationTrack("Example Station Track 2", stationModel);

        return stationModelRepository.save(stationModel);
    }



}
