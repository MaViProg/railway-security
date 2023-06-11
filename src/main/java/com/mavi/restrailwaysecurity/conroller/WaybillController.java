package com.mavi.restrailwaysecurity.conroller;

import com.mavi.restrailwaysecurity.dto.CreateWaybillRequestDto;
import com.mavi.restrailwaysecurity.dto.CreateWaybillResponseDto;
import com.mavi.restrailwaysecurity.dto.UpdateWaybillDto;
import com.mavi.restrailwaysecurity.entity.Cargo;
import com.mavi.restrailwaysecurity.entity.Waybill;
import com.mavi.restrailwaysecurity.repository.WaybillRepository;
import com.mavi.restrailwaysecurity.service.WagonService;
import com.mavi.restrailwaysecurity.service.WaybillService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/waybills")
public class WaybillController {

    @Autowired
    private WaybillRepository waybillRepository;

    @Autowired
    WaybillService waybillService;

    @Autowired
    WagonService wagonService;


    /**
     * getAllWaybills()
     * GET http://localhost:8084/api/waybills
     *
     * @return
     */
    @GetMapping
    public List<Waybill> getAllWaybills() {
        return waybillRepository.findAll();
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
        return waybillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Waybill not found with id " + id));
    }

    /**
     * createWaybill()
     * POST http://localhost:8083/api/waybills
     *
     * @param
     * @return
     */
    @PostMapping
    public CreateWaybillResponseDto createWaybill(@RequestBody CreateWaybillRequestDto createWaybillRequestDto) {

        Waybill waybill = new Waybill();

        //берем вес у DTO и присваиваем его сущности
        waybill.setCargoWeight(createWaybillRequestDto.getCargoWeight());
        waybill.setWagonWeight(createWaybillRequestDto.getWagonWeight());
        waybill.setSerialNumber(createWaybillRequestDto.getSerialNumber());
        waybill.setWagonNumber(createWaybillRequestDto.getWagonNumber());

        Cargo cargo = new Cargo();

        cargo.setId(createWaybillRequestDto.getCargoId());
        waybill.setCargo(cargo);

        Waybill waybill2 = waybillService.createWaybill(waybill);
        CreateWaybillResponseDto createWaybillResponseDto = new CreateWaybillResponseDto();
        createWaybillResponseDto.setCargoWeight(waybill2.getCargoWeight());
        createWaybillResponseDto.setWagonWeight(waybill2.getWagonWeight());
        createWaybillResponseDto.setSerialNumber(waybill2.getSerialNumber());
        createWaybillResponseDto.setWagonNumber(waybill2.getWagonNumber());
        createWaybillResponseDto.setCargoId(waybill2.getCargo().getId());
        createWaybillResponseDto.setId(waybill2.getId());

        return createWaybillResponseDto;
    }


    /**
     * updateWaybill()
     * PUT http://localhost:8084/api/waybills/id
     *
     * @param
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Waybill updateWaybill(@RequestBody UpdateWaybillDto updateWaybillDto,
                                 @PathVariable Long id) {

        Waybill waybill = new Waybill();
        waybill.setId(id);
        waybill.setCargoWeight(updateWaybillDto.getCargoWeight());
        waybill.setWagonWeight(updateWaybillDto.getWagonWeight());
        waybill.setSerialNumber(updateWaybillDto.getSerialNumber());
        waybill.setWagonNumber(updateWaybillDto.getWagonNumber());

        Cargo cargo = new Cargo();
        cargo.setId(updateWaybillDto.getCargoId());

        waybill.setCargo(cargo);
        return waybillRepository.save(waybill);
    }

    /**
     * deleteWaybill()
     * DELETE http://localhost:8084/api/waybills/id
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteWaybill(@PathVariable Long id) {
        waybillRepository.deleteById(id);
    }

}




