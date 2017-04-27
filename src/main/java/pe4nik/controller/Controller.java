package pe4nik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe4nik.entity.Text;
import pe4nik.entity.User;
import pe4nik.entity.Word;
import pe4nik.registration.LoginUser;
import pe4nik.registration.RegUser;
import pe4nik.registration.ResponseString;
import pe4nik.service.ServiceImpl;

import java.util.List;

/**
 * Created by Pe4Nik on 22.03.2017.
 */
@RestController
public class Controller {

    @Autowired
    public ServiceImpl serviceImpl;

//    @Autowired
//    public UserDao userDao;
//
//    @Autowired
//    public WordDao wordDao;

    @RequestMapping(value = "/audio/{word}")
    @ResponseBody
    public String getFile(@PathVariable String word) {
        return serviceImpl.getAudioFile(word);
    }

    @RequestMapping(value = "/word/{id}")
    @ResponseBody
    public Word getWord(@PathVariable Long id) {
        return serviceImpl.getWord(id);
    }




    @RequestMapping(value = "/words")
    @ResponseBody
    public List<Word> getAllWords() {
        return serviceImpl.getAllWords();
    }


    @RequestMapping(value = "/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return serviceImpl.getUser(id);
    }


    @RequestMapping(value = "/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return serviceImpl.getAllUsers();
    }


    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseString checkUser(@RequestBody LoginUser user) {
        return serviceImpl.checkUser(user);
    }


    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseString saveUser(@RequestBody RegUser user) {
        return serviceImpl.saveUser(new User(null,user.getEmail(),user.getUsername(),user.getPassword()));
    }


    @RequestMapping(value = "/text/{id}")
    @ResponseBody
    public Text getText(@PathVariable Long id) {
        return serviceImpl.getText(id);
    }


    @RequestMapping(value = "/savetext", method = RequestMethod.POST)
    @ResponseBody
    public String saveText(@RequestBody Text text) {
        return serviceImpl.saveText(text);
    }


    @RequestMapping(value = "/texts")
    @ResponseBody
    public List getAllTexts() {
        return serviceImpl.getAllTexts();
    }

//    @RequestMapping(value = "/addaudio")
//    @ResponseBody
//    public List<Word> addAudio() {
//        List<Word> words = serviceImpl.getAllWords();
//        File[] files = new File("C:\\Users\\Pe4Nik\\Downloads\\eng-wcp-us_flac\\flac").listFiles();
//        for (Word word:words) {
//            for (File file:files) {
//                if(word.getWord().equals(file.getName().substring(6,file.getName().length()-5))) {
//                    word.setAudio(true);
//                    wordDao.save(word);
//                }
//            }
//        }
//        return serviceImpl.getAllWords();
//    }
}
