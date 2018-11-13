package cn.com.work.dao;

import cn.com.work.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseDao<User,String> {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);
}