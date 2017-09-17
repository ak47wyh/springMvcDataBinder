package com.wyh.collection;

import com.wyh.bean.User;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by wyh on 2017/9/16.
 */
public class UserMap {

    private Map<String,User> users;

    @Override
    public String toString() {
        return "UserMap{" +
                "users=" + users +
                '}';
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
