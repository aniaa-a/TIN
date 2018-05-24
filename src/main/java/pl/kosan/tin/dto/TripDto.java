package pl.kosan.tin.dto;

import java.util.List;

public class TripDto {

    private Long tripId;
    private String city;
    private String departureTime;
    private String arriveTime;
    private String content;
    private List<Double> prices;
    private List<String> services;
    private String miniPhoto;
    private String lead;
    private String photo;
    private String title;
    private boolean inSlider;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public boolean isInSlider() {
        return inSlider;
    }

    public void setInSlider(boolean inSlider) {
        this.inSlider = inSlider;
    }

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public String getMiniPhoto() {
        return miniPhoto;
    }

    public void setMiniPhoto(String miniPhoto) {
        this.miniPhoto = miniPhoto;
    }

    @Override
    public String toString() {
        return "TripDto{" +
                "tripId=" + tripId +
                ", city='" + city + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", content='" + content + '\'' +
                ", prices=" + prices +
                ", services=" + services +
                ", miniPhoto='" + miniPhoto + '\'' +
                ", lead='" + lead + '\'' +
                ", photo='" + photo + '\'' +
                ", title='" + title + '\'' +
                ", inSlider=" + inSlider +
                '}';
    }
}
