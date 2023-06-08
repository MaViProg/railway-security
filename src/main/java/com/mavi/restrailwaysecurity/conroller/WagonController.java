package com.mavi.restrailwaysecurity.conroller;

import com.mavi.restrailwaysecurity.entity.Wagon;
import com.mavi.restrailwaysecurity.repository.WagonRepository;
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

    /**
     * GET a single wagon by ID
     * GET http://localhost:8084/api/wagons/id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Wagon> getWagonById(@PathVariable Long id) {
        Optional<Wagon> wagonOptional = wagonRepository.findById(id);
        return ResponseEntity.notFound().build();
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

}
