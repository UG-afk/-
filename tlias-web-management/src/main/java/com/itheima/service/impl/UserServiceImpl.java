package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Borrow;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1. 设置分页参数
        PageHelper.startPage(page, pageSize);

        //2. 执行查询
        List<User> userList = userMapper.list();
        Page<User> p = (Page<User>) userList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public Result login(User user) {
        if (userMapper.selectByName(user.getName()) != null) {
            if (user.getPassword().equals(userMapper.selectByName(user.getName()).getPassword())) {
                Map<String,Object> claims=new HashMap<>();
                claims.put("name",user.getName());
                claims.put("password",user.getPassword());
                claims.put("power",1);
                String jwt= JwtUtils.generateJwt(claims);
                return Result.success(jwt);
            } else {
                return Result.error("密码错误");
            }
        } else {
            return Result.error("用户不存在");
        }

    }


    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> selectByNames(String name) {
        return userMapper.selectByNames(name);
    }

    @Override
    public PageBean pageByBadUser(Integer page, Integer pageSize) {
        //1. 设置分页参数
        PageHelper.startPage(page, pageSize);

        //2. 执行查询
        List<User> userList = userMapper.selectByCredit();
        Page<User> p = (Page<User>) userList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public User selectByCreditAndName(String name) {
        return userMapper.selectByCreditAndName(name);
    }

    @Override
    public Result deleteByName(String name) {
        int flag = userMapper.deleteByName(name);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }

    @Override
    public int updateByCreditAndName(String name) {
        return userMapper.updateByCreditAndName(name);
    }

    @Override
    public User selectBySame(String name) {
        return userMapper.selectBySame(name);
    }

    public int updateByUrl(String url,String name){
        return userMapper.updateByUrl(url,name);
    }

}


