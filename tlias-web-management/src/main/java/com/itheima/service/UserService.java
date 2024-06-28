package com.itheima.service;

import com.itheima.pojo.Borrow;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;

import java.util.List;

public interface UserService {
    PageBean page(Integer page, Integer pageSize);
    Result login(User user);
    int insert(User user);

    List<User> selectByNames(String name);

    PageBean pageByBadUser(Integer page, Integer pageSize);

    User selectByCreditAndName(String name);

    Result deleteByName(String name);
    int updateByCreditAndName(String name);

    User selectBySame(String name);

//    int updateByPassword(String password,String name);
    int updateByUrl(String url,String name);
}
