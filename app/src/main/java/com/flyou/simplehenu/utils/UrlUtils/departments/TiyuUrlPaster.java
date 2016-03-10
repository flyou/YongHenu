package com.flyou.simplehenu.utils.UrlUtils.departments;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**  ============================================================  
 * ��Ŀ���ƣ�HenuCenter   
 * 
 * �����ƣ�JkyUrlPaster   
 * 
 * ��������   
 * 
 * �����ˣ�flyou  
 * 
 * ����ʱ�䣺2015-4-26 ����9:20:12  
 *  
 * �޸ı�ע��   
 * 
 * �汾��@version    
 *   ============================================================ 
 */
public class TiyuUrlPaster {
  public static final String TAG = "JkyUrlPaster";
  public static final String PATH = "JkyUrlPaster";
  public static final String ID = "form1";
  private static Document doc;
  private static Element content;


  public static String GetShow() {
    try {
      doc = Jsoup.connect(PATH).timeout(7000).get();
      content = doc.getElementById(ID);

    } catch (Exception e) {

      e.printStackTrace();
      return "";
    }
    return content.toString();
  }

}
