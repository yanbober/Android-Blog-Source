package com.yanbober.android_mvp_demo.model;

import com.yanbober.android_mvp_demo.bean.InfoBean;

/**
 * Author       : yanbo
 * Date         : 2015-05-11
 * Time         : 17:29
 * Description  :
 */
public interface IInfoModel {
    //从数据提供者获取数据方法
    InfoBean getInfo();
    //存入数据提供者方法
    void setInfo(InfoBean info);
}
