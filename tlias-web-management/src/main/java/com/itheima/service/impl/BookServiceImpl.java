package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.BookMapper;
import com.itheima.pojo.Book;


import com.itheima.pojo.PageBean;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> list() {
        return bookMapper.list();
    }

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<Book> bookList = bookMapper.list();
        Page<Book> p = (Page<Book>) bookList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public List<Book> selectByNameByType(String name,String type) {
        return bookMapper.selectByNameByType(name,type);
    }

    @Override
    public int deletById(Integer id) {
        return bookMapper.deletById(id);
    }

    @Override
    public void insert(Book book) {
        if(bookMapper.selectSame(book)==null){
            bookMapper.insert(book);
        }else {
            bookMapper.upDataNumber(book.getNumber()+(bookMapper.selectSame(book)).getNumber(),book.getName());
        }
    }

    @Override
    public int upData(Book book) {
        return bookMapper.upData(book);
    }

    @Override
    public int deleteBySub(int id) {
        if(bookMapper.selectSame(bookMapper.selectById(id)).getNumber()>1){
            return bookMapper.upDataNumber(bookMapper.selectById(id).getNumber()-1,bookMapper.selectById(id).getName());
        }else {
            return bookMapper.deletById(id);
        }
    }


}
