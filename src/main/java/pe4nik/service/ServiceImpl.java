package pe4nik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe4nik.dao.TextDao;
import pe4nik.dao.UserDao;
import pe4nik.dao.WordDao;
import pe4nik.entity.Text;
import pe4nik.entity.User;
import pe4nik.entity.Word;
import pe4nik.registration.Response;

import java.util.List;

/**
 * Created by Pe4Nik on 21.03.2017.
 */
@Service
public class ServiceImpl {

    @Autowired
    private WordDao wordDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TextDao textDao;

    @Transactional
    public Word getWord(Long id) {
        return wordDao.findOne(id);
    }

    @Transactional
    public List<Word> getAllWords() {
        return wordDao.findAll();
    }

    @Transactional
    public User getUser(Long id) {
        return userDao.findOne(id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    public User findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Transactional
    public Response saveUser(User user) {
        //id must be 0
        Response response = new Response();
        user.setId(0L);
        if (userDao.findByEmail(user.getEmail()) == null ||
                !user.getEmail().equals(userDao.findByEmail(user.getEmail()).getEmail())) {
            userDao.save(user);
            //return "Ok";
            response.setValue("Ok");
            return response;
        }
        response.setValue("\"User with this email \" + user.getEmail() + \" already exist!\"");
        return response;
    }

    @Transactional
    public Text getText(Long id) {
        return textDao.findOne(id);
    }

    @Transactional
    public String saveText(Text text) {
        text.setId(0L);
        if(!text.getText().isEmpty()) {
            textDao.save(text);
            return "Ok";
        }
        else
            return "Text is empty";
    }

    @Transactional
    public List getAllTexts() {
        return textDao.findAll();
    }
}
