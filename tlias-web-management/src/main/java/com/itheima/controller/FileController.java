package com.itheima.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.Resource;
import com.itheima.utils.JSONFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/fileload")
public class FileController {
    @GetMapping
    public String fileLoad(MultipartFile[] files,
                           HttpServletRequest request) throws Exception {
        System.out.println();

        int i=0;
        System.out.println();
        //设置上传的文件所存放的路径
        String path = request.getServletContext().getRealPath("/") + "files/";
        ObjectMapper mapper = new ObjectMapper();
        if (files != null && files.length > 0) {
            //循环获取上传的文件
            for (MultipartFile file : files) {
                //获取上传文件的名称
                String filename = file.getOriginalFilename();
                ArrayList<Resource> list = new ArrayList<>();
                //读取files.json文件中的文件名称
                String json = JSONFileUtils.readFile(path + "/files.json");
                if (json.length() != 0) {
                    //将files.json的内容转为对象集合
                    list = (ArrayList<Resource>) mapper.readValue(json,
                            new TypeReference<List<Resource>>() {
                            });
                    for (Resource resource : list) {
                        //如果上传的文件在files.json文件中有同名文件，将当前上传的文件重命名，以避免重名
                        if (resource.getName().split("\\.")[0].contains(filename.split("\\.")[0])){
                            i++;
                        }
                    }
                    if(i!=0){
                        String[] split = filename.split("\\."); //正则表达式
                        filename = split[0] + "("+i+")."+ split[1];
                    }
                }
                // 文件保存的全路径
                String filePath = path + filename;
                // 保存上传的文件
                file.transferTo(new File(filePath));
                list.add(new Resource(filename));
                json = mapper.writeValueAsString(list); //将集合中转换成json
                //将上传文件的名称保存在files.json文件中
                JSONFileUtils.writeFile(json, path + "/files.json");
            }
            request.setAttribute("msg", "(上传成功)");
            return "fileload";
        }
        request.setAttribute("msg", "(上传为空)");
        return "fileload";
    }

    //   @ResponseBody的作用其实是将java对象转为json格式的数据。
//   @ResponseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据。
    @ResponseBody
    @RequestMapping(value = "/getFilesName",produces="text/html;charset=utf-8")
    public String getFilesName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path=request.getServletContext().getRealPath("/")+"files/files.json";
        String json=JSONFileUtils.readFile(path);
        return json;
    }

    @RequestMapping("/download")
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
