package pl.kosan.tin.dto;

import pl.kosan.tin.model.ReservationStatus;

import java.util.Date;

public class ReservationPerUserDto {

    private Long idReservation;
    private String city;
    private String dateTrip;
    private Double price;
    private Integer numPeople;
    private String arriveTime;
    private String departureTime;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateTrip() {
        return dateTrip;
    }

    public void setDateTrip(String dateTrip) {
        this.dateTrip = dateTrip;
    }

    public Integer getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(Integer numPeople) {
        this.numPeople = numPeople;
    }
}
