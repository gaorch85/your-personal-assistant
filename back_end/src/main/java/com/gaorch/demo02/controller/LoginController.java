package com.gaorch.demo02.controller;

import com.gaorch.demo02.entity.User;
import com.gaorch.demo02.service.LoginService;
import com.gaorch.demo02.utils.Response;
import com.gaorch.demo02.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/signup")
    public Response signup(@RequestBody User user)
    {
        Result result = loginService.signup(user);
        return result.isSuccess() ? Response.ok() : Response.error();
    }

    @PostMapping("/login")
    public Response login(@RequestBody User user)
    {
        Result result = loginService.login(user);
        return result.isSuccess() ?
                Response.ok().setData(result.getData()) : Response.error();
    }

}
