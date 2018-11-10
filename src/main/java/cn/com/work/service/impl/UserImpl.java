package cn.com.work.service.impl;/**
 * Created by user on 2018/11/10.
 */

import cn.com.work.dao.UserMapper;
import cn.com.work.entity.User;
import cn.com.work.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author user
 * @create 2018-11-10 8:45
 * @desc 实现类
 */
@Service
public class UserImpl implements UserService{
    @Resource
    private UserMapper userDao ;

    @Override
    public User findAll(String userId) {
        System.out.println("cesjhi ");
        return userDao.selectByPrimaryKey(userId);
    }
}
