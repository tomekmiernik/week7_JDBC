package com.example.week7.gui;

import com.example.week7.model.Car;
import com.example.week7.repository.CarDaoImpl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CarForm extends FormLayout {
    private final String SAVE_CAR = "Zapisz";
    private final String CANCEL = "Zamknij";
    private CarDaoImpl carDao;
    private CarGui carGui;

    private TextField mark = new TextField("Marka");
    private TextField model = new TextField("Model");
    private TextField color = new TextField("Kolor");
    private TextField productionYear = new TextField("Rocznik");

    private Button save = new Button(SAVE_CAR);
    private Button cancel = new Button(CANCEL);
    private Binder<Car> binder = new Binder<>(Car.class);

    @Autowired
    public CarForm(CarDaoImpl carDao, CarGui carGui) {
        this.carDao = carDao;
        this.carGui = carGui;

        HorizontalLayout buttonsGrid = new HorizontalLayout(save,cancel);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        add(mark,model,color,productionYear, buttonsGrid);

        binder.bindInstanceFields(this);

        save.addClickListener(event->saveCarItem());
        cancel.addClickListener(event->closedForm());
    }

    private void closedForm() {
        setVisible(false);
    }

    private void saveCarItem() {
        Car car = binder.getBean();
        if(car == null){
            car = new Car();
        }
        carDao.saveCar(car.getCarId(),mark.getValue(), model.getValue(),color.getValue(), productionYear.getValue());
        carGui.updateList();
        setCar(null);
    }

    void setCar(Car car) {
        binder.setBean(car);
        if(car == null || car.getCarId()>0){
            setVisible(false);
        }else {
            setVisible(true);
            mark.focus();
        }
    }
}
