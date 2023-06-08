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

    /**
     * getAllStationModels()
     * GET http://localhost:8084/api/station-models
     *
     * @return
     */
    @GetMapping
    public List<StationModel> getAllStationModels() {
        return stationModelRepository.findAll();
    }

    /**
     * getStationModelById()
     * GET http://localhost:8084/api/station-models/id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public StationModel getStationModelById(@PathVariable Long id) {
        return stationModelRepository.findById(id).orElseThrow();
    }

    /**
     * createStationModel()
     * POST http://localhost:8084/api/station-models
     *
     * @param stationModel
     * @return
     */
    @PostMapping
    public StationModel createStationModel(@RequestBody StationModel stationModel) {
        return stationModelRepository.save(stationModel);
    }

    /**
     * updateStationModel()
     * PUT http://localhost:8084/api/station-models/id
     *
     * @param id
     * @param stationModel
     * @return
     */
    @PutMapping("/{id}")
    public StationModel updateStationModel(@PathVariable Long id, @RequestBody StationModel stationModel) {
        stationModel.setId(id);
        return stationModelRepository.save(stationModel);
    }

    /**
     * deleteStationModel()
     * DELETE http://localhost:8084/api/station-models/id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStationModel(@PathVariable Long id) {
        stationModelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * deleteAllStationModel()
     * DELETE http://localhost:8084/api/station-models
     *
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllStationModel() {
        stationModelRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
