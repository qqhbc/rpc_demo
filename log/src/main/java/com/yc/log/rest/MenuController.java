package com.yc.log.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.yc.log.model.po.Menu;
import com.yc.log.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public List<Menu> getListMenu() {
        return menuService.getListMenu();
    }

    @GetMapping("/list/leaf")
    public List<Menu> getListMenuLeaf() throws Exception {
        List<Menu> list = menuService.getListMenuLeaf();
        Socket socket = new Socket("127.0.0.1", 8083);
        OutputStream out = socket.getOutputStream();
        out.write(JSON.toJSONString(list).getBytes("UTF-8"));
        out.close();
        socket.close();
        return list;
    }

    @PutMapping("/edit")
    public void editNameById(@RequestBody Menu menu) {
        menuService.editNameById(menu);
    }

    @PostMapping("/add")
    public void addMenu(@RequestBody Menu menu) {
        menuService.addMenu(menu);
    }

    @DeleteMapping("/delete")
    public void deleteMenu(@RequestParam Integer id) {
        menuService.deleteMenu(id);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam String name,@RequestParam MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        String filename = file.getOriginalFilename();
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
           // String path = System.getProperty("user.dir"); //得到项目路径 D:\project\log
            file.transferTo(new File(path+File.separator+"static"+File.separator+filename));
            logger.info("upload file success {}",filename);
        } catch (IllegalStateException | IOException e) {
            logger.error("creat file fail {} {}",filename,e.getMessage());
        }
        return "success";
    }

    @PostMapping("/multipart_upload")
    public String multipartUpload(HttpServletRequest request) throws Exception{
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        String path = ResourceUtils.getURL("classpath:").getPath();
        files.forEach(b ->{
                try {
                    b.transferTo(new File(path+File.separator+"static"+File.separator+b.getOriginalFilename()));
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
        });
        return "success";
    }
    
    @PostMapping("/download")
    public String download(HttpServletResponse response){
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("D:/update/test.txt"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        //response.setContentType("application/force-download");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + "test.txt");
        OutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte [] bytes = new byte[1024];
        try {
            while(in.read(bytes) != -1){
                try {
                    out.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
