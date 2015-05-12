package com.yanbober.android_mvp_demo.model;

import com.yanbober.android_mvp_demo.bean.InfoBean;

/**
 * Author       : yanbo
 * Date         : 2015-05-12
 * Time         : 08:35
 * Description  :
 */
public class InfoModelImpl implements IInfoModel {
    //模拟存储数据
    private InfoBean infoBean = new InfoBean();

    @Override
    public InfoBean getInfo() {
        //模拟存储数据，真实有很多操作
        return infoBean;
    }

    @Override
    public void setInfo(InfoBean info) {
        //模拟存储数据，真实有很多操作
        infoBean = info;
    }
}
