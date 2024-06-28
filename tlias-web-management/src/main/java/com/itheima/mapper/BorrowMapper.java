package com.itheima.mapper;


import com.itheima.pojo.Borrow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BorrowMapper {
    @Select("select * from borrow")
    List<Borrow> list();

    @Select("select * from borrow where book_id=#{bookId}")
    List<Borrow> selectBybookId(int bookId);

    //  查询当前用户的借阅信息
    @Select("select * from borrow where borrower=#{name}")
    List<Borrow> selectByMyself(String name);

    //  查询当前用户的借阅信息的查询
    @Select("select * from borrow where borrower=#{name} and book_id=#{bookId}")
    List<Borrow> selectByMyselfSearch(String name,String bookId);


    @Insert("insert into borrow(book_id,book_name,borrow_code,borrower,begin_time,end_time)values(#{bookId},#{bookName},#{borrowCode},#{borrower},#{beginTime},#{endTime})")
    int insert(Borrow borrow);

    @Delete("delete from borrow where book_id=#{bookId}")
    int delete(String bookId);
}
