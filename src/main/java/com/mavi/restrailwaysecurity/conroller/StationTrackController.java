package com.mavi.restrailwaysecurity.conroller;

import com.mavi.restrailwaysecurity.entity.StationTrack;
import com.mavi.restrailwaysecurity.repository.StationTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station-tracks")
//@ComponentScan("com.example.demo")
public class StationTrackController {

    @Autowired
    private StationTrackRepository stationTrackRepository;

    @GetMapping("/{id}")
    public StationTrack getStationTrackById(@PathVariable Long id) {
        return stationTrackRepository.findById(id).orElse(null);
    }

    @PostMapping
    public StationTrack createStationTrack(@RequestBody StationTrack stationTrack) {
        return stationTrackRepository.save(stationTrack);
    }

    @PutMapping("/{id}")
    public StationTrack updateStationTrack(@PathVariable Long id, @RequestBody StationTrack stationTrack) {
        stationTrack.setId(id);
        return stationTrackRepository.save(stationTrack);
    }

    @DeleteMapping("/{id}")
    public void deleteStationTrack(@PathVariable Long id) {
        stationTrackRepository.deleteById(id);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllWagons() {
        stationTrackRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
