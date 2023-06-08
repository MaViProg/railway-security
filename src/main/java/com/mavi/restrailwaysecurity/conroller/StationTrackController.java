package com.mavi.restrailwaysecurity.conroller;

import com.mavi.restrailwaysecurity.entity.StationTrack;
import com.mavi.restrailwaysecurity.repository.StationTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/station-tracks")
//@ComponentScan("com.example.demo")
public class StationTrackController {

    @Autowired
    private StationTrackRepository stationTrackRepository;

    /**
     * getStationTracksById()
     * GET http://localhost:8084/api/station-tracks/id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public StationTrack getStationTrackById(@PathVariable Long id) {
        return stationTrackRepository.findById(id).orElse(null);
    }

    /**
     * getAllStationTracks()
     * GET http://localhost:8084/api/station-tracks
     *
     * @param
     * @return
     */
    @GetMapping
    public List<StationTrack> getAllStationTracks() {
        Iterable<StationTrack> stationTracks = stationTrackRepository.findAll();
        List<StationTrack> list = new ArrayList<>();
        for (StationTrack stationTrack : stationTracks) {
            list.add(stationTrack);
        }
        return list;
    }

    /**
     * createStationTrack()
     * POST http://localhost:8084/api/station-tracks
     *
     * @param stationTrack
     * @return
     */
    @PostMapping
    public StationTrack createStationTrack(@RequestBody StationTrack stationTrack) {
        return stationTrackRepository.save(stationTrack);
    }

    /**
     * updateStationTrack
     * PUT http://localhost:8084/api/station-tracks/id
     *
     * @param id
     * @param stationTrack
     * @return
     */
    @PutMapping("/{id}")
    public StationTrack updateStationTrack(@PathVariable Long id, @RequestBody StationTrack stationTrack) {
        stationTrack.setId(id);
        return stationTrackRepository.save(stationTrack);
    }

    /**
     * deleteStationTrack()
     * DELETE BY ID http://localhost:8084/api/station-tracks/id
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteStationTrack(@PathVariable Long id) {
        stationTrackRepository.deleteById(id);
    }

    /**
     * deleteAllStationTracks()
     * DELETE http://localhost:8084/api/station-tracks
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllStationTracks() {
        stationTrackRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
