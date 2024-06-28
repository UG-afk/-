package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private String name;
    private String  password;
    private int age;
    private String email;
    private int score;
    private int credit;
    private String url;
}
