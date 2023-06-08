package com.mavi.restrailwaysecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 Вагон: номер, тип, собственный вес и грузоподъемность
 У каждого вагона может быть только одна накладная, но у каждой накладной может быть несколько вагонов
 отношение @ManyToOne между сущностями «Накладная» и «Вагон».
 */

@Entity
@Table(name = "wagon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wagon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "number", nullable = false)
    private String number;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "tare_weight", nullable = false)
    private double tareWeight;

    @NotNull
    @Column(name = "load_capacity", nullable = false)
    private double loadCapacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_track_id", nullable = false)
    private StationTrack stationTrack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waybill_id")
    private Waybill waybill;

    @Column(name = "position", nullable = false)
    private int position;

    public Wagon(String number, String type, double tareWeight, double loadCapacity, StationTrack stationTrack) {
        this.number = number;
        this.type = type;
        this.tareWeight = tareWeight;
        this.loadCapacity = loadCapacity;
        this.stationTrack = stationTrack;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}



