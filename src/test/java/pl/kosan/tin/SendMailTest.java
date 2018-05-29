package pl.kosan.tin;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.kosan.tin.model.MailUser;
import pl.kosan.tin.services.MailService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SendMailTest {


    @Autowired
    MailService service;


    @Test
    @Ignore
    public void shouldSendMail(){

        MailUser user = new MailUser();
        user.setMail("testowyMail@wp.pl");
        user.setSubject("test");
        user.setContent("to jest testowy mail");

        service.sendMail(user);

    }

}
