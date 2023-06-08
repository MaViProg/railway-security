package com.mavi.restrailwaysecurity.conroller;

import com.mavi.restrailwaysecurity.entity.StationModel;
import com.mavi.restrailwaysecurity.repository.StationModelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station-models")
public class StationModelController {

    private final StationModelRepository stationModelRepository;

    public StationModelController(StationModelRepository stationModelRepository) {
        this.stationModelRepository = stationModelRepository;
    }

    @GetMapping
    public List<StationModel> getAllStationModels() {
        return stationModelRepository.findAll();
    }

    @GetMapping("/{id}")
    public StationModel getStationModelById(@PathVariable Long id) {
        return stationModelRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public StationModel createStationModel(@RequestBody StationModel stationModel) {
        return stationModelRepository.save(stationModel);
    }

    @PutMapping("/{id}")
    public StationModel updateStationModel(@PathVariable Long id, @RequestBody StationModel stationModel) {
        stationModel.setId(id);
        return stationModelRepository.save(stationModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStationModel(@PathVariable Long id) {
        stationModelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllWagons() {
        stationModelRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
