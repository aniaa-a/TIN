package pl.kosan.tin.dto;

import pl.kosan.tin.model.ReservationStatus;

import java.util.Date;

public class ReservationPerUserDto {

    private Long idReservation;
    private String city;
    private String dateTrip;
    private String pricePerPerson;
    private Integer numPeople;

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

    public String getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(String pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public Integer getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(Integer numPeople) {
        this.numPeople = numPeople;
    }
}
