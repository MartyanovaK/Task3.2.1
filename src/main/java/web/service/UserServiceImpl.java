package web.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;
import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void UserDao(UserDao userDao) {
        this.userDao = userDao;
    }



    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }


    @Override
    public void add(User user) {
        userDao.add(user);
    }


    @Override
    public void delete(long id) {
        userDao.delete(id);
    }


    @Override
    public void edit(User user) {
        userDao.edit(user);
    }


    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }
}
