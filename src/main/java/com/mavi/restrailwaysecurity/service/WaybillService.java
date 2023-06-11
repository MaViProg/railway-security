package com.mavi.restrailwaysecurity.service;

import com.mavi.restrailwaysecurity.entity.Cargo;
import com.mavi.restrailwaysecurity.entity.Waybill;
import com.mavi.restrailwaysecurity.repository.WagonRepository;
import com.mavi.restrailwaysecurity.repository.WaybillRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class WaybillService {

    @Autowired
    private WaybillRepository waybillRepository;

    @Autowired
    WagonRepository wagonRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * Method createWaybill:
     * Receives a cargoId parameter, and creates a new waybill with the specified cargo.
     *
     * @param
     */

    public Waybill createWaybill(Waybill waybill) {
        return waybillRepository.save(waybill);
    }


        @Transactional
        public void saveWaybill() {
            Waybill waybill = new Waybill();
            waybill.setWagonNumber("ABC123");
            waybill.setCargoWeight(1000.0);
            waybill.setCargo(new Cargo());
            waybill.setWagonWeight(5000.0);
            waybill.setSerialNumber(75);
            entityManager.persist(waybill);

        }
    }







