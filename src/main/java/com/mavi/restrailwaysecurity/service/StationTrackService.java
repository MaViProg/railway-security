package com.mavi.restrailwaysecurity.service;

import com.mavi.restrailwaysecurity.entity.StationTrack;
import com.mavi.restrailwaysecurity.repository.StationTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationTrackService {

    @Autowired
    StationTrackRepository stationTrackRepository;

    public StationTrack createStationTrack (StationTrack stationTrack) {
        return stationTrackRepository.save(stationTrack);
    }
}


