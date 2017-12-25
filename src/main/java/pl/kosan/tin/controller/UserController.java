package pl.kosan.tin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kosan.tin.model.User;
import pl.kosan.tin.services.UserService;

@RestController
@RequestMapping(value = "/tin")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public void RegisterUser(User user) {

        userService.registerUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/user/log", method = RequestMethod.GET)
    public User logInUser(@RequestParam(defaultValue = "email") String email,
                                                                @RequestParam(defaultValue = "password") String password) {

        return userService.findUserByMailAndPass(email, password);
    }
}
