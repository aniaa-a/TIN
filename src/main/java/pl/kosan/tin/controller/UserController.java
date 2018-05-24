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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public UserResponseDto RegisterUser(@RequestBody User user, HttpServletResponse response) {


        UserResponseDto userResponseDto = new UserResponseDto();
        LOGGER.info("haslo register: "+ user.getPassword());
        if (userService.findUserByMail(user.getEmail()) != null) {
            response.setStatus(200);
            userResponseDto.setStatus("mail istnieje w bazie");
        }else {
            response.setStatus(201);
            userService.registerUser(user);
            userResponseDto.setStatus("ok");
        }
        return userResponseDto;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/log/{email}/{password}", method = RequestMethod.GET)
    public UserResponseDto logInUser(HttpServletRequest req, @PathVariable("email") String email,
                                     @PathVariable("password") String password) {
        HttpSession session = req.getSession();
        UserResponseDto userResponseDto = new UserResponseDto();

        User user = userService.findUserByMailAndPass(email, password);


        if (user != null) {
            session.setAttribute("user", email);
           LOGGER.info("id sesji: " +session.getId() + " log: "+ session.getAttribute("user"));


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
        String user= null;
        HttpSession session = req.getSession();

             user = (String) session.getAttribute("user");
            LOGGER.info("id sesji: " +session.getId() + " isLogged: "+user);

        UserResponseDto userResponseDto = new UserResponseDto();

        if (user != null) {
            userResponseDto.setUser(userService.findUserByMail(user));
            userResponseDto.setStatus("ok");

        }else{
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
