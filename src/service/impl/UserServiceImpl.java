package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User login(User user) {
        //调用DAO的方法查询用户是否存在
        UserDao userDao = new UserDaoImpl();
        return userDao.login(user);
    }
}
