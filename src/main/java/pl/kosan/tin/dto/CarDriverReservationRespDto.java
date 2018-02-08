package pl.kosan.tin.dto;

import java.util.Date;

public class CarDriverReservationRespDto {


    private String pesel;
    private String registr_num;
    private String date;
    private Long idReservation;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getRegistr_num() {
        return registr_num;
    }

    public void setRegistr_num(String registr_num) {
        this.registr_num = registr_num;
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

    @Override
    public String toString() {
        return "CarDriverReservationRespDto{" +
                "pesel='" + pesel + '\'' +
                ", registr_num='" + registr_num + '\'' +
                ", date=" + date +
                ", idReservation=" + idReservation +
                '}';
    }
}
