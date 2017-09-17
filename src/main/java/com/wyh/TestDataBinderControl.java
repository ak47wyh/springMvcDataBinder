package com.wyh;

import com.wyh.bean.Person;
import com.wyh.bean.User;
import com.wyh.collection.UserList;
import com.wyh.collection.UserMap;
import com.wyh.collection.UserSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by wyh on 2017/9/15.
 */
@Controller
public class TestDataBinderControl {

    /**
     * http://localhost:8080/baseType?age=1
     * 基本数据
     * @param age
     * @return
     */
    @RequestMapping("baseType")
    @ResponseBody
    public String baseType(int age){

        return "age:"+age;
    }

    /**
     * http://localhost:8080/packageType?age=
     * 封装数据
     * @param age
     * @return
     */
    @RequestMapping("packageType")
    @ResponseBody
    public String packageType(Integer age){

        return "age:"+age;
    }

    /**
     * http://localhost:8080/array?name=1&name=2&name=3
     * 封装数据
     * @param name
     * @return
     */
    @RequestMapping("array")
    @ResponseBody
    public String array(String[] name){
        StringBuffer str = new StringBuffer();
        for(String nstr: name){
            str.append(nstr+",");
        }
        return "array:"+str.toString();
    }

    /**
     * http://localhost:8080/objectType?name=wang&age=2
     * @param user
     * @return
     */
    @RequestMapping("objectType")
    @ResponseBody
    public String object(User user){
        return user.toString();
    }

    /**
     * http://localhost:8080/obj2?name=wang&age=2&contactInfo.address=cccc&contactInfo.phoneNum
     * @param person
     * @return
     */
    @RequestMapping("obj2")
    @ResponseBody
    public String obj2(Person person){
        return person.toString();
    }

    /**
     * http://localhost:8080/obj3?name=wang&age=1
     * http://localhost:8080/obj3?user.name=wang&age=1&person.name=cc
     * @param person
     * @param user
     * @return
     */
    @RequestMapping("obj3")
    @ResponseBody
    public String obj3(Person person,User user){
        return person.toString() +"  "+user.toString();
    }

    @InitBinder("user")
    public void initUser(WebDataBinder binder){
        binder.setFieldDefaultPrefix("user.");
    }

    @InitBinder("person")
    public void initPerson(WebDataBinder binder){
        binder.setFieldDefaultPrefix("person.");
    }


    @RequestMapping("list")
    @ResponseBody
    public String list(List<User> user){
        return user.toString();
    }

    /**
     * http://localhost:8080/listPackage?users[0].name=wang&users[1].name=cc
     * 如果如下直接跳到6中间会有很多空值，浪费空间
     * http://localhost:8080/listPackage?users[0].name=wang&users[6].name=cc
     * 据说无法直接接收集合需要封装后，才能接收
     * @param userList
     * @return
     */
    @RequestMapping("listPackage")
    @ResponseBody
    public String listPackage(UserList userList){
        return userList.toString();
    }

    /**
     * http://localhost:8080/setPackage?users[0].name=wang&users[1].name=cc
     * 需要对集合初始化，不能大于集合的长度
     * @param users
     * @return
     */
    @RequestMapping("setPackage")
    @ResponseBody
    public String setPackage(UserSet users){

        return users.toString();
    }

    /**
     * http://localhost:8080/mapPackage?users[%22c%22].age=1&users[%22c%22].name=www&users[%22d%22].age=1&users[%22d%22].name=www
     *
     * http://localhost:8080/mapPackage?users[%22c%22].age=1&users[%22c%22].name=www&users[%22c%22].age=1&users[%22c%22].name=www
     *
     * @param users
     * @return
     */
    @RequestMapping("mapPackage")
    @ResponseBody
    public String mapPackage(UserMap users){

        return users.toString();
    }

    /**
     *
     * http://localhost:8080/jsonPackage
     * {name:wang,
     * age:1,
     * email:dd}
     *
     * @param user
     * @return
     */
    @RequestMapping("jsonPackage")
    @ResponseBody
    public String json(@RequestBody User user){

        return user.toString();
    }


    /**
     *  http://localhost:8080/xmlPackage
     *
     * <?xml version="1.0" encoding="utf-8">
     *     <people>
     *         <name>wang</name>
     *         <email>163.com</>
     *      </people>
     *
     * @param user
     * @return
     */
    @RequestMapping("xmlPackage")
    @ResponseBody
    public String xml(@RequestBody User user){

        return user.toString();
    }


    /**
     * http://localhost:8080/convert?bool=yes
     * @param bool
     * @return
     */
    @RequestMapping("convert")
    @ResponseBody
    public String convert( Boolean bool){

        return bool.toString();
    }

    @RequestMapping("formatDate")
    @ResponseBody
    public String formatDate( Date date){

        return date.toString();
    }


}
