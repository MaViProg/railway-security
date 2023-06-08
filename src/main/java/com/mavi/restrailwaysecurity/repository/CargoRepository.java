package com.mavi.restrailwaysecurity.repository;

import com.mavi.restrailwaysecurity.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
