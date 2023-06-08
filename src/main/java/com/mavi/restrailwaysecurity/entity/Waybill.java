package com.mavi.restrailwaysecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Waybill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "waybill")
    private List<Wagon> wagons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @NotNull
    @Column(name = "cargo_weight", nullable = false)
    private double cargoWeight;

    @NotNull
    @Column(name = "wagon_weight", nullable = false)
    private double wagonWeight;

}
