package pl.kosan.tin.model;

import java.util.Date;

public class User {

    Long idUser;
    String name;
    String surname;
    String identityNum;
    String email;
    String phone;

    public User() {
    }

    public User(Long idUser, String name, String surname, String identityNum,String email, String phone) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.identityNum = identityNum;
        this.email = email;
        this.phone = phone;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", identityNum='" + identityNum + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
