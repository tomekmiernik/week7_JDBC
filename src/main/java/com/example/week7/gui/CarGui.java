package com.example.week7.gui;

import com.example.week7.model.Car;
import com.example.week7.repository.CarDaoImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Route("")
public class CarGui extends VerticalLayout {
    private CarDaoImpl carDao;
    private Grid<Car> grid = new Grid<>(Car.class);
    private CarForm carForm;

    TextField sinceDate = new TextField();
    TextField fromDate = new TextField();

    public CarGui(CarDaoImpl carDao) {
        this.carDao = carDao;
        carForm = new CarForm(carDao, this);

        grid.setColumns("mark", "model", "color", "productionYear");
        add(grid);
        setSizeFull();

        Button addCarButton = new Button("Dodaj nowy samochód");
        addCarButton.addClickListener(event -> {
            grid.asSingleSelect().clear();
            carForm.setCar(new Car());
        });

        sinceDate.setPlaceholder("Od");
        fromDate.setPlaceholder("Do");
        Button filteredButton = new Button("Filtruj");
        Button resetResultButton = new Button("Reset");
        HorizontalLayout toolToolbar = new HorizontalLayout();
        toolToolbar.add(addCarButton, sinceDate, fromDate, filteredButton, resetResultButton);

        HorizontalLayout mainContent = new HorizontalLayout(grid, carForm);
        mainContent.setSizeFull();
        grid.setSizeFull();
        add(toolToolbar, mainContent);
        updateList();

        carForm.setCar(null);
        grid.asSingleSelect().addValueChangeListener(event ->
                carForm.setCar(grid.asSingleSelect().getValue()));

        filteredButton.addClickListener(event -> filterResult());
        resetResultButton.addClickListener(event -> updateList());
    }

    private void filterResult() {
        Collection<Car> byProductionDate = carDao.findByProductionDate(sinceDate.getValue(), fromDate.getValue());
        grid.setItems(byProductionDate);
        carForm.setCar(null);
    }

    void updateList() {
        grid.setItems(carDao.findAll());
    }
}
