package pl.kosan.tin.model;

public class Car {

    Long carId;
    String brand;
    String registrationNum;
    Integer carSeats;

    public Car() {
    }

    public Car(Long carId, String brand, String registrationNum, Integer carSeats) {
        this.carId = carId;
        this.brand = brand;
        this.registrationNum = registrationNum;
        this.carSeats = carSeats;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }

    public Integer getCarSeats() {
        return carSeats;
    }

    public void setCarSeats(Integer carSeats) {
        this.carSeats = carSeats;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", brand='" + brand + '\'' +
                ", registrationNum='" + registrationNum + '\'' +
                ", carSeats=" + carSeats +
                '}';
    }
}
