package cn.com.work.controller;

import cn.com.work.entity.User;
import cn.com.work.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018/11/10.
 */

@Component
@RequestMapping( "/UserController")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public PageInfo getUser(){
        PageHelper.startPage(0, 15);
        List<User> list= new ArrayList();
        list.add(userService.findAll("111"));
        return   new PageInfo(list);
    }
}
