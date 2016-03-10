package com.flyou.simplehenu.utils.UrlUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * ============================================================
 * 项目名称：HenuCenter
 * <p/>
 * 类名称：Newsitem
 * <p/>
 * 类描述：
 * <p/>
 * 创建人：flyou
 * <p/>
 * 创建时间：2015-4-20 上午9:55:24
 * <p/>
 * 修改备注：
 * <p/>
 * 版本：@version
 * ============================================================
 */
public class NewsItemPaster {
    private static Document doc;
    private static Element divAllItemElement;
    public static final String TAG = "Newsitem";

    public String getNewsbody(String itemUrl) {


        try {
            doc = Jsoup.connect(itemUrl).timeout(7000).get();
            divAllItemElement = doc.getElementById("articleBody");


        } catch (Exception e) {

            e.printStackTrace();
//            TODO 网络解析失败，显示失败页面

            return "";
        }
        if (null==divAllItemElement) {
            return "";
        } else {
            return divAllItemElement.toString();
        }
    }

}
