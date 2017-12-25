package pl.kosan.tin.dto;

import java.util.Date;

public class UserReservationRespDto {

    private String city;
    private String mailUser;
    private Date dateTrip;
    private Double pricePerPerson;
    private Integer numPeople;


    public UserReservationRespDto() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public Date getDateTrip() {
        return dateTrip;
    }

    public void setDateTrip(Date dateTrip) {
        this.dateTrip = dateTrip;
    }

    public Double getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(Double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public Integer getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(Integer numPeople) {
        this.numPeople = numPeople;
    }
}