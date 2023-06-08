package com.mavi.restrailwaysecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "station_track")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
   public class StationTrack {
   
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
   
       @Column(nullable = false)
       private String name;
   
       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "station_model_id", nullable = false)
       private StationModel stationModel;
   
       @OneToMany(mappedBy = "stationTrack")
       private Set<Wagon> wagons;

   }
