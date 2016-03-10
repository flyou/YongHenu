package com.flyou.simplehenu.utils.UrlUtils.departments;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class LiShiNewsitemShow {
  private static Document doc;
  private static Element divAllItemElement;
  public static final String TAG = "Newsitem";
public static String getNewsbody(String itemUrl){

  
    try {
      doc=Jsoup.connect(itemUrl).timeout(7000).get();
      divAllItemElement=doc.getElementsByClass("article_body").first();
    } catch (Exception e) {
    
      e.printStackTrace();
      return "";
    }
    if (null==divAllItemElement) {
        return "";
    } else {
        return divAllItemElement.toString();
    }
  }
  

}
