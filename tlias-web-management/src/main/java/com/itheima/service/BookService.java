package com.itheima.service;

import com.itheima.pojo.Book;
import com.itheima.pojo.PageBean;

import java.util.List;

/**
 * 部门管理
 */

public interface BookService {
    List<Book> list();
    PageBean page(Integer page, Integer pageSize);
    List<Book> selectByNameByType(String name,String type);
    int deletById(Integer id);
    void insert(Book book);

    int upData(Book book);

    int deleteBySub(int id);

}
