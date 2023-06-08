package com.mavi.restrailwaysecurity.conroller;

import com.mavi.restrailwaysecurity.entity.Waybill;
import com.mavi.restrailwaysecurity.repository.WaybillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waybills")
public class WaybillController {

    private final WaybillRepository repository;

    public WaybillController(WaybillRepository repository) {
        this.repository = repository;
    }

    /**
     * getAllWaybills()
     * GET http://localhost:8084/api/waybills
     *
     * @return
     */
    @GetMapping
    public List<Waybill> getAllWaybills() {
        return repository.findAll();
    }

    /**
     * getWaybillById()
     * GET http://localhost:8084/api/waybills/id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Waybill getWaybillById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Waybill not found with id " + id));
    }

    /**
     * createWaybill()
     * POST http://localhost:8084/api/waybills
     *
     * @param waybill
     * @return
     */
    @PostMapping
    public Waybill createWaybill(@RequestBody Waybill waybill) {
        return repository.save(waybill);
    }

    /**
     * updateWaybill()
     * PUT http://localhost:8084/api/waybills/id
     *
     * @param newWaybill
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Waybill updateWaybill(@RequestBody Waybill newWaybill, @PathVariable Long id) {
        return repository.findById(id)
                .map(waybill -> {
                    waybill.setCargo(newWaybill.getCargo());
                    waybill.setCargoWeight(newWaybill.getCargoWeight());
                    waybill.setWagonWeight(newWaybill.getWagonWeight());
                    return repository.save(waybill);
                })
                .orElseGet(() -> {
                    newWaybill.setId(id);
                    return repository.save(newWaybill);
                });
    }

    /**
     * deleteWaybill()
     * DELETE http://localhost:8084/api/waybills/id
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteWaybill(@PathVariable Long id) {
        repository.deleteById(id);
    }

    /**
     * deleteAllWaybills()
     * DELETE http://localhost:8084/api/waybills
     *
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllWaybills() {
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}

