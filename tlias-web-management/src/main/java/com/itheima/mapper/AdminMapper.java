package com.itheima.mapper;

import com.itheima.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where name=#{name}")
    Admin selectByName(String name);
    @Insert("insert into admin(name,password,age,email) values(#{name},#{password},#{age},#{email})")
    void insert(Admin admin);
    //    添加url
    @Update("UPDATE admin SET url=#{url} where name=#{name}")
    int updateByUrl(String url,String name);
    @Update("UPDATE admin SET name=#{newName} where name=#{oldName}")
    int updateByName(String oldName,String newName);
}
