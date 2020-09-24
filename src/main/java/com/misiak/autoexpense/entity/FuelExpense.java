package com.misiak.autoexpense.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fuel_expense")
public class FuelExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal price;

    private double litres;

    private double milage;

    @Column(name = "average_cost")
    private BigDecimal averageCost;

    @Column(name = "average_consumption")
    private BigDecimal averageConsumption;

    @ManyToOne
    @JsonIgnore
    private Car car;

    @Column(name = "expense_date")
    private Timestamp expenseDate;
}
