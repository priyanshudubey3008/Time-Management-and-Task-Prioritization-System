package com.timemanager.util;

import com.timemanager.model.User;

public final class Session {
    private static User currentUser;
    private Session() {}
    public static void setUser(User user){ currentUser=user; }
    public static User getUser(){ return currentUser; }
    public static void logout(){ currentUser=null; }
}
