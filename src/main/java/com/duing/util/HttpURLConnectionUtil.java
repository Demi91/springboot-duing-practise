package com.duing.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// http请求工具类  java原生的使用方式
//   httpclient做了进一步封装 ->  应用更广
public class HttpURLConnectionUtil {


    public static String doGet(String urlStr) {
        HttpURLConnection conn = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlStr);
            // 通过url打开一个远程连接  强转类型
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 连接时间和读取时间
            //  连接时间： 发送请求端 连接到  url目标地址端的时间
            //            受到距离长短和网络速度的影响
            //  读取时间： 指连接成功后  获取数据的时间
            //            受到数据量和服务器处理速度的影响
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(60000);

            // 设定请求头参数的方式：如指定接收json数据   服务端的key值为content-type
//            conn.setRequestProperty("Accept", "application/json");

            // 发送请求
            conn.connect();

            if (conn.getResponseCode() != 200) {
                // TODO 此处应该增加异常处理
                return "error code";
            }

            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            // 逐行读取  不为空就继续
            while ((line = br.readLine()) != null) {
                result.append(line);
                System.out.print(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (is != null) is.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }


    public static void main(String[] args) {
        String str = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
        String str1 = "https://ncov.dxy.cn/ncovh5/view/pneumonia?scene=2&from=singlemessage&isappinstalled=0";
        String result = doGet(str1);
        System.out.println(result);
    }
}
