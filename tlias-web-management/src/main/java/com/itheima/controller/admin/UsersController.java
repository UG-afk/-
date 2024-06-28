package com.itheima.controller.admin;


import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/admin/users")
public class UsersController {

    @Autowired
    private UserService userService;
    /***
     * 新增用户信息
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody @RequestParam(value = "ruleForm") User user) {
        log.info("新增管理员", user);
        userService.insert(user);
        return Result.success();
    }

    /***
     * 用户登录
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("登录", user);
        return userService.login(user);
    }



    /***
     * 分页查询全部用户信息
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize
    ){
        System.out.println(page);
        System.out.println(pageSize);
        log.info("分页查询, 参数: {},{}",page,pageSize);
        //调用service分页查询
        PageBean pageBean = userService.page(page,pageSize);
        return Result.success(pageBean);
    }

    /**
     * 检测借阅人是否存在
     */
    @GetMapping("/borrow/{name}")
    public Result Same(@PathVariable String name){
        log.info("检测借阅人是否存在");
        User user = userService.selectBySame(name);
        if(user==null){
            return Result.error("无此用户");
        }else {
            return Result.success();
        }

    }

    /**
     * 根据用户名查询信息
     * @return
     */
    @GetMapping("/{name}")
    public Result selectByName(@PathVariable String name) {
        log.info("根据用户名查询信息");
        System.out.println(name);
        List<User> userList = userService.selectByNames(name);
        return Result.success(userList);
    }
    /**
     * 根据用户名删除信息
     * @return
     */

    @DeleteMapping("/{name}")
    public Result deleteByName(@PathVariable String name){
        log.info("根据用户名删除信息");
        return userService.deleteByName(name);
    }


    /***
     * 分页查询失信用户信息
     * @return
     */
    @GetMapping("/badUser")
    public Result pageByBadUser(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize
    ){
        System.out.println(page);
        System.out.println(pageSize);
        log.info("分页查询, 参数: {},{}",page,pageSize);
        //调用service分页查询
        PageBean pageBean = userService.pageByBadUser(page,pageSize);
        return Result.success(pageBean);
    }

    /**
     * 根据用户名查询失信名单
     * @return
     */
    @GetMapping("/badUser/{name}")
    public Result selectByBadUserName(@PathVariable String name) {
        log.info("根据用户名查询失信名单");
        System.out.println(name);
        User user = userService.selectByCreditAndName(name);
        return Result.success(user);
    }


    /**
     * 移除失信名单
     * @return
     */
    @PutMapping("/badUser")
    public Result updateByCreditAndName(String name) {
        log.info("移除失信名单");
        System.out.println(name);
        int d = userService.updateByCreditAndName(name);
        return Result.success(d);
    }



}
