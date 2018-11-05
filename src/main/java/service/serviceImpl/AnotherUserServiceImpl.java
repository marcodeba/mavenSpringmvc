package service.serviceImpl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

@Service
public class AnotherUserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public String getUserName() {
        return userDao.getUserName();
    }
}

