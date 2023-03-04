package service;

import entity.User;

public class LoggedInUser {

    private static LoggedInUser instance = null;

    private static User user;

    public LoggedInUser() {
    }

    public static LoggedInUser getInstance() {
        if (instance == null) {
            instance = new LoggedInUser();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
