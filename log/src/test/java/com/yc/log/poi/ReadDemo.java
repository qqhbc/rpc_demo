package com.yc.log.poi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

public class ReadDemo {
    @Test
    public void fun() throws Exception{
        String path = ResourceUtils.getURL("classpath:").getPath();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path+File.separator+"static"+File.separator+"user.xlsx")));
        StringBuffer sb = new StringBuffer();
        String s = null;
        while((s = in.readLine()) != null){
            sb.append(s+"\n");
        }
        in.close();
        System.out.println(sb.toString());
    }
    @Test
    public void fun1(){
        String s = "hello.txt";
        String substring = s.substring(s.lastIndexOf("."));
        System.out.println(substring);
        System.out.println(s.lastIndexOf("."));
    }
}
