package com.yc.log.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class MD5Utils {
    @Test
    public void fun(){
        String hex = DigestUtils.md5Hex("456");
        System.out.println(hex);
    }
}
