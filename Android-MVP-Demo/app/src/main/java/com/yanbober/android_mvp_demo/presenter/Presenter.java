package com.yanbober.android_mvp_demo.presenter;

import com.yanbober.android_mvp_demo.activity.IInfoView;
import com.yanbober.android_mvp_demo.bean.InfoBean;
import com.yanbober.android_mvp_demo.model.IInfoModel;
import com.yanbober.android_mvp_demo.model.InfoModelImpl;

/**
 * Author       : yanbo
 * Date         : 2015-05-12
 * Time         : 08:34
 * Description  :
 */
public class Presenter {
    private IInfoModel infoModel;
    private IInfoView infoView;

    public Presenter(IInfoView infoView) {
        this.infoView = infoView;

        infoModel = new InfoModelImpl();
    }
    //供UI调运
    public void saveInfo(InfoBean bean) {
        infoModel.setInfo(bean);
    }
    //供UI调运
    public void getInfo() {
        //通过调用IInfoView的方法来更新显示，设计模式运用
        //类似回调监听处理
        infoView.setInfo(infoModel.getInfo());
    }
}
