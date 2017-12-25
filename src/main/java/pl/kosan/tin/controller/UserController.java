package pl.kosan.tin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    public void RegisterUser(User user) {

        userService.registerUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/log", method = RequestMethod.GET)
    public User logInUser(HttpServletRequest req, @RequestParam(defaultValue = "email") String email,
                          @RequestParam(defaultValue = "password") String password) {
        HttpSession session = req.getSession();

        User user = userService.findUserByMailAndPass(email, password);
        if (user != null) {
            session.setAttribute("user", email);

        }
        return user;

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/isLogged", method = RequestMethod.GET)
    public boolean logged(HttpServletRequest req, @RequestParam(defaultValue = "email") String email) {

        HttpSession session = req.getSession();
        String user = (String) session.getAttribute(email);
        if (user != null)
            return true;
        else
            return false;
    }


}
