package pl.kosan.tin.dto;

import java.util.Date;

public class CarDriverReservationRespDto {


    private Long idCar;
    private Long idCarDriver;
    private String date;
    private Long idReservation;

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public Long getIdCarDriver() {
        return idCarDriver;
    }

    public void setIdCarDriver(Long idCarDriver) {
        this.idCarDriver = idCarDriver;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }
}
