package com.mavi.restrailwaysecurity.conroller;

import com.mavi.restrailwaysecurity.entity.Cargo;
import com.mavi.restrailwaysecurity.exceptions.EntityNotFoundException;
import com.mavi.restrailwaysecurity.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;

    /**
     * createCargo()
     * POST http://localhost:8084/api/cargos
     *
     * @param cargo
     * @return
     */
    @PostMapping
    public Cargo createCargo(@RequestBody Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    /**
     * getCargoById()
     * GET http://localhost:8084/api/cargos/id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Cargo getCargoById(@PathVariable Long id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo not found"));
    }

    /**
     * getAllCargos()
     * GET http://localhost:8084/api/cargos
     *
     * @return
     */
    @GetMapping
    public List<Cargo> getAllCargos() {
        return cargoRepository.findAll();
    }

    /**
     * updateCargo()
     * PUT http://localhost:8084/api/cargos/id
     *
     * @param id
     * @param updatedCargo
     * @return
     */
    @PutMapping("/{id}")
    public Cargo updateCargo(@PathVariable Long id, @RequestBody Cargo updatedCargo) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo not found"));
        cargo.setCode(updatedCargo.getCode());
        cargo.setName(updatedCargo.getName());
        return cargoRepository.save(cargo);
    }

    /**
     * deleteCargoById()
     * DELETE BY ID http://localhost:8084/api/cargos/id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCargoById(@PathVariable Long id) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo not found"));
        cargoRepository.delete(cargo);
        return ResponseEntity.ok().build();
    }

}
