package com.itheima.controller.user;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/user/users")
public class UserController {
    @Autowired
    UserService userService;
    /**
     * 新增用户信息
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody  User user) {
        log.info("新增用户", user);
        userService.insert(user);
        return Result.success();
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("用户登录", user);
        return userService.login(user);

    }
    /**
     * 用户头像和用户名
     */
    @GetMapping("/upload/{name}")
    public Result upload(@PathVariable String name){
        log.info("用户信息获取", name);
        User user=userService.selectBySame(name);
        return Result.success(user);
    }

    /**
     * 设置用户头像
     */
    @PostMapping("/setupload")
    public Result setupload(@RequestBody User user){
        log.info("设置头像和用户名",user);
        userService.updateByUrl(user.getUrl(),user.getName());
        return Result.success();
    }


}
