package com.itheima.service;

import com.itheima.pojo.Admin;
import com.itheima.pojo.Result;

public interface AdminService {
    Result login(Admin admin);
    void insert(Admin admin);
    int updateByUrl(String url,String name);
}
