package pl.kosan.tin.model;

public class DriverToCar {

    Long DriverToCar;
    Long carDriverId;
    Long carId;

    public DriverToCar() {
    }

    public DriverToCar(Long driverToCar, Long carDriverId, Long carId) {
        DriverToCar = driverToCar;
        this.carDriverId = carDriverId;
        this.carId = carId;
    }

    public Long getDriverToCar() {
        return DriverToCar;
    }

    public void setDriverToCar(Long driverToCar) {
        DriverToCar = driverToCar;
    }

    public Long getCarDriverId() {
        return carDriverId;
    }

    public void setCarDriverId(Long carDriverId) {
        this.carDriverId = carDriverId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "DriverToCar{" +
                "DriverToCar=" + DriverToCar +
                ", carDriverId=" + carDriverId +
                ", carId=" + carId +
                '}';
    }
}
