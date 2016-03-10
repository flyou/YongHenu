package com.flyou.simplehenu.utils.UrlUtils;



import com.flyou.simplehenu.domain.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 项目名称：HenuNews
 *
 * 类名称：NewsUrlPaster
 *
 * 类描述：
 *
 * 创建人：flyou
 *
 * 创建时间：2015-4-19 下午11:51:39
 *
 * 修改备注：
 *
 * 版本：@version ============================================================
 */
public class NewsUrlPaster {
  public static final String TAG = "NewsUrlPaster";
  private static List<News> list;
  private static News news;

  public static List<News> getAllNews(String url) {
    list = new ArrayList<News>();
    Document doc;
    try {
      doc = Jsoup.connect(url).timeout(7000).get();
      Element content = doc.getElementsByClass("news").first();
      Elements links = content.getElementsByTag("a");
      if (content.select("a").size() > 0) {
        for (final Element link : links) {

        //获得绝对路径
        String newsUrl = link.attr("abs:href");
        String newsTitle = link.text();
        news = new News(newsTitle, newsUrl);
        list.add(news);
    }
        }
    } catch (Exception e) {
      return null;
    }

    return list;
  }

}
