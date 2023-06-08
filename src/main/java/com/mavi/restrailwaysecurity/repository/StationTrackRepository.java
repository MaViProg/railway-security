package com.mavi.restrailwaysecurity.repository;

import com.mavi.restrailwaysecurity.entity.StationTrack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationTrackRepository extends CrudRepository<StationTrack, Long> {

}
