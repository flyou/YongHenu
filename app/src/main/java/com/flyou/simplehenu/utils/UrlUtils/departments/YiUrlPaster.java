package com.flyou.simplehenu.utils.UrlUtils.departments;

import com.flyou.simplehenu.domain.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class YiUrlPaster {
  public static final String TAG = "YiUrlPaster";
  public static final String ID = "newslist";
 
  private static List<News> list;
  private static News news;

  public static List<News> GetShow(String path) {
    list = new ArrayList<News>();
    Document doc;
    try {
      doc = Jsoup.connect(path).timeout(7000).get();
      Element content = doc.getElementById(ID).getElementsByTag("ul").first();
      Elements links = content.getElementsByTag("a");
      if (content.select("a").size() > 0) {
        for (Element link : links) {

          // ��þ���·��
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
