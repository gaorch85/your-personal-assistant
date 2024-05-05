package com.gaorch.demo02.controller;

import com.gaorch.demo02.entity.User;
import com.gaorch.demo02.service.PersonalService;
import com.gaorch.demo02.utils.Response;
import com.gaorch.demo02.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping("/getInfo")
    public Response getInfo() {
        Result result = personalService.getInfo();
        return result.isSuccess() ?
                Response.ok().setData(result.getData()) : Response.error();
    }

    @PutMapping("/changePassword")
    public Response changePassword(@RequestBody User user) {
        Result result = personalService.changePassword(user);
        return result.isSuccess() ? Response.ok() : Response.error();
    }

    @PutMapping("/user/update")
    public Response update(@RequestBody User user) {
        Result result = personalService.update(user);
        return result.isSuccess() ? Response.ok() : Response.error();
    }

    @DeleteMapping("/user")
    public Response deactivateAccount() {
        Result result = personalService.delete();
        return result.isSuccess() ? Response.ok() : Response.error();
    }

}
