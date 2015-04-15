package io.github.yanbober.fresco_android_cn_demo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Author       : yanbo
 * Date         : 2015-04-10
 * Time         : 15:35
 * Description  :
 */
public class FrescoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Fresco.initialize(getApplicationContext());
    }
}
