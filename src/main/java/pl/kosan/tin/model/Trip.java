package pl.kosan.tin.model;

import java.util.List;

public class Trip {

    private Long tripId;
    private String city;
    private String departureTime;
    private String arriveTime;
    private String content;
    private String lead;
    private String photo;
    private String miniPhoto;
    private String title;


    public Trip() {
    }

    public Trip(Long tripId, String city, String departureTime, String arriveTime) {
        this.tripId = tripId;
        this.city = city;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;

    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getMiniPhoto() {
        return miniPhoto;
    }

    public void setMiniPhoto(String miniPhoto) {
        this.miniPhoto = miniPhoto;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", city='" + city + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", content='" + content + '\'' +
                ", lead='" + lead + '\'' +
                ", photo='" + photo + '\'' +
                ", title='" + title + '\'' +

                '}';
    }
}
