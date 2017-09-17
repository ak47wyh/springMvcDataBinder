package com.wyh.collection;

import com.wyh.bean.User;

import java.util.List;

/**
 * Created by wyh on 2017/9/16.
 */
public class UserList {

    private List<User> users;

    @Override
    public String toString() {
        return "UserList{" +
                "users=" + users +
                '}';
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
