package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {

    private int bookId;
    private int borrowCode;
    private String bookName;
    private String borrower;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private LocalDateTime beginTime;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private LocalDateTime endTime;

}
