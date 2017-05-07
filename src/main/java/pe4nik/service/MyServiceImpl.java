package pe4nik.service;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import pe4nik.dao.TextDao;
import pe4nik.dao.UserDao;
import pe4nik.dao.UserDataDAO;
import pe4nik.dao.WordDao;
import pe4nik.entity.Text;
import pe4nik.entity.User;
import pe4nik.entity.UserData;
import pe4nik.entity.Word;
import pe4nik.jsonentity.LoginUser;
import pe4nik.jsonentity.ResponseString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pe4Nik on 21.03.2017.
 */
@Service
public class MyServiceImpl implements MyService{

    @Autowired
    public WordDao wordDao;

    @Autowired
    public UserDao userDao;

    @Autowired
    public TextDao textDao;

    @Autowired
    public UserDataDAO userDataDAO;

    @Transactional
    public List<Word> getWords(List<Long> ids) {
        List<Word> words = new ArrayList<>();
        for (Long id:ids) {
            words.add(wordDao.findOne(id));
        }
        return words;
    }

    @Transactional
    public String getUserLearnedWords(String username) {
        return userDataDAO.getOne(userDao.findByUsername(username).getId())
                .getLearnedWords();
    }

    @Transactional
    public void addUserLearnedWords(String learnedWords, String username) {
        userDataDAO.save(new UserData(userDao.findByUsername(username).getId()
                , learnedWords));
    }

    @Override
    @Transactional
    public void save(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

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
            this.save(user);
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
    public ResponseString saveText(Text text) {
        ResponseString responseString = new ResponseString();
        text.setId(0L);
        if(!text.getText().isEmpty()) {
            textDao.save(text);
            responseString.setValue("Ok");
            return responseString;
        }
        else {
            responseString.setValue("Text is empty");
            return responseString;
        }
    }

    @Transactional
    public List getAllTexts() {
        return textDao.findAll();
    }
}
