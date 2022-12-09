package com.test.mychat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.mychat.common.util.JsonUtil;
import com.test.mychat.common.util.ParameterCondition;
import com.test.mychat.common.vo.WrappedResult;
import com.test.mychat.mapper.UserMapper;
import com.test.mychat.pojo.User;
import com.test.mychat.service.IUserService;
//import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public WrappedResult queryUserListByCondition(ParameterCondition<User> parameterCondition) throws Exception {
        try {
            User user = JsonUtil.getEntityClazz(parameterCondition.getFilter(),User.class);
            PageHelper.startPage(parameterCondition.getPageIndex(),parameterCondition.getPageSize());
            List<User> pages = getBaseMapper().queryUserListByCondition(user);
            PageInfo<User> pageInfo = new PageInfo<>(pages);
            return WrappedResult.successWrapedResult(pageInfo.getList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public User findByUsername(User user) {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        List<User> users = getBaseMapper().queryUserListByCondition(user);
        return users.get(0);
    }

    @Override
    public User findUserById(String userId) {
        User user = getBaseMapper().selectById(userId);
        return user;
    }
}
