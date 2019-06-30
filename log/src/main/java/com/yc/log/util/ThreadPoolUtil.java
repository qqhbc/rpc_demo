package com.yc.log.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolUtil {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);
    
    public static ThreadPoolExecutor executor =  new ThreadPoolExecutor(100, 100, 600, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadFactory() {
            
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "log-thread");
            }
        });
    
    public static void  Executor(Runnable r){
        try{
        executor.execute(r);
        }catch(Exception e){
            logger.error("the thread runtime fail {}",e);
        }
    }
}
