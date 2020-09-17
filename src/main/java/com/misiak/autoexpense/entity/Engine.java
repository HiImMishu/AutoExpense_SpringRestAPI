package com.misiak.autoexpense.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double capacity;

    private double horsepower;

    private int cylinders;

    @OneToOne
    @JoinColumn(name = "engine_id")
    private Car car;
}
