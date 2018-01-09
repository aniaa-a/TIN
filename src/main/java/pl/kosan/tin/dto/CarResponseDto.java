package pl.kosan.tin.dto;

import pl.kosan.tin.model.Car;

import java.util.List;

public class CarResponseDto {

    List<Car> carList;

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
