package pe4nik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe4nik.dao.UserDao;
import pe4nik.entity.User;

import java.util.List;

/**
 * Created by Pe4Nik on 24.03.2017.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User getById(Long id) {
        return userDao.findOne(id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
