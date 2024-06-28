package com.itheima.mapper;

import com.itheima.pojo.Book;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book")
    List<Book> list();

    @Select("select * from book where name like concat('%',#{name},'%')")
    List<Book> selectByNames(String name);
    @Select("select * from book where name like #{name} && author like #{author} && publish like #{publish}" )
    Book selectSame(Book book);

    @Select("select * from book where id =#{id}" )
    Book selectById(int id);

    @Delete("delete from book where id=#{id}")
    int deletById(Integer id);

    @Insert("insert into book(name,type,author,number,price,publish)values(#{name},#{type},#{author},#{number},#{price},#{publish})")
    int insert(Book book);
    @Update("UPDATE book SET number=#{number} where name=#{name}")
    int upDataNumber(int number,String name);

    @Update("UPDATE book SET name=#{name},type=#{type},author=#{author},number=#{number},price=#{price},publish=#{publish} where id=#{id}")
    int upData(Book book);

    @Select("<script>"
            + "select * from book"
            + " <where>"
            + " <if test='name != null and name!= &apos;&apos;' > "
            + "	    and name like concat('%',#{name},'%')"
            + "	</if>"
            + " <if test='type != null and type!= &apos;&apos;' > "
            + "	    and type like concat('%',#{type},'%')"
            + "	</if>"
            + "</where>"
            + "</script>")
    List<Book> selectByNameByType(String name,String type);
}
