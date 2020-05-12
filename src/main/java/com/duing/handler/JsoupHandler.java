package com.duing.handler;


import com.duing.bean.DataBean;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Map;

public class JsoupHandler {


//    public static String htmlStr = "<html><head></head><body>" +
//            "<p>hello html</p></body></html>";

    public static void main(String[] args) {

//        Document document = Jsoup.parse(htmlStr);
//        System.out.println(document);

        // 通过标签名找到元素
//        Elements elements = document.getElementsByTag("p");
//        System.out.println(elements);

//        document.getElementsById    通过id找到元素
//        Element element = document.select("a[href]");  还支持使用正则表达式查找元素

    }


    public static String urlStr = "https://ncov.dxy.cn/ncovh5/view/pneumonia?" +
            "scene=2&from=singlemessage&isappinstalled=0";

    public static ArrayList<DataBean> getData() {

        ArrayList<DataBean> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(urlStr).get();
//            Elements scripts = doc.select("script");
            // 找到指定的标签数据
            Element oneScript = doc.getElementById("getAreaStat");

            String data = oneScript.data();
            // 字符串截取出json格式的数据
            String subData = data.substring(data.indexOf("["),
                    data.lastIndexOf("]") + 1);

//            System.out.println(subData);

            Gson gson = new Gson();
            ArrayList list = gson.fromJson(subData, ArrayList.class);

            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String name = (String) map.get("provinceName");
                double nowConfirm = (Double) map.get("currentConfirmedCount");
                double confirm = (Double) map.get("confirmedCount");
                double heal = (Double) map.get("curedCount");
                double dead = (Double) map.get("deadCount");

                DataBean dataBean = new DataBean(name, (int) nowConfirm, (int) confirm
                        , (int) heal, (int) dead);
                result.add(dataBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
