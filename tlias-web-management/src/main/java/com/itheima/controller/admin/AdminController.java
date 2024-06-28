package com.itheima.controller.admin;

import com.itheima.pojo.Admin;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;
    /***
     * 新增管理员信息
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody  Admin admin) {
        log.info("新增管理员", admin);
        adminService.insert(admin);
        return Result.success();
    }

    /***
     * 管理员登录
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        log.info("登录", admin);
        return adminService.login(admin);
    }

    /**
     * 设置用户头像和用户名
     */
    @PostMapping("/setupload")
    public Result setupload(@RequestBody Admin admin){
        log.info("设置头像和用户名",admin);
        adminService.updateByUrl(admin.getUrl(),admin.getName());
        return Result.success();
    }

    @PostMapping("/setupload/name")
    public Result setuploadName(@RequestBody Admin admin){
        log.info("设置头像和用户名",admin);
        adminService.updateByUrl(admin.getUrl(),admin.getName());
        return Result.success();
    }


}
