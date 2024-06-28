package com.itheima.file;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@CrossOrigin
public class ImageController {
    @Resource
    UserMapper userMapper;
    @PostMapping("/fileUpload")
    public Result upload(MultipartFile image) throws IOException {
//        1.jpg
        String originalFilename = image.getOriginalFilename();
        System.out.println(originalFilename);
//        1uuid.jpg
        UUID uuid = UUID.randomUUID();
        int indexOf = originalFilename.lastIndexOf(".");
        String prefix = originalFilename.substring(0,indexOf); //前缀文件名
        String suffix = originalFilename.substring(indexOf); //后缀扩展名
        originalFilename = prefix+uuid+suffix;
        System.out.println(originalFilename);
        String path = "D:\\javaee\\tlias-web-management\\src\\main\\resources\\image\\"+originalFilename;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        image.transferTo(file);

        System.out.println(path);
        return Result.success(path);
    }
}
