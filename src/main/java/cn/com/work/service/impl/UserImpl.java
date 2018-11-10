package cn.com.work.service.impl;/**
 * Created by user on 2018/11/10.
 */

import cn.com.work.dao.UserMapper;
import cn.com.work.service.UserService;

import javax.annotation.Resource;

/**
 * @author user
 * @create 2018-11-10 8:45
 * @desc 实现类
 */
public class UserImpl implements UserService{
    @Resource(name = "UserDao")
    private UserMapper userDao ;
}
