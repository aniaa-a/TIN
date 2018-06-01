package pl.kosan.tin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kosan.tin.dto.UserLoginRequestDto;
import pl.kosan.tin.dto.UserRegisterDto;
import pl.kosan.tin.dto.UserRespDto;
import pl.kosan.tin.dto.UserResponseDto;
import pl.kosan.tin.model.User;
import pl.kosan.tin.services.UserService;
import pl.kosan.tin.util.TransformUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public void RegisterUser(@RequestBody UserRegisterDto user, HttpServletResponse response) {

        TransformUser transform = new TransformUser();
        if (userService.findUserByMail(user.getEmail()) == null) {

            userService.registerUser(transform.tranformUserRegisterDto(user));
            response.setStatus(201);
        } else {
            response.setStatus(409);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/isExist")
    public @ResponseBody UserRespDto czekUserExist(@RequestParam ("email") String email) {
        UserRespDto user = new UserRespDto();

        if (userService.findUserByMail(email) != null) {

            user.setExisting(true);
        } else {
            user.setExisting(false);
        }
        return user;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserResponseDto logInUser(HttpServletRequest req, @RequestBody UserLoginRequestDto userLoginRequestDto) {
        HttpSession session = req.getSession();
        UserResponseDto userResponseDto = new UserResponseDto();

        User user = userService.findUserByMailAndPass(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());


        if (user != null) {
            session.setAttribute("user", userLoginRequestDto.getEmail());
            LOGGER.info("id sesji: " + session.getId() + " log: " + session.getAttribute("user"));


            userResponseDto.setUser(user);
            userResponseDto.setStatus("ok");
        } else {
            userResponseDto.setStatus("nie znaleziono użytkownika");
        }
        return userResponseDto;

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/isLogged", method = RequestMethod.GET)
    public UserResponseDto logged(HttpServletRequest req) {
        String user = null;
        HttpSession session = req.getSession();

        user = (String) session.getAttribute("user");
        LOGGER.info("id sesji: " + session.getId() + " isLogged: " + user);

        UserResponseDto userResponseDto = new UserResponseDto();

        if (user != null) {
            userResponseDto.setUser(userService.findUserByMail(user));
            userResponseDto.setStatus("ok");

        } else {
            userResponseDto.setStatus("brak uzytkownika");
        }
        return userResponseDto;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logOutUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.invalidate();
    }


}
