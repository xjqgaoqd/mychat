package com.test.mychat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.mychat.pojo.TestUser;
import com.test.mychat.pojo.User;

import java.util.List;

public interface TestUserMapper extends BaseMapper<TestUser> {

    List<TestUser> queryTestUserListByCondition(TestUser user);
}
