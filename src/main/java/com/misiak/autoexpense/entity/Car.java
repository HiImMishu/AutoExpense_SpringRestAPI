package com.misiak.autoexpense.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("cars")
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private List<FuelExpense> fuelExpenses;
}
