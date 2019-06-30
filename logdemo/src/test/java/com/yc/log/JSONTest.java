package com.yc.log;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.yc.log.test.model.User;
public class JSONTest {
    
    @Test
    public void JSONDemo() {
        User [] users = {new User("hty",22),new User("wpx",33),new User("ssl",88)};
        String user = JSONObject.toJSONString(users);
        System.out.println(user);
        List<User> list = JSONObject.parseArray(user, User.class);
        list.forEach(System.out::println);
        String string = JSONObject.toJSONString(list);
        System.out.println(string);
    }
}
