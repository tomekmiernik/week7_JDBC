package com.example.week7.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Car {

    @Id
    private long carId;
    @Column
    private String mark;
    @Column
    private String model;
    @Column
    private String color;
    @Column
    private String productionYear;

    public Car() {
    }

    public Car(String mark, String model, String color, String productionYear) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }

    public Car(long carId, String mark, String model, String color, String productionYear) {
        this.carId = carId;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }


    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", productionYear='" + productionYear + '\'' +
                '}';
    }
}
