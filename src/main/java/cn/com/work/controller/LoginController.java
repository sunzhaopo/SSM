package cn.com.work.controller;/**
 * Created by user on 2018/11/12.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : user
 * @Date: 2018/11/12 20:58
 * @Description:
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login/toLogin";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "login/toRegister";
    }
}