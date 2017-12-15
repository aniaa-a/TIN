package pl.kosan.tin.model;

public class carDriver {

    Long carDriverId;
    String name;
    String surname;
    String pesel;
    String IdentityNum;
    Double salary;

    public carDriver() {
    }

    public carDriver(Long carDriverId, String name, String surname, String pesel, String identityNum, Double salary) {
        this.carDriverId = carDriverId;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        IdentityNum = identityNum;
        this.salary = salary;
    }

    public Long getCarDriverId() {
        return carDriverId;
    }

    public void setCarDriverId(Long carDriverId) {
        this.carDriverId = carDriverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getIdentityNum() {
        return IdentityNum;
    }

    public void setIdentityNum(String identityNum) {
        IdentityNum = identityNum;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "carDriver{" +
                "carDriverId=" + carDriverId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                ", IdentityNum='" + IdentityNum + '\'' +
                ", salary=" + salary +
                '}';
    }
}
