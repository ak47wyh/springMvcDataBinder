package com.wyh.collection;

import com.wyh.bean.User;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wyh on 2017/9/16.
 */
public class UserSet {

    private Set<User> users;

    public UserSet() {
         users = new LinkedHashSet<User>();
        User user = new User();
        users.add(user);
        User user1 = new User();
        users.add(user1);
    }

    @Override
    public String toString() {
        return "UserList{" +
                "users=" + users +
                '}';
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
