package com.yanbober.android_mvp_demo.activity;

import com.yanbober.android_mvp_demo.bean.InfoBean;

/**
 * Author       : yanbo
 * Date         : 2015-05-11
 * Time         : 17:30
 * Description  :
 */
public interface IInfoView {
    //给UI显示数据的方法
    void setInfo(InfoBean info);
    //从UI取数据的方法
    InfoBean getInfo();
}
