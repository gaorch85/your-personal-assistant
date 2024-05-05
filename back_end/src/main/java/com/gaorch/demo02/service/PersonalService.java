package com.gaorch.demo02.service;

import com.gaorch.demo02.entity.User;
import com.gaorch.demo02.mapper.UserMapper;
import com.gaorch.demo02.utils.JwtUtils;
import com.gaorch.demo02.utils.PasswordUtils;
import com.gaorch.demo02.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalService
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;     //用于解析请求头中的token

    public Result getInfo()
    {
        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User user = userMapper.selectByUsername(username);
        if (user != null)
            return Result.ok().data("items", user);
        else
            return Result.error();
    }

    public Result changePassword(User user)
    {
        String password = user.getPassword();
        User selectUser = userMapper.selectByUsername(user.getUsername());
        if (selectUser != null)
        {
            selectUser.setSalt(PasswordUtils.generateSalt());
            selectUser.setPassword(PasswordUtils.hashPassword(password, selectUser.getSalt()));
            int i = userMapper.updateById(user);
            if(i>0)
                return Result.ok();
        }
        return Result.error();
    }

    public Result update(User user)
    {
        int i = userMapper.updateById(user);
        return i > 0 ? Result.ok() : Result.error();
    }

    public Result delete()
    {
        String token = request.getHeader("X-token");
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        int i =  userMapper.deleteByUsername(username);
        return i > 0 ? Result.ok() : Result.error();
    }

}
