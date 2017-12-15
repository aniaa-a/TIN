package pl.kosan.tin.model;

public class Trip {

    Long tripId;
    String city;
    String departureTime;
    String arriveTime;
    Double pricePerPerson;

    public Trip() {
    }

    public Trip(Long tripId, String city, String departureTime, String arriveTime, Double pricePerPerson) {
        this.tripId = tripId;
        this.city = city;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.pricePerPerson = pricePerPerson;
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
                ", pricePerPerson=" + pricePerPerson +
                '}';
    }
}
