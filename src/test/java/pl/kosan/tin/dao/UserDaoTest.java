package pl.kosan.tin.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.kosan.tin.model.User;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Ignore
public class UserDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    UserDao userDao;
    User user = new User();

    @Before
    public void init() {

        user.setName("test");
        user.setSurname("test");
        user.setIdentityNum("car234343");
        user.setPhone("658736587435");
        user.setEmail("test@testDaoReservation.pl");
        user.setPassword("haslo");
    }

    @Test
    public void shouldInsertUser() {

        userDao.insertUser(user);
        Assert.assertNotNull(userDao.findUserByMail("test@tesst.pl"));

    }

    @Test
    public void shouldGetAllUser() {

        Assert.assertNotNull(userDao.findAllUser());
    }

    @Test
    public void shouldRemoveUser() {

        User u = userDao.findUserByMail(user.getEmail());
        logger.info(u.toString());
        userDao.deleteUserById(u.getIdUser());
        Assert.assertNull(userDao.findUserByMail(user.getEmail()));
    }




}
