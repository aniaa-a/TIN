package pl.kosan.tin.dto;

import pl.kosan.tin.model.ReservationStatus;

import java.util.Date;

public class UserReservationRespDto {


    private String city;
    private String phoneUser;
    private String mailUser;
    private Date dateTrip;
    private ReservationStatus status;
    private Double pricePerPerson;
    private Double price;
    private Integer numPeople;


    public UserReservationRespDto() {
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = pricePerPerson * numPeople;
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
                ", city='" + city + '\'' +
                ", phoneUser='" + phoneUser + '\'' +
                ", mailUser='" + mailUser + '\'' +
                ", dateTrip=" + dateTrip +
                ", status=" + status +
                ", pricePerPerson=" + pricePerPerson +
                ", price=" + price +
                ", numPeople=" + numPeople +
                '}';
    }
}
