package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.kosan.tin.model.MailUser;

@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.to}")
    String to;

    public void sendMail(MailUser mailUser) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(mailUser.getMail());
        mail.setSubject(mailUser.getSubject());
        mail.setText(mailUser.getContent());
        mail.setTo(to);

        javaMailSender.send(mail);
    }
}
