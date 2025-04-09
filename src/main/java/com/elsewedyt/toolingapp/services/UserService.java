package com.elsewedyt.toolingapp.services;

import com.elsewedyt.toolingapp.dao.UserDao;
import com.elsewedyt.toolingapp.models.User;

import java.util.Optional;

import static com.elsewedyt.toolingapp.services.SettingService.APP_BUNDLE;
import static com.elsewedyt.toolingapp.services.WindowUtils.ALERT;
import static com.elsewedyt.toolingapp.services.WindowUtils.ALERT_WARNING;

public class UserService {

    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public Optional<User> loadUserData(int userId) {
        try {
            return Optional.ofNullable(userDao.loadUserData(userId));
        } catch (Exception ex) {
            //    LOG_EXCEP(this.getClass().getName(), "loadUserData", ex);
            return Optional.empty();
        }
    }

    public int checkLogIn(String userName, String pass) {
        // Check for empty or null username
        if (userName == null || userName.isEmpty()) {
            ALERT("", APP_BUNDLE().getString("USER_NAME_INVALID"), ALERT_WARNING);
        }
        // Check for empty or null password
        if (pass == null || pass.isEmpty()) {
            ALERT("", APP_BUNDLE().getString("PASSWORD_INVALID"), ALERT_WARNING);
        }
        // If both username and password are valid, attempt to log in
        if ((userName != null && !userName.isEmpty()) && (pass != null && !pass.isEmpty())) {
            return userDao.logIn(userName, pass);
        } else {
            return -1;
        }
    }
}
