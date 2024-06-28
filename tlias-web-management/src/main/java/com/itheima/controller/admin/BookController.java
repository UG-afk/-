package com.itheima.controller.admin;

import com.itheima.pojo.Book;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

//    /***
//     * 查询全部图书信息
//     * @return
//     */
//
//    //@RequestMapping(value = "/books",method = RequestMethod.GET)
//    @GetMapping
//    public Result list() {
//        log.info("查询全部图书数据");
//        List<Book> bookList = bookService.list();
//        return Result.success(bookList);
//    }


    /***
     * 分页查询全部图书信息
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize
                      ){

        System.out.println(page);
        System.out.println(pageSize);
        log.info("分页查询, 参数: {},{}",page,pageSize);
        //调用service分页查询
        PageBean pageBean = bookService.page(page,pageSize);
        return Result.success(pageBean);
    }


    /***
     * 借阅删除
     * @return
     */
    @DeleteMapping("/borrow/{id}")
    public Result deleteByBorrow(@PathVariable int id){
        log.info("借阅删除");
        bookService.deleteBySub(id);
        return Result.success();
    }

    /**
     * 根据书名查询信息
     * @return
     */
    @GetMapping("search")
    public Result selectByName (String name, String type) {
        log.info("根据书名和种类查询信息");
        System.out.println(name);
        System.out.println(type);
        List<Book> bookList = bookService.selectByNameByType(name,type);
        return Result.success(bookList);
    }

    /***
     * 根据id删除书籍
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deletById(@PathVariable Integer id) {
        log.info("根据id删除书籍信息");
        int d=bookService.deletById(id);
        return Result.success(d);
    }

    /***
     * 新增书籍
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody Book book) {
        System.out.println(book);
        log.info("新增书籍",book);
        bookService.insert(book);
        return Result.success();
    }


    @PutMapping
    public Result upData(@RequestBody Book book){
        log.info("根据id修改书籍信息");
        int d=bookService.upData(book);
        return Result.success(d);
    }



    /**
     * 下载电子版
     */
    @PostMapping("/download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String filename) throws Exception {

        String path=request.getServletContext().getRealPath("/files");//        下载文件所在路径
        File file =new File(path+File.separator+filename);//        创建文件对象
        HttpHeaders headers=new HttpHeaders();//        设置消息头
        filename=getFileName(String.valueOf(request),filename);  //解决中文乱码问题
        headers.setContentDispositionFormData("attachment",filename);//        打开文件
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);//        下载返回的文件数据
//        使用ResponseEntity对象封装返回下载数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }


    public static String getFileName(String agent, String filename) throws UnsupportedEncodingException {
        if (agent.contains("MSIE")) {
            // IE浏览器(版本太低的也无法打开)
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            Base64.Encoder encoder = Base64.getEncoder();
            filename = "=?utf-8?B?" + encoder.encodeToString(filename.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
