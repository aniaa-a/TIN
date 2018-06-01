package pl.kosan.tin.util;

import pl.kosan.tin.dto.UserRegisterDto;
import pl.kosan.tin.model.User;


public class TransformUser {


    public User tranformUserRegisterDto(UserRegisterDto userRegisterDto){

        User user = new User();
        user.setPassword(userRegisterDto.getPassword());
        user.setRole(userRegisterDto.getRole());
        user.setEmail(userRegisterDto.getEmail());
        user.setPhone(userRegisterDto.getPhone());
        user.setIdentityNum(userRegisterDto.getIdentityNum());
        user.setSurname(userRegisterDto.getSurname());
        user.setName(userRegisterDto.getName());

        return user;
    }
}
