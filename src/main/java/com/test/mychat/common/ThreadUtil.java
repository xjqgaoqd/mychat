package com.test.mychat.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用于发送邮件
 */
public class ThreadUtil {
    /** 一个无界的线程池 */
    public static ExecutorService executorService = Executors.newCachedThreadPool();
}
