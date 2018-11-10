package cn.com.work.controller;

import cn.com.work.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Admin on 2018/11/10.
 */

@Component
@RequestMapping(value = "/UserController")
public class UserController {
    @Resource(name = "UserService")
    private UserService userService;
}
