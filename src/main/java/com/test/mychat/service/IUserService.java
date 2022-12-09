package com.test.mychat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.mychat.common.util.ParameterCondition;
import com.test.mychat.common.vo.WrappedResult;
import com.test.mychat.pojo.User;

import java.util.List;

public interface IUserService extends IService<User> {

    WrappedResult queryUserListByCondition(ParameterCondition<User> parameterCondition) throws Exception;

}
