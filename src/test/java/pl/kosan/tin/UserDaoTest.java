package pl.kosan.tin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.kosan.tin.dao.UserDao;
import pl.kosan.tin.model.User;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan(basePackages = "pl.kosan.tin.dao")
public class UserDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    UserDao userDao;

    @Test
    public void shouldInsertUser() {

        User user = new User();
        user.setName("test");
        user.setSurname("test");
        user.setIdentityNum("car234343");
        user.setPhone("658736587435");
        user.setEmail("test@test.pl");
        user.setPassword("haslo");

        userDao.insertUser(user);
    }


}
