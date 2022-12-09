package com.test.mychat.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.mychat.annotation.UserLoginToken;
import com.test.mychat.common.util.ParameterCondition;
import com.test.mychat.common.util.RedisUtils;
import com.test.mychat.common.vo.CodeMsg;
import com.test.mychat.common.vo.WrappedResult;
import com.test.mychat.pojo.User;
import com.test.mychat.service.ITockenSevice;
import com.test.mychat.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(tags = "用户接口")
public class UserController {
    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ITockenSevice tockenSevice;

    @ApiOperation("获取用户列表")
    @UserLoginToken
    @RequestMapping(path = "/getUserList",method = RequestMethod.POST)
    public WrappedResult getUsers(@RequestBody ParameterCondition<User> parameterCondition){
        try {
            return userService.queryUserListByCondition(parameterCondition);
        } catch (Exception e){
            return WrappedResult.failedWrappedResult(e.getMessage());
        }
    }


    //Redis test
    @PostMapping(value = "/hello1")
    @ApiOperation(value = "redis测试",notes = "redis存的id")
    public String hello(@RequestParam (value = "id") String id){
        //查询缓存中是否存在
        boolean hasKey = redisUtils.exists(id);
        String str = "";
        if(hasKey){
            //获取缓存
            Object object =  redisUtils.get(id);
            log.info("从缓存获取的数据"+ object);
            str = object.toString();
        }else{
            //从数据库中获取信息
            log.info("从数据库中获取数据");
//            str = testService.test();
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set(id,str,10L, TimeUnit.MINUTES);
            log.info("数据插入缓存" + str);
        }
        return str;
    }

    /**
     * 登录验证
     * @param user
     * @param response
     * @return
     */
    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public WrappedResult login(User user, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        //获取用户账号密码
        User userForBase = new User();
        userForBase.setId(userService.findByUsername(user).getId());
        userForBase.setUsername(userService.findByUsername(user).getUsername());
        userForBase.setPassword(userService.findByUsername(user).getPassword());
        //判断账号或密码是否正确
        if (!userForBase.getPassword().equals(user.getPassword())) {
            return WrappedResult.failedWrappedResult(String.valueOf(CodeMsg.USER_OR_PASS_ERROR));
        } else {
            String token = tockenSevice.getToken(userForBase);
            jsonObject.put("token", token);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return WrappedResult.successWrapedResult(jsonObject);
        }
    }

}
