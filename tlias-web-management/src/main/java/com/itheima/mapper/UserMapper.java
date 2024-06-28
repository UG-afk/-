package com.itheima.mapper;

import com.itheima.pojo.Admin;
import com.itheima.pojo.Borrow;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> list();
//    登录用
    @Select("select * from user where name=#{name}")
    User selectByName(String name);
//    搜索用
    @Select("select * from user where name like concat('%',#{name},'%')")
    List<User> selectByNames(String name);
//    借阅搜索用户是否存在
    @Select("select * from user where name=#{name}")
    User selectBySame(String name);
//    注册用
    @Insert("insert into user(name,password,age,email,score,credit) values(#{name},#{password},#{age},#{email},0,80)")
    int insert(User user);
//    查询当前用户借阅记录
//    @Select("select * from user where name like concat('%',#{name},'%')")
//    List<Borrow> selectByMyBorrow(String name)
//    删除用
    @Delete("delete from user where name=#{name}")
    int deleteByName(String name);
//    查找失信人员
    @Select("select * from user where credit = 0")
    List<User> selectByCredit();

//    查询指定失信人员
    @Select("select * from user where credit = 0 and name like concat('%',#{name},'%')")
    User selectByCreditAndName(String name);

    // 移除失信名单
    @Update("UPDATE user SET credit=10 where name=#{name}")
    int updateByCreditAndName(String name);

//    添加url
    @Update("UPDATE user SET url=#{url} where name=#{name}")
    int updateByUrl(String url,String name);


}
