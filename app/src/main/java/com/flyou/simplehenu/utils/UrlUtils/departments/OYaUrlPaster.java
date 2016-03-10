package com.flyou.simplehenu.utils.UrlUtils.departments;

import com.flyou.simplehenu.domain.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class OYaUrlPaster {
  public static final String TAG = "JkyUrlPaster";
  public static final String CLASS = "list_main_content";
 
  private static List<News> list;
  private static News news;

  public static List<News> GetShow(String path) {
    list = new ArrayList<News>();
    Document doc;
    try {
      doc = Jsoup.connect(path).timeout(7000).get();
      Element content = doc.getElementsByClass(CLASS).first();
      Elements links = content.getElementsByTag("a");
      if (content.select("a").size() > 0) {
        for (Element link : links) {
      
          //��þ���·��
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
