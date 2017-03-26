package pe4nik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe4nik.dao.UserDao;
import pe4nik.dao.WordDao;
import pe4nik.entity.Text;
import pe4nik.entity.User;
import pe4nik.entity.Word;
import pe4nik.service.ServiceImpl;

import java.util.List;

/**
 * Created by Pe4Nik on 22.03.2017.
 */
@RestController
public class Controller {

    @Autowired
    ServiceImpl serviceImpl;

    @Autowired
    private UserDao userDao;

    @Autowired
    private WordDao wordDao;


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
    public String checkUser(@RequestBody User user) {
        User foundUser = serviceImpl.findUserByEmail(user.getEmail());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword()))
            return "Ok";
        return "Wrong password";
    }


    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(@RequestBody User user) {
        return serviceImpl.saveUser(user);
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
}
