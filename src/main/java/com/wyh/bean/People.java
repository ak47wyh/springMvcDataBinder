package com.wyh.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wyh on 2017/9/15.
 */
@XmlRootElement(name = "people")
public class People {

    private String name;
    private String email;

    @XmlElement(name = "name")
    public String getEmail() {
        return email;
    }
    @XmlElement(name = "emial")
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
