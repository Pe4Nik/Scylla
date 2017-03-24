package pe4nik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

//    @Autowired
//    UserService userService;

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
}
