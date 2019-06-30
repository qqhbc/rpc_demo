package com.yc.log.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yc.log.annotation.DataSourceRouting;
import com.yc.log.annotation.SystemLogService;
import com.yc.log.mapper.blog.TUserMapper;
import com.yc.log.mapper.test.UserMapper;
import com.yc.log.model.po.TUser;
import com.yc.log.model.po.User;
import com.yc.log.model.vo.UserVO;
import com.yc.log.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private int i = 1;
    @Autowired
    private TUserMapper userMapper;
    @Autowired
    private UserMapper mapper;
    
    @Override
    @SystemLogService(description = "获取用户信息",appName = "用户管理")
    public List<UserVO> getUserList() {
        List<TUser> userList = userMapper.getUserList();
        List<UserVO> voList = new ArrayList<>(); 
        userList.forEach((b) -> voList.add(UserVO.valueOf(b)));
        return voList;
    }

    @Override
    @SystemLogService(description = "通过账号获取用户信息",appName = "用户管理")
    public UserVO getUserById(String account) {
        return userMapper.getUserById(account);
    }

    @Override
    @SystemLogService(description = "多源数据",appName = "test")
    public User getUserById(Integer id) {
        return mapper.getUserById(id);
    }

    @Override
    public String export(HttpServletResponse response) {
        List<TUser> list = userMapper.getUserList();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("user");
        XSSFCellStyle style = wb.createCellStyle();
        style.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        XSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HorizontalAlignment.CENTER);//水平居中  
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("account");
        row.createCell(2).setCellValue("name");
        row.createCell(3).setCellValue("password");
        row.createCell(4).setCellValue("phone");
        row.createCell(5).setCellValue("address");
        row.createCell(6).setCellValue("email");
        row.createCell(7).setCellValue("create_date");
        for(int j=0;j<row.getLastCellNum();j++){
            row.getCell(j).setCellStyle(style2);
        }
        list.stream().forEach(b -> {
            XSSFRow createRow = sheet.createRow(i++);
            createRow.createCell(0).setCellValue(b.getId());
            createRow.createCell(1).setCellValue(b.getAccount());
            createRow.createCell(2).setCellValue(b.getName());
            createRow.createCell(3).setCellValue(b.getPassword());
            createRow.createCell(4).setCellValue(b.getPhone());
            createRow.createCell(5).setCellValue(b.getAddress());
            createRow.createCell(6).setCellValue(b.getEmail());
            XSSFCell cell = createRow.createCell(7);
            cell.setCellStyle(style);
            cell.setCellValue(b.getCreate_date());
        });
        FileOutputStream out = null;
        OutputStream stream = null;
        try {
            stream = response.getOutputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            out = new FileOutputStream(ResourceUtils.getURL("classpath:").getPath()+File.separator+"static"+File.separator+"user.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            wb.write(out);
            wb.write(stream);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + "user.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wb.close();
            out.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "success";
    }

    @Override
    public String importExcel(MultipartFile file) throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath();
        String filename = file.getOriginalFilename();
        String string = filename.substring(filename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString()+string;
        file.transferTo(new File(path+File.separator+"static"+File.separator+fileName));
        FileInputStream in = new FileInputStream(path+File.separator+"static"+File.separator+fileName);
        XSSFWorkbook wb = new XSSFWorkbook(in);
        XSSFSheet sheet = wb.getSheetAt(0);
            for(int j=1;j<sheet.getLastRowNum()+1;j++){
                TUser b = new TUser();
                XSSFRow row = sheet.getRow(j);
                b.setId((long) row.getCell(0).getNumericCellValue());
                b.setAccount(row.getCell(1).getStringCellValue());
                b.setName(row.getCell(2).getStringCellValue());
                b.setPassword(row.getCell(3).getStringCellValue());
                b.setPhone(row.getCell(4).getStringCellValue());
                b.setAddress(row.getCell(5).getStringCellValue());  
                b.setEmail(row.getCell(6).getStringCellValue());
                b.setCreate_date(row.getCell(7).getDateCellValue());
                userMapper.insert(b);
            }
        wb.close();
        in.close();
        return "success";
    }

    @Override
    public int update() {
        TUser user = new TUser();
        user.setId(10000001l);
        user.setName("hty");
        return userMapper.updateByPrimaryKeySelective(user);
    }
    public TUser getTUserById(Integer id){
//        ThreadPoolUtil.Executor(new Runnable() {
//            
//            @Override
//            public void run() {
//                userMapper.delete();
//            }
//        });
//        
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        List<Integer> list = new ArrayList<>(2);
        list.add(2);
        list.add(3);
        userMapper.delete();
        return userMapper.selectById(id);
    }
    
    public int insert(){
        TUser user = new TUser();
        user.setId(10000001l);
        user.setName("hty");
        user.setAccount("yyxx");
        return userMapper.insertSelective(user);
    }

    @Override
    @DataSourceRouting(description = "jiaohu_db")
    public List<User> getUser() {
        List<User> user = mapper.getUser();
        return user;
    }
}
