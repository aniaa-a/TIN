package pl.kosan.tin.dto;

import pl.kosan.tin.model.ReservationStatus;

import java.util.Date;

public class UserReservationRespDto {

    Long reservationId;
    String carDriver;
    String city;
    String user;
    String identityNumUser;
    String phoneUser;
    String mailUser;
    Date dateTrip;
    ReservationStatus status;
    Double pricePerPerson;
    Integer numPeople;


    public UserReservationRespDto() {
    }

    public UserReservationRespDto(Long reservationId, String carDriver, String city, String user, String identityNumUser, String phoneUser, String mailUser, Integer numPeople, Date dateTrip, ReservationStatus status, Double priceForTrip) {
        this.reservationId = reservationId;
        this.carDriver = carDriver;
        this.city = city;
        this.user = user;
        this.identityNumUser = identityNumUser;
        this.phoneUser = phoneUser;
        this.mailUser = mailUser;
        this.dateTrip = dateTrip;
        this.status = status;
        this.pricePerPerson = pricePerPerson;
        this.numPeople = numPeople;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getCarDriver() {
        return carDriver;
    }

    public void setCarDriver(String carDriver) {
        carDriver = carDriver;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIdentityNumUser() {
        return identityNumUser;
    }

    public void setIdentityNumUser(String identityNumUser) {
        this.identityNumUser = identityNumUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
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

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "UserReservationRespDto{" +
                "reservationId=" + reservationId +
                ", CarDriver='" + carDriver + '\'' +
                ", city='" + city + '\'' +
                ", user='" + user + '\'' +
                ", identityNumUser='" + identityNumUser + '\'' +
                ", phoneUser='" + phoneUser + '\'' +
                ", mailUser='" + mailUser + '\'' +
                ", dateTrip=" + dateTrip +
                ", status='" + status + '\'' +
                ", priceForTrip=" + pricePerPerson +
                '}';
    }
}
