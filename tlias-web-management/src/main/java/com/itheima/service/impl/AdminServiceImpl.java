package com.itheima.service.impl;

import com.itheima.mapper.AdminMapper;
import com.itheima.pojo.Admin;
import com.itheima.pojo.Result;
import com.itheima.service.AdminService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Result login(Admin admin) {
        if(adminMapper.selectByName(admin.getName())!=null){
            if(admin.getPassword().equals(adminMapper.selectByName(admin.getName()).getPassword())) {
                Map<String,Object> claims=new HashMap<>();
                claims.put("name",admin.getName());
                claims.put("password",admin.getPassword());
                claims.put("power",1);
                String jwt= JwtUtils.generateJwt(claims);
                return Result.success(jwt);
            }
            else{
                return Result.error("密码错误");
            }
        }else{
            return Result.error("用户不存在");
        }

    }

    @Override
    public void insert(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public int updateByUrl(String url, String name) {
        return adminMapper.updateByUrl(url,name);
    }
}
