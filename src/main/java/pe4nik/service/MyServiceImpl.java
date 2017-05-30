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
import java.util.Arrays;
import java.util.HashMap;
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
    public String getUserWordsToStudy(String username) {
        return userDataDAO.getOne(userDao.findByUsername(username).getId())
                .getWordsToStudy();
    }

    @Transactional
    public void addUserWordsToStudy(List<String> wordsStudy, String username) {
        String wordsIds = null, textsIds = null, progress = null, wordsToStudy = null;
        List<String> list = new ArrayList<String>();
        if(userDataDAO.findOne(userDao.findByUsername(username).getId()) == null) {
            wordsIds = "";
            textsIds = "";
            progress = "";
            wordsToStudy = "";
        }
        else {
            wordsIds = userDataDAO.findOne(userDao.findByUsername(username).getId()).getLearnedWords();
            textsIds = userDataDAO.findOne(userDao.findByUsername(username).getId()).getLearnedTexts();
            progress = userDataDAO.findOne(userDao.findByUsername(username).getId()).getProgress();
            wordsToStudy = userDataDAO.findOne(userDao.findByUsername(username).getId()).getWordsToStudy();

        }
        if(wordsToStudy != null)
            list = Arrays.asList(wordsToStudy.split(","));
        else
            wordsToStudy = "";
        for (String word:wordsStudy) {
            if(!list.contains(String.valueOf(wordDao.findByWord(word).getId()))) {
                if (!wordsToStudy.equals(""))
                    wordsToStudy += "," + wordDao.findByWord(word).getId();
                else
                    wordsToStudy += wordDao.findByWord(word).getId();
            }
        }
        userDataDAO.save(new UserData(userDao.findByUsername(username).getId()
                , wordsIds, textsIds, wordsToStudy, progress));
    }

    @Transactional
    public String getPreferable(String username) {
        if(userDataDAO.getOne(userDao.findByUsername(username).getId()).getProgress() != null) {
            String[] strProgress = userDataDAO.getOne(userDao.findByUsername(username).getId())
                    .getProgress().split(",");

            HashMap<Integer, Integer> map = new HashMap<>();
            int[] intProgress = {0,0,0,0};
            for (int i = 0; i < strProgress.length; i++) {
                intProgress[i] = Integer.parseInt(strProgress[i]);
            }
            String preferable = "";


            for (int i = 0; i < intProgress.length; i++) {
                int min = 0;
                for(int j = 0; j < intProgress.length; j++) {
                    if(!map.containsKey(j) && intProgress[j] < intProgress[min]) {
                        min = j;
                    }
                    if(i == 3) {
                        if(!map.containsKey(j)) {
                            min = j;
                        }
                    }
                }
                map.put(min, Integer.parseInt(strProgress[min]));

                switch (min) {
                    case 0:
                        preferable += "reading";
                        break;
                    case 1:
                        preferable += "writing";
                        break;
                    case 2:
                        preferable += "speaking";
                        break;
                    case 3:
                        preferable += "listening";
                        break;
                }
                if (i != 3)
                    preferable += ", ";
            }
            return preferable;
        }
        else
            return "";
    }


    @Transactional
    public String getProgress(String username) {
        return userDataDAO.getOne(userDao.findByUsername(username).getId())
                .getProgress();
    }

    @Transactional
    public void setProgress(String newProgress, String username) {
        UserData userData = userDataDAO.findOne(userDao.findByUsername(username).getId());
        if(!(userData.getProgress() == null)) {
            String[] progress = userData.getProgress().split(",");
            String[] progressNew = newProgress.split(",");
            String newStr = "";
            for(int i=0;i<progress.length;i++) {
                newStr += String.valueOf(Integer.parseInt(progress[i]) + Integer.parseInt(progressNew[i]));
                if(i != progress.length-1)
                    newStr+=",";
            }
            userData.setProgress(newStr);
        }
        else
            userData.setProgress(newProgress);
        userDataDAO.save(userData);
    }

    public List<Text> getTexts(List<Long> ids) {
        List<Text> texts = new ArrayList<>();
        for (Long id:ids) {
            texts.add(textDao.findOne(id));
        }
        return texts;
    }

    @Transactional
    public String getUserLearnedTexts(String username) {
        return userDataDAO.getOne(userDao.findByUsername(username).getId())
                .getLearnedTexts();
    }

    @Transactional
    public void addUserLearnedTexts(List<String> learnedTexts, String username) {
        String wordsIds = null, textsIds = null, progress = null, wordsToStudy = null;
        List<String> list = new ArrayList<String>();
        if(userDataDAO.findOne(userDao.findByUsername(username).getId()) == null) {
            wordsIds = "";
            textsIds = "";
            progress = "";
            wordsToStudy = "";
        }
        else {
            wordsIds = userDataDAO.findOne(userDao.findByUsername(username).getId()).getLearnedWords();
            textsIds = userDataDAO.findOne(userDao.findByUsername(username).getId()).getLearnedTexts();
            progress = userDataDAO.findOne(userDao.findByUsername(username).getId()).getProgress();
            wordsToStudy = userDataDAO.findOne(userDao.findByUsername(username).getId()).getWordsToStudy();

        }
        if(textsIds != null)
            list = Arrays.asList(textsIds.split(","));
        else
            textsIds = "";
        for (String text:learnedTexts) {
            if(!list.contains(text)) {
                if (!textsIds.equals(""))
                    textsIds += "," + text;
                else
                    textsIds += text;
            }
        }
        userDataDAO.save(new UserData(userDao.findByUsername(username).getId()
                , wordsIds, textsIds, wordsToStudy, progress));
    }

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
    public void addUserLearnedWords(List<String> learnedWords, String username) {
        String wordsIds = null, textsIds = null, progress = null, wordsToStudy = null;
        List<String> list = new ArrayList<String>();
        if(userDataDAO.findOne(userDao.findByUsername(username).getId()) == null) {
            wordsIds = "";
            textsIds = "";
            progress = "";
            wordsToStudy = "";
        }
        else {
            wordsIds = userDataDAO.findOne(userDao.findByUsername(username).getId()).getLearnedWords();
            textsIds = userDataDAO.findOne(userDao.findByUsername(username).getId()).getLearnedTexts();
            progress = userDataDAO.findOne(userDao.findByUsername(username).getId()).getProgress();
            wordsToStudy = userDataDAO.findOne(userDao.findByUsername(username).getId()).getWordsToStudy();

        }
        if(wordsIds != null)
            list = Arrays.asList(wordsIds.split(","));
        else
            wordsIds = "";
        for (String word:learnedWords) {
            if(!list.contains(String.valueOf(wordDao.findByWord(word).getId()))) {
                if (!wordsIds.equals(""))
                    wordsIds += "," + wordDao.findByWord(word).getId();
                else
                    wordsIds += wordDao.findByWord(word).getId();
            }
        }
        userDataDAO.save(new UserData(userDao.findByUsername(username).getId()
                , wordsIds, textsIds, wordsToStudy, progress));
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
            //  /home/ec2-user/eng-wcp-us_flac/flac
            //  C:\Users\Pe4Nik\Downloads\eng-wcp-us_flac\flac
            String catPath = "C:\\ucheba\\диплом\\eng-wcp-us_flac\\flac";
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
    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Transactional
    public ResponseString checkUser(@RequestBody LoginUser user) {
        ResponseString responseString = new ResponseString();
        User foundUser = findUserByUsername(user.getUsername());
//        if (foundUser == null) {
//            responseString.setValue("Wrong username");
//            return responseString;
//        }
//        if (!foundUser.getPassword().equals(user.getPassword())) {
//            responseString.setValue("Wrong password");
//            return responseString;
//        }
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
