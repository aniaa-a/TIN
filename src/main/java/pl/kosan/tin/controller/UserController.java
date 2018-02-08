package pl.kosan.tin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kosan.tin.dto.UserResponseDto;
import pl.kosan.tin.model.User;
import pl.kosan.tin.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public UserResponseDto RegisterUser(@RequestBody User user) {


        UserResponseDto userResponseDto = new UserResponseDto();

        if (userService.findUserByMail(user.getEmail()) != null) {
            userResponseDto.setStatus("mail istnieje w bazie");
        }else {
            userService.registerUser(user);
            userResponseDto.setStatus("ok");
        }
        return userResponseDto;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/log", method = RequestMethod.GET)
    public UserResponseDto logInUser(HttpServletRequest req, @RequestParam(defaultValue = "email") String email,
                                     @RequestParam(defaultValue = "password") String password) {
        HttpSession session = req.getSession();
        UserResponseDto userResponseDto = new UserResponseDto();
        User user = userService.findUserByMailAndPass(email, password);
        if (user != null) {
            session.setAttribute("user", email);
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

        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        UserResponseDto userResponseDto = new UserResponseDto();
        System.out.print(user);
        if (user != null) {
            userResponseDto.setUser(userService.findUserByMail(user));
            userResponseDto.setStatus("ok");

        }else{
            userResponseDto.setStatus("brak uzytkownika");
        }
        return userResponseDto;
    }


}
