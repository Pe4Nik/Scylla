import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pe4nik.config.WebConfig;
import pe4nik.dao.TextDao;
import pe4nik.dao.UserDao;
import pe4nik.dao.WordDao;
import pe4nik.entity.Text;
import pe4nik.entity.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Pe4Nik on 30.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class ServiceTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private WordDao wordDao;


    @Autowired
    private TextDao textDao;

    @Test
    @Transactional
    public void saveTextTest() throws Exception {
        Text text = new Text(0L,"some text");
        Long id = textDao.save(text).getId();
        assertNotNull(textDao.findOne(id));
    }

    @Test
    @Transactional
    public void saveUserTest() throws Exception {
        User user = new User(0L,"test@email.com","username","pass");
        userDao.save(user);
        assertNotNull(userDao.findByEmail(user.getEmail()));
        assertEquals(user.getEmail(), userDao.findByEmail(user.getEmail()).getEmail());
        assertEquals(user.getUsername(), userDao.findByEmail(user.getEmail()).getUsername());
        assertEquals(user.getPassword(), userDao.findByEmail(user.getEmail()).getPassword());
    }

    @Test
    @Transactional
    public void getExistentUserTest() throws Exception {
        assertEquals("wolfgang@amadeus.at", userDao.findByEmail("wolfgang@amadeus.at").getEmail());
    }

    @Test
    @Transactional
    public void getNonExistentUserTest() throws Exception {
        assertEquals(null, userDao.findByEmail("google@gmail.com"));
    }

    @Test
    @Transactional
    public void getExistentWordTest() throws Exception {
        assertEquals("hello", wordDao.findByWord("hello").getWord());
    }

    @Test
    @Transactional
    public void getNonExistentWordTest() throws Exception {
        assertEquals(null, wordDao.findByWord("kduj46skn@ds*hadgasjhg&52hsd"));
    }

    @Test
    @Transactional
    public void getUsersTest() throws Exception {
        assertNotNull(userDao.findAll());
    }

    @Test
    @Transactional
    public void getWordsTest() throws Exception {
        assertNotNull(wordDao.findAll());
    }
}
