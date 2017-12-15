package pl.kosan.tin.model;

import java.util.Date;

public class Reservation {

    Long reservationId;
    Long DriverToCarId;
    Long tripId;
    Long userId;
    Date dateTrip;


    public Reservation() {
    }

    public Reservation(Long reservationId, Long driverToCarId, Long tripId, Long userId) {
        this.reservationId = reservationId;
        DriverToCarId = driverToCarId;
        this.tripId = tripId;
        this.userId = userId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getDriverToCarId() {
        return DriverToCarId;
    }

    public void setDriverToCarId(Long driverToCarId) {
        DriverToCarId = driverToCarId;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateTrip() {
        return dateTrip;
    }

    public void setDateTrip(Date dateTrip) {
        this.dateTrip = dateTrip;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", DriverToCarId=" + DriverToCarId +
                ", tripId=" + tripId +
                ", userId=" + userId +
                '}';
    }
}
