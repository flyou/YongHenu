package com.flyou.simplehenu.utils.UrlUtils.departments;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * ============================================================
 * ��Ŀ���ƣ�HenuCenter
 * <p/>
 * �����ƣ�Newsitem
 * <p/>
 * ��������
 * <p/>
 * �����ˣ�flyou
 * <p/>
 * ����ʱ�䣺2015-4-20 ����9:55:24
 * <p/>
 * �޸ı�ע��
 * <p/>
 * �汾��@version
 * ============================================================
 */
public class LifeNewsitemShow {
    private static Document doc;
    private static Element divAllItemElement;
    public static final String TAG = "Newsitem";

    public static String getNewsbody(String itemUrl) {


        try {
            doc = Jsoup.connect(itemUrl).timeout(7000).get();
            divAllItemElement = doc.getElementById("arcmain");

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
