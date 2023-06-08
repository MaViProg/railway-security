package com.mavi.restrailwaysecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Set;

@Entity
@Table(name = "station")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
   public class StationModel {
   
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
   
       @Column(nullable = false)
       private String name;
   
       @OneToMany(mappedBy = "stationModel")
       private Set<StationTrack> stationTracks;

   }
