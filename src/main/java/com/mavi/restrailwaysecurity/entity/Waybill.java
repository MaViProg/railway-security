package com.mavi.restrailwaysecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Натурный лист для приема вагонов
 * Список вагонов с атрибутами:
 * Порядковый номер
 * Номер вагона
 * Номенклатура груза
 * Вес груза в вагоне
 * Вес вагона
 * В каждой накладной может быть только один груз, но каждый груз может присутствовать в нескольких накладных.
 * Отношение @ManyToOne между объектами Waybill и Cargo.
 */

@Entity
@Table(name = "waybill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Waybill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;

    @NotNull
    @Column(name = "cargo_weight", nullable = false)
    private Double cargoWeight;

    @NotNull
    @Column(name = "wagon_weight", nullable = false)
    private Double wagonWeight;

    @NotNull
    @Column(name = "serial_number", nullable = false)
    private Integer serialNumber;

    @NotNull
    @Column(name = "wagon_number", nullable = false)
    private String wagonNumber;

    public Waybill(Cargo cargo) {
        this.cargo = cargo;
        this.cargoWeight = 0.0;
        this.wagonWeight = 0.0;
    }

    @Override
    public String toString() {
        return "Waybill{" +
                "id=" + id +
                ", cargo=" + cargo +
                ", cargoWeight=" + cargoWeight +
                ", wagonWeight=" + wagonWeight +
                ", serialNumber=" + serialNumber +
                ", wagonNumber='" + wagonNumber + '\'' +
                '}';
    }

}



