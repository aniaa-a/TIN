package pl.kosan.tin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kosan.tin.model.MailUser;
import pl.kosan.tin.services.MailService;

@RestController
@RequestMapping(value = "/mail")
public class MailController {

    @Autowired
    MailService mailService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void sendMail(@RequestBody MailUser mailUser) {

        mailService.sendMail(mailUser);

    }
}
