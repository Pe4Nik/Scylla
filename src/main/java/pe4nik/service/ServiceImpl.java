package pe4nik.service;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import pe4nik.dao.TextDao;
import pe4nik.dao.UserDao;
import pe4nik.dao.WordDao;
import pe4nik.entity.Text;
import pe4nik.entity.User;
import pe4nik.entity.Word;
import pe4nik.registration.LoginUser;
import pe4nik.registration.ResponseString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pe4Nik on 21.03.2017.
 */
@Service("serviceImpl")
public class ServiceImpl {

    @Autowired
    public WordDao wordDao;

    @Autowired
    public UserDao userDao;

    @Autowired
    public TextDao textDao;

    @Transactional
    public String getAudioFile(String word) {
        if(wordDao.findByWord(word).getAudio()) {
            String catPath = "C:\\Users\\Pe4Nik\\Downloads\\eng-wcp-us_flac\\flac";
            File file = new File(catPath+File.separator+"En-us-"+word+".flac");
            byte[] bFile = new byte[(int) file.length()];
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                fileInputStream.read(bFile);
                fileInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Base64.encodeBase64String(bFile);
        }
        else
            return "Oh, shit!";
    }

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
    public ResponseString checkUser(@RequestBody LoginUser user) {
        ResponseString responseString = new ResponseString();
        User foundUser = findUserByEmail(user.getEmail());
        if (foundUser == null) {
            responseString.setValue("Wrong email");
            return responseString;
        }
        if (!foundUser.getPassword().equals(user.getPassword())) {
            responseString.setValue("Wrong password");
            return responseString;
        }
        responseString.setValue("Ok, "+foundUser.getUsername());
        return responseString;
    }

    @Transactional
    public ResponseString saveUser(User user) {
        //id must be 0
        ResponseString responseString = new ResponseString();
        user.setId(0L);
        if (userDao.findByEmail(user.getEmail()) == null ||
                !user.getEmail().equals(userDao.findByEmail(user.getEmail()).getEmail())) {
            userDao.save(user);
            //return "Ok";
            responseString.setValue("Ok");
            return responseString;
        }
        responseString.setValue("\"User with this email \" + user.getEmail() + \" already exist!\"");
        return responseString;
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
