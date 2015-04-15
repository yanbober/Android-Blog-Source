package io.github.yanbober.fresco_android_cn_demo.sources;

import java.util.List;

/**
 * Author       : yanbo
 * Date         : 2015-04-10
 * Time         : 15:52
 * Description  : 资源生成器
 */
public interface SrcCreaterImpl {
    //获取一张图片
    public String getPic();
    //获取一组图片
    public List<String> getPicList();
    //获取一张Gif图片
    public String getGif();
    //获取一组Gif图片
    public List<String> getGifList();
}
