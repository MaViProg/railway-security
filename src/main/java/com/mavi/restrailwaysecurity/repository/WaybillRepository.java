package com.mavi.restrailwaysecurity.repository;

import com.mavi.restrailwaysecurity.entity.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaybillRepository extends JpaRepository<Waybill, Long> {
   }
