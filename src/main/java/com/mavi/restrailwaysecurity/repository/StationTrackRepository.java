package com.mavi.restrailwaysecurity.repository;

import com.mavi.restrailwaysecurity.entity.StationTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationTrackRepository extends JpaRepository<StationTrack, Long> {

}
