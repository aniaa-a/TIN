package pl.kosan.tin.dto;

import pl.kosan.tin.model.User;

public class UserResponseDto {

    User user;
    String status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
