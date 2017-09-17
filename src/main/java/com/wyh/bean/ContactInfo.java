package com.wyh.bean;

/**
 * Created by wyh on 2017/9/15.
 */
public class ContactInfo {

    private String phoneNum;

    private String address;

    @Override
    public String toString() {
        return "ContactInfo{" +
                "phoneNum='" + phoneNum + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
