package pl.kosan.tin.dto;

import pl.kosan.tin.model.ReservationStatus;

import java.util.Date;

public class UserReservationRespDto {

    private String phoneUser;
    private Long tripId;
    private String mailUser;
    private Date dateTrip;
    private Double price;
    private Integer numPeople;


    public UserReservationRespDto() {
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public void setPrice(Double price) {
        this.price = price;
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

   /* public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }*/


    public Double getPrice() {
        return price;
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
                ", phoneUser='" + phoneUser + '\'' +
                ", mailUser='" + mailUser + '\'' +
                ", dateTrip=" + dateTrip +
               // ", status=" + status +
                ", price=" + price +
                ", numPeople=" + numPeople +
                '}';
    }
}
