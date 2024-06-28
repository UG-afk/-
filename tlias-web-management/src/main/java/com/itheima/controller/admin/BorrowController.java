package com.itheima.controller.admin;


import com.itheima.pojo.Borrow;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.BorrowService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    BorrowService borrowService;

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
        PageBean pageBean = borrowService.page(page,pageSize);
        return Result.success(pageBean);
    }

    /**
     * 根据图书id查询信息
     * @return
     */
    @GetMapping("/{bookId}")
    public Result selectBybookId(@PathVariable int bookId) {
        log.info("根据书名查询信息");
        System.out.println(bookId);
        List<Borrow> borrowList = borrowService.selectBybookId(bookId);
        return Result.success(borrowList);
    }


    /**
     * 查询当前用户的借阅信息
     */
    @GetMapping("/myself/{name}")
    public Result selectByMyself(@PathVariable String name){
        log.info("查询当前用户的借阅信息");
        System.out.println(name);
        List<Borrow> borrowList=borrowService.selectByMyself(name);
        return Result.success(borrowList);
    }

    /**
     * 查询当前用户的借阅信息的搜索
     */
    @GetMapping("/myself/{name}/{borrower}")
    public Result selectByMyself(@PathVariable String name,@PathVariable String borrower){
        log.info("查询当前用户的借阅信息");
        System.out.println(name);
        System.out.println(borrower);
        List<Borrow> borrowList=borrowService.selectByMyselfSearch(name,borrower);
        return Result.success(borrowList);
    }

    /**
     * 新增借阅
     */
    @PostMapping
    public Result insert(@RequestBody Borrow borrow) {
        System.out.println(borrow);
        log.info("新增借阅",borrow);
        borrowService.insert(borrow);
        return Result.success();
    }

    /**
     * 新增借阅
     */
    @DeleteMapping
    public Result delete(String bookId) {
        System.out.println(bookId);
        log.info("删除借阅",bookId);
        borrowService.delete(bookId);
        return Result.success();
    }
}
