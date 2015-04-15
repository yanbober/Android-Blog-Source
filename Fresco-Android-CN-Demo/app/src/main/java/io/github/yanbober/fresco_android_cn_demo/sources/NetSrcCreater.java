package io.github.yanbober.fresco_android_cn_demo.sources;

import java.util.ArrayList;
import java.util.List;

/**
 * Author       : yanbo
 * Date         : 2015-04-10
 * Time         : 15:57
 * Description  : 网络图片生成器
 */
public class NetSrcCreater implements SrcCreaterImpl {
    @Override
    public String getPic() {
        return "http://t12.baidu.com/it/u=4095575894,102452705&fm=32&s=A98AA55F526172A6F6A058E50300A060&w=623&h=799&img.JPEG";
    }

    @Override
    public List<String> getPicList() {
        List<String> list = new ArrayList<>();

        list.add("http://img0.bdstatic.com/img/image/cb5016e0d29c198a7b0e7f3dabf7a1351410240928.jpg");
        list.add("http://img0.bdstatic.com/img/image/77a483d72a2d16264e65858374ab70151409117959.jpg");
        list.add("http://img0.bdstatic.com/img/image/44e206d007e8663c8161f386d2cbb9871409804255.jpg");
        list.add("http://img0.bdstatic.com/img/image/a4602ed3a2c98399cd48917370f00d1e1408098579.jpg");

        return list;
    }

    @Override
    public String getGif() {
        return "http://img0.bdstatic.com/img/image/shitu/feimg/uploading.gif";
    }

    @Override
    public List<String> getGifList() {
        List<String> list = new ArrayList<>();

        list.add("http://img4.duitang.com/uploads/item/201308/11/20130811200201_NVJNK.thumb.600_0.gif");
        list.add("http://img0.bdstatic.com/img/image/shitu/feimg/uploading.gif");
        list.add("http://ww1.sinaimg.cn/mw600/6345d84ejw1dvxp9dioykg.gif");
        list.add("http://speedtexting.net/img/imgPhone.gif");

        return list;
    }
}
