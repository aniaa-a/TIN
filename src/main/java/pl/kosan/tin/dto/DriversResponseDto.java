package pl.kosan.tin.dto;

import pl.kosan.tin.model.CarDriver;

import java.util.List;

public class DriversResponseDto {

    private List<CarDriver> listCarDriver;

    public List<CarDriver> getListCarDriver() {
        return listCarDriver;
    }

    public void setListCarDriver(List<CarDriver> listCarDriver) {
        this.listCarDriver = listCarDriver;
    }
}
