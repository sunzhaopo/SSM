package cn.com.work.controller;

import cn.com.work.entity.User;
import cn.com.work.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Admin on 2018/11/10.
 */

@Component
@RequestMapping( "/UserController")
public class UserController {
    @Resource(name = "UserService")
    private UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(){

        return userService.findAll("111");
    }
}
