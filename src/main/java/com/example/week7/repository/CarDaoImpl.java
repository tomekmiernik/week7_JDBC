package com.example.week7.repository;

import com.example.week7.dao.CarDao;
import com.example.week7.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(long id, String mark, String model, String color, String productionYear) {
        new Car(id, mark,model,color,productionYear);
        id = autoIncrementedCarId();
        String sql = "INSERT INTO cars VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, id, mark, model, color, productionYear);
    }

    @Override
    public Collection<Car> findAll() {
        List<Car> carList = new ArrayList<>();
        String sql ="SELECT * FROM cars";
        List<Map<String, Object>> carMaps = jdbcTemplate.queryForList(sql);
        carMaps.forEach(c-> carList.add(new Car(
                String.valueOf(c.get("mark")),
                String.valueOf(c.get("model")),
                String.valueOf(c.get("color")),
                String.valueOf(c.get("production_year"))
        )));
        return carList;
    }

    @Override
    public Collection<Car> findByProductionDate(String sinceDate, String fromDate) {
        List<Car> carList = new ArrayList<>();
        String sql ="SELECT * FROM cars where cars.production_year BETWEEN ? AND ?";
        List<Map<String, Object>> carMaps = jdbcTemplate.queryForList(sql,sinceDate,fromDate);
        carMaps.forEach(c-> carList.add(new Car(
                String.valueOf(c.get("mark")),
                String.valueOf(c.get("model")),
                String.valueOf(c.get("color")),
                String.valueOf(c.get("production_year"))
                )));
        return carList;
    }

    private long autoIncrementedCarId(){
        String sql ="SELECT * FROM cars";
        List<Map<String, Object>> carMaps = jdbcTemplate.queryForList(sql);
        long carId;
        if(carMaps.isEmpty()){
            carId = 1;
            return carId;
        }else {
            int size = carMaps.size();
            carId = Long.parseLong(String.valueOf(size)) + 1;
        }
        return carId;
    }
}
