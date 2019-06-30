package com.yc.log.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.yc.log.model.TestModel;

public class POIDemo {
    
    @Test //创建workbook 和 sheet
    public void creatExcel() throws Exception{
        String string = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("test");
        workbook.createSheet();
        HSSFRow row = sheet.createRow(0); //创建行
        HSSFCell cell = row.createCell(0); //创建列
        cell.setCellValue("胡天耀");
        row.createCell(1).setCellValue(true);
        row.createCell(2).setCellValue(12.8);
        row.createCell(4).setCellValue(Calendar.DATE);
        row.createCell(5).setCellValue(string);
        
        workbook.createInformationProperties(); //创建文档信息
        DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation(); //摘要文档信息
        dsi.setCategory("人物信息");
        dsi.setManager("尹超");
        dsi.setCompany("嘉立创");
        SummaryInformation si = workbook.getSummaryInformation();
        si.setAuthor("苏福林");
        si.setCreateDateTime(new Date());
        si.setSubject("主体");
        si.setTitle("标题");
        si.setComments("备注");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
        HSSFCell cellT = row.createCell(3);
        cellT.setCellValue(12);
        cellT.setCellStyle(style);
        
        FileOutputStream out = new FileOutputStream("D:/update/simple.xls");
        workbook.write(out);
        workbook.close();
        out.close();
    }
    
    @Test
    public void readExcel() throws Exception{
        FileInputStream in = new FileInputStream("D:/update/simple.xls");
        HSSFWorkbook wk = new HSSFWorkbook(in);
        HSSFSheet sheet = wk.getSheetAt(0);
        int num1 = sheet.getLastRowNum()+1;
        List<TestModel> list = new ArrayList<>();
        for(int i=0;i<num1;i++){
            HSSFRow row = sheet.getRow(i);
            TestModel model = new TestModel();
            HSSFCell name = row.getCell(0);
            HSSFCell hasSuccess = row.getCell(1);
            HSSFCell account = row.getCell(2);
            HSSFCell price = row.getCell(3);
            HSSFCell num = row.getCell(4);
            HSSFCell date = row.getCell(5);
            model.setName(name.getStringCellValue());
            model.setHasSuccess(hasSuccess.getBooleanCellValue());
            model.setAccount(account.getNumericCellValue());
            model.setPrice(price.getNumericCellValue());
            model.setNum((int)num.getNumericCellValue());
            model.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").parse(date.getStringCellValue()));
            list.add(model);
            }
        System.out.println(list);
        wk.close();
    }
}
