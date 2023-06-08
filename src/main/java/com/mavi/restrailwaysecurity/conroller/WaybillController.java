package com.mavi.restrailwaysecurity.conroller;

import com.mavi.restrailwaysecurity.entity.Waybill;
import com.mavi.restrailwaysecurity.repository.WaybillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WaybillController {

    private final WaybillRepository repository;

    public WaybillController(WaybillRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/waybills")
    public List<Waybill> getAllWaybills() {
        return repository.findAll();
    }

    @GetMapping("/waybills/{id}")
    public Waybill getWaybillById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Waybill not found with id " + id));
    }

    @PostMapping("/waybills")
    public Waybill createWaybill(@RequestBody Waybill waybill) {
        return repository.save(waybill);
    }

    @PutMapping("/waybills/{id}")
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

    @DeleteMapping("/waybills/{id}")
    public void deleteWaybill(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

