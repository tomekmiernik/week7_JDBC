package com.example.week7.dao;

import com.example.week7.model.Car;

import java.util.Collection;

public interface CarDao {
    void saveCar(long id, String mark, String model, String color, String productionYear);
    Collection<Car> findAll();
    Collection<Car> findByProductionDate(String sinceDate, String fromDate);
}
