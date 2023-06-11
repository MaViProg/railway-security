package com.mavi.restrailwaysecurity.conroller;

import com.mavi.restrailwaysecurity.dto.CreateStationTrackRequestDTO;
import com.mavi.restrailwaysecurity.dto.CreateStationTrackResponseDTO;
import com.mavi.restrailwaysecurity.dto.UpdateStationTrackDTO;
import com.mavi.restrailwaysecurity.entity.StationModel;
import com.mavi.restrailwaysecurity.entity.StationTrack;
import com.mavi.restrailwaysecurity.entity.Wagon;
import com.mavi.restrailwaysecurity.exceptions.EntityNotFoundException;
import com.mavi.restrailwaysecurity.repository.StationModelRepository;
import com.mavi.restrailwaysecurity.repository.StationTrackRepository;
import com.mavi.restrailwaysecurity.service.StationTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station-tracks")
public class StationTrackController {

    @Autowired
    private StationTrackRepository stationTrackRepository;

    @Autowired
    StationModelRepository stationModelRepository;

    @Autowired
    StationTrackService stationTrackService;


    /**
     * getStationTracksById()
     * GET http://localhost:8084/api/station-tracks/id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public StationTrack getStationTrackById(@PathVariable Long id) {
        return stationTrackRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Waybill not found with id " + id));
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
        return stationTrackRepository.findAll();
    }

    /**
     *
     * @param stationTrack
     * @return
     */

    /**
     * ERROR
     * createStationTrack()
     * POST http://localhost:8084/api/station-tracks
     *
     * @param
     * @return успешный ответ с созданной записью StationTrack
     */

    //deserelization from DTO to Entity
    @PostMapping
    public CreateStationTrackResponseDTO createStationTrack(@RequestBody CreateStationTrackRequestDTO createStationTrackRequestDTO) {
        StationTrack stationTrack = new StationTrack();

        //берем вес у DTO и присваиваем его сущности
        stationTrack.setName(createStationTrackRequestDTO.getName());
        //TODO Rename stationTrack to Wagons
        stationTrack.setWagons(createStationTrackRequestDTO.getStationTrack());

        Wagon wagon = new Wagon();
        wagon.setId(createStationTrackRequestDTO.getStationModelId());

        StationTrack stationTrack2 = stationTrackService.createStationTrack(stationTrack);
        CreateStationTrackResponseDTO createStationTrackResponseDTO = new CreateStationTrackResponseDTO();
        createStationTrackResponseDTO.setName(stationTrack2.getName());
        createStationTrackResponseDTO.setId(stationTrack2.getId());
        createStationTrackResponseDTO.setStationModelId(stationTrack2.getStationModel().getId());
        return createStationTrackResponseDTO;

    }


    /**
     * Обновила этот метод!
     * updateStationTrack
     * PUT http://localhost:8084/api/station-tracks/id
     *
     * @param id
     * @param
     * @return
     */
    //TODO Какой точно запрос в Postman?
    @PutMapping("/{id}")
    public StationTrack updateStationTrack(@RequestBody UpdateStationTrackDTO stationTrackDTO, @PathVariable Long id) {
        StationTrack updateStationTrack = new StationTrack();
        updateStationTrack.setId(id);
        updateStationTrack.setName(stationTrackDTO.getName());
        StationModel stationModel = new StationModel();
        stationModel.setId(stationTrackDTO.getStationModelId());

        updateStationTrack.setStationModel(stationModel);

        return stationTrackRepository.save(updateStationTrack);

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
     *
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllStationTracks() {
        stationTrackRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
