package pl.kosan.tin.dto;

import pl.kosan.tin.model.ReservationStatus;
import java.util.Date;


public class ReservationRespDto {

    private Long idReservation;
    private String city;
    private Date dateTrip;
    private ReservationStatus status;
    private String registration_num;
    private String surnameClient;
    private String peselClient;

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

    public String getRegistration_num() {
        return registration_num;
    }

    public void setRegistration_num(String registration_num) {
        this.registration_num = registration_num;
    }

    public String getSurnameClient() {
        return surnameClient;
    }

    public void setSurnameClient(String surnameClient) {
        this.surnameClient = surnameClient;
    }

    public String getPeselClient() {
        return peselClient;
    }

    public void setPeselClient(String peselClient) {
        this.peselClient = peselClient;
    }
}
