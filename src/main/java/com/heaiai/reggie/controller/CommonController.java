package com.heaiai.reggie.controller;

import com.heaiai.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @description文件上传下载通用类
 * @author: Heaiai
 * @create: 2023-08-31 22:10:55
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;
    /***
     * @Description: 文件上传
     * 这里的参数名file是要跟前端进行匹配的
     * @Author:Heaiai
     * @Create:2023/8/31 22:13
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        log.info(file.toString());
        //原始文件名称
        String originalFilename  =file.getOriginalFilename();
        //获取原始文件
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID重新生成文件名，防止文件名重复造成文件覆盖
        String fileName = UUID.randomUUID().toString();
        // 使用随即生成的文件名+原始文件后缀
        fileName = fileName+suffix;
        //创建一个目录文件
        File dir = new File(basePath);
        //判断当前目录是否存在，如果不存在则进行新创建一个
        if(!dir.exists()){
            dir.mkdirs();
        }
        try {
            //将临时文件转存到指定位置
            file.transferTo(new File(basePath+ fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(fileName);
    }
    /***
     * @Description:文件下载
     * @Author:Heaiai
     * @Create:2023/9/2 21:32
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath+name));
            //输出流，通过输出流将文件写会浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            //设置文件流问图片格式
            response.setContentType("image/jpeg");
            int len = 0;
            //这表示每次读取1KB
            byte[] bytes = new byte[1024];
            //每次循环读bytes个字符，只要没有读完，就会一直读
            int i = 0;
            while ((len = fileInputStream.read(bytes))!= -1){
                i++;
                //将读到的文件进行写，从0个开始写，读多少写多少
                outputStream.write(bytes,0,len);
                //流刷新
                outputStream.flush();
            }
            System.out.println("一共读取了"+i+"遍数据");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
