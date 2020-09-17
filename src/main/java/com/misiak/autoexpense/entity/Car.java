package com.misiak.autoexpense.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String make;

    private String model;

    @Column(name = "production_year")
    private int productionYear;

    private double mileage;

    @Column(name = "base_price")
    private BigDecimal basePrice;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "engine_id")
    private Engine engine;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<FuelExpense> fuelExpenses;
}
