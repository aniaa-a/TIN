package pl.kosan.tin.model;

import java.util.Date;

public class User {

    Long idUser;
    String name;
    String surname;
    Date birth_date;
    String identityNum;

    public User() {
    }

    public User(Long idUser, String name, String surname, Date birth_date, String identityNum) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.identityNum = identityNum;
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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth_date=" + birth_date +
                ", identityNum='" + identityNum + '\'' +
                '}';
    }
}
