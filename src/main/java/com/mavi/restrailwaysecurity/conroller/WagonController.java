package com.mavi.restrailwaysecurity.conroller;

import com.mavi.restrailwaysecurity.dto.ReceiveWagonsRequestDTO;
import com.mavi.restrailwaysecurity.entity.Wagon;
import com.mavi.restrailwaysecurity.repository.WagonRepository;
import com.mavi.restrailwaysecurity.service.WagonService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Wagon controller
 */
@RestController
@RequestMapping("/api/wagons")
public class WagonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WagonController.class);

    @Autowired
    private WagonRepository wagonRepository;

    @Autowired
    private WagonService wagonService;

    /**
     * GET a single wagon by ID
     * GET http://localhost:8084/api/wagons/id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Wagon getWagonById(@PathVariable Long id) {
        return wagonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wagon not found with id " + id));
    }

    /**
     * getAllWagons()
     * GET http://localhost:8084/api/wagons
     *
     * @return
     */
    @GetMapping
    public List<Wagon> getAllWagons() {
        return wagonRepository.findAll();
    }

    /**
     * CREATE a new wagon
     * POST http://localhost:8084/api/wagons
     *
     * @param wagon
     * @return
     */
    @PostMapping
    public Wagon createWagon(@Valid @RequestBody Wagon wagon) {
        return wagonRepository.save(wagon);
    }

    /**
     * UPDATE an existing wagon by ID
     * PUT http://localhost:8084/api/wagons/id
     *
     * @param id
     * @param updatedWagon
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Wagon> updateWagon(@PathVariable Long id, @RequestBody Wagon updatedWagon) {
        Optional<Wagon> wagonOptional = wagonRepository.findById(id);
        if (wagonOptional.isPresent()) {
            Wagon wagon = wagonOptional.get();
            wagon.setNumber(updatedWagon.getNumber());
            wagon.setType(updatedWagon.getType());
            wagon.setTareWeight(updatedWagon.getTareWeight());
            wagon.setLoadCapacity(updatedWagon.getLoadCapacity());
            Wagon savedWagon = wagonRepository.save(wagon);
            return ResponseEntity.ok(savedWagon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE an existing wagon by ID
     * DELETE http://localhost:8084/wagons/id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWagon(@PathVariable Long id) {
        Optional<Wagon> wagonOptional = wagonRepository.findById(id);
        if (wagonOptional.isPresent()) {
            wagonRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE all wagons
     * DELETE http://localhost:8084/wagons
     *
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllWagons() {
        wagonRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    /**
     * Операция приема вагонов на предприятие.
     * На входе список вагонов с учетом на какой путь станции данные вагоны принимаются.
     * Вагоны могут приниматься только в конец состава.
     * http://localhost:8083/api/wagons/receive
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/receive")
    public ResponseEntity<String> receiveWagons(@RequestBody ReceiveWagonsRequestDTO receiveWagonsRequestDTO) {
        wagonService.receiveWagons(receiveWagonsRequestDTO.getWagonsId(), receiveWagonsRequestDTO.getStationTrackId());

        return ResponseEntity.ok("Wagons received successfully");
    }

    /**
     * Операция перестановки вагонов внутри станции.
     * На входе список вагонов и путь на который они будут перемещены.
     * Вагоны могут быть перемещены только в начало или конец состава.
     *
     * @param
     * @param stationTrackId
     * @return
     */
//    @PostMapping("/move")
//    public ResponseEntity<String> moveWagons(@RequestBody MoveWagonsRequestDTO) {
//        wagonService.moveWagons(wagons, stationTrackId);
//        return ResponseEntity.ok("Wagons moved successfully");
//    }


    /**
     * Операция убытия вагонов на сеть РЖД.
     * Вагоны могут убывать только с начала состава.
     * waybillId} - фактический идентификатор накладной
     *
     * POST http://localhost:8083/api/wagons/depart-waybills/1
     *
     * @param waybillId
     * @return
     */

    @GetMapping("/depart-waybills/{waybillId}")
    public ResponseEntity<String> departWagons(@PathVariable Long waybillId) {
        wagonService.departWagons(waybillId);

        return ResponseEntity.ok("Wagons departed successfully");
    }
}


