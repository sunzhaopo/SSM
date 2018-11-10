package cn.com.work.service;/**
 * Created by user on 2018/11/10.
 */

import cn.com.work.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resources;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @author user
 * @create 2018-11-10 8:42
 * @desc 用户接口
 */
public interface UserService {
    User findAll(String userId );
}
