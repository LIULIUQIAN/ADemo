package com.example.ademo.manager;

import com.example.ademo.module.user.User;

public class UserManager {

    public static UserManager userManager = null;
    private User user = null;

    public static UserManager getInstance(){
        if (userManager == null){
            synchronized (UserManager.class){
                if (userManager == null){
                    userManager = new UserManager();
                }
                return userManager;
            }
        }else {
            return userManager;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean hasLogined(){
        return user == null ? false : true;
    }

    public void removeUser(){
        this.user = null;
    }
}

