package com.jyh.com.jyh.Base;

import android.app.Application;

/**
 * Created by vvguoliang on 2017/6/23.
 *
 * Application 开始打开启动页面
 */

public class LoanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 设置该CrashHandler为程序的默认处理器
        UnCeHandler catchExcep = new UnCeHandler( this );
        Thread.setDefaultUncaughtExceptionHandler( catchExcep );
    }
}
