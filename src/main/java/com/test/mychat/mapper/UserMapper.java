package com.test.mychat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.mychat.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> queryUserListByCondition(User user);
}
