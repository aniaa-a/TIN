package pl.kosan.tin.model;

public class Trip {

    private Long tripId;
    private String city;
    private  String departureTime;
    private String arriveTime;
    private String content;
    private Double pricePerPerson;
    private String lead;
    private String photo;
    private String title;
    private Integer rabat;


    public Trip() {
    }

    public Trip(Long tripId, String city, String departureTime, String arriveTime, Double pricePerPerson) {
        this.tripId = tripId;
        this.city = city;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.pricePerPerson = pricePerPerson;
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

    public Integer getRabat() {
        return rabat;
    }

    public void setRabat(Integer rabat) {
        this.rabat = rabat;
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

    public Double getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(Double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", city='" + city + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", content='" + content + '\'' +
                ", pricePerPerson=" + pricePerPerson +
                ", lead='" + lead + '\'' +
                ", photo='" + photo + '\'' +
                ", title='" + title + '\'' +
                ", rabat=" + rabat +
                '}';
    }
}
