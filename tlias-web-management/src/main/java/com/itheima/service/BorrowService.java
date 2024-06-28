package com.itheima.service;


import com.itheima.pojo.Borrow;
import com.itheima.pojo.PageBean;

import java.util.List;

public interface BorrowService {

    List<Borrow> list();

    PageBean page(Integer page, Integer pageSize);

    List<Borrow> selectBybookId(int bookId);

    List<Borrow> selectByMyself(String name);

    List<Borrow> selectByMyselfSearch(String name,String bookId);

    int insert(Borrow borrow);

    int delete(String bookId);
}
