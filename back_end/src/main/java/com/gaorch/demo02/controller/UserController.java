package com.gaorch.demo02.controller;

import com.gaorch.demo02.entity.Employee;
import com.gaorch.demo02.entity.User;
import com.gaorch.demo02.mapper.UserMapper;
import com.gaorch.demo02.utils.JwtUtils;
import com.gaorch.demo02.utils.PasswordUtils;
import com.gaorch.demo02.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;     //用于解析请求头中的token

    @PostMapping("/signup")
    public Result signup(@RequestBody User user)
    {
        user.setSalt(PasswordUtils.generateSalt());
        user.setPassword(PasswordUtils.hashPassword(user.getPassword(), user.getSalt()));
        User selectUser = userMapper.selectByUsername(user.getUsername());
        if(selectUser == null)
        {
            int i = userMapper.insert(user);
            if(i > 0)
                return Result.ok();
            else
                return Result.error();
        }
        else
            return Result.error();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user)
    {
        User selectUser = userMapper.selectByUsername(user.getUsername());
        if(selectUser != null)
        {
            String salt = selectUser.getSalt();
            String curHashPassword = PasswordUtils.hashPassword(user.getPassword(), salt);
            if(curHashPassword.equals(selectUser.getPassword()))
            {
                String token = JwtUtils.generateToken(user.getUsername());
                System.out.println(token);
                return Result.ok().data("token", token);
            }
            else
                return Result.error();
        }
        else
            return Result.error();
    }

    @GetMapping("/getInfo")
    public Result getInfo()
    {
        String token = request.getHeader("X-token");
        //System.out.println(token);
        try {
            String username = JwtUtils.getClaimsByToken(token).getSubject();
            User user = userMapper.selectByUsername(username);
            if (user != null) {
                System.out.println(user);
                return Result.ok().data("items", user);
            } else
                return Result.unAuthorized();
        } catch (Exception e)
        {
            return Result.unAuthorized();
        }
    }

    @PutMapping("/changePassword")
    public Result changePassword(@RequestBody User data)
    {
        String password = data.getPassword();
        System.out.println(password);
        String token = request.getHeader("X-token");
        //System.out.println(token);
        try {
            String username = JwtUtils.getClaimsByToken(token).getSubject();
            User user = userMapper.selectByUsername(username);
            if (user != null) {
                System.out.println(user);
                user.setSalt(PasswordUtils.generateSalt());
                user.setPassword(PasswordUtils.hashPassword(password, user.getSalt()));
                int i = userMapper.updateById(user);
                if(i > 0)
                    return Result.ok();
                else
                    return Result.error();
            } else
                return Result.unAuthorized();
        } catch (Exception e)
        {
            return Result.unAuthorized();
        }
    }

    @PutMapping("/user/update")
    public Result update(@RequestBody User user)
    {
        String token = request.getHeader("X-token");
        //System.out.println(token);
        if(JwtUtils.isAuthrize(token))
        {
            int i = userMapper.updateById(user);
            if(i > 0)
                return Result.ok();
            else
                return Result.error();
        }
        else
            return Result.unAuthorized();
    }

    @DeleteMapping("/user")
    public Result deactivateAccount()
    {
        String token = request.getHeader("X-token");
        //System.out.println(token);
        try {
            String username = JwtUtils.getClaimsByToken(token).getSubject();
            boolean ans = userMapper.deleteByUsername(username);
            if(ans)
            {
                return Result.ok();
            }
            else
                return Result.error();
        }
        catch (Exception e)
        {
            return Result.unAuthorized();
        }
    }
}
