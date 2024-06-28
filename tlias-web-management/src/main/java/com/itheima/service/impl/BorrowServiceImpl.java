package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.BookMapper;
import com.itheima.mapper.BorrowMapper;
import com.itheima.pojo.Book;
import com.itheima.pojo.Borrow;
import com.itheima.pojo.PageBean;
import com.itheima.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    BorrowMapper borrowMapper;


    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<Borrow> bookList = borrowMapper.list();
        Page<Borrow> p = (Page<Borrow>) bookList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public List<Borrow> selectBybookId(int bookId) {
        return borrowMapper.selectBybookId(bookId);

    }


    @Override
    public List<Borrow> selectByMyself(String name) {
       return borrowMapper.selectByMyself(name);
    }

    @Override
    public List<Borrow> selectByMyselfSearch(String name, String bookId) {
        return borrowMapper.selectByMyselfSearch(name,bookId);
    }

    @Override
    public int insert(Borrow borrow) {

        return borrowMapper.insert(borrow);
    }

    @Override
    public int delete(String bookId) {
        return borrowMapper.delete(bookId);
    }


}
