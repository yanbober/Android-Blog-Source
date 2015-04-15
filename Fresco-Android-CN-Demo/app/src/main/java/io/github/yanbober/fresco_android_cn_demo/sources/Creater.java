package io.github.yanbober.fresco_android_cn_demo.sources;

import java.util.List;

/**
 * Author       : yanbo
 * Date         : 2015-04-10
 * Time         : 16:07
 * Description  : 资源获取器
 */
public class Creater {
    /**
    * 图片来源
    */
    public interface Type{
        public static final int NET = 1;
        public static final int LOCAL = 2;
    }

    private SrcCreaterImpl mSrcCreater;

    /**
    * 初始化类型
    */
    public void init(int type) {
        switch (type) {
            case Type.LOCAL:
                mSrcCreater = new LocalSrcCreater();
                break;
            case Type.NET:
                mSrcCreater = new NetSrcCreater();
                break;
            default:
                throw new IllegalArgumentException("非法类型参数!");
        }
    }

    public String getPic() {
        if (mSrcCreater == null) {
            throw new IllegalArgumentException("请先初始化类型参数!");
        }

        return mSrcCreater.getPic();
    }

    public List<String> getPicList() {
        if (mSrcCreater == null) {
            throw new IllegalArgumentException("请先初始化类型参数!");
        }

        return mSrcCreater.getPicList();
    }

    public String getGif() {
        if (mSrcCreater == null) {
            throw new IllegalArgumentException("请先初始化类型参数!");
        }

        return mSrcCreater.getGif();
    }

    public List<String> getGifList() {
        if (mSrcCreater == null) {
            throw new IllegalArgumentException("请先初始化类型参数!");
        }

        return mSrcCreater.getGifList();
    }
}
