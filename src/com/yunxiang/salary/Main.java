package com.yunxiang.salary;

import java.util.logging.Logger;

/**
 * Created by wangqingxiang on 2017/4/19.
 */
public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        logger.info("开始执行");
        new YFileChooser();
    }
}
