package com.duing.handler;

import com.duing.bean.DataBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataHandler {

    public static String testStr = "{\"name\":\"寒江雪真皮\"}";

    public static void main(String[] args) throws Exception{
        getData();
    }



    public static List<DataBean> getData() throws Exception {
//        Gson gson = new Gson();
////        Gson gson1 = new GsonBuilder().create();
//        Map map = gson.fromJson(testStr,Map.class);
//        System.out.println(map);


        // 读取文件中的文本内容   然后再转化为java对象
//        File file = new File("tmp.txt");

        FileReader fr = new FileReader("tmp.txt");
        char[] cBuf = new char[1024];
        int cRead = 0;
        StringBuilder builder = new StringBuilder();
        while ((cRead = fr.read(cBuf)) > 0) {
            builder.append(new String(cBuf, 0, cRead));
        }

        fr.close();

//        System.out.println(builder.toString());
        Gson gson = new Gson();
        Map map = gson.fromJson(builder.toString(), Map.class);
        System.out.println(map);

        ArrayList areaList = (ArrayList) map.get("areaTree");
        Map dataMap = (Map) areaList.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");

        // 遍历然后转化
        List<DataBean> result = new ArrayList<>();

        for (int i = 0; i < childrenList.size(); i++) {
            Map tmp = (Map) childrenList.get(i);
            String name = (String)tmp.get("name");

            Map totalMap = (Map) tmp.get("total");
            double nowConfirm = (Double)totalMap.get("nowConfirm");
            double confirm = (Double)totalMap.get("confirm");
            double heal = (Double)totalMap.get("heal");
            double dead = (Double)totalMap.get("dead");

            DataBean dataBean = new DataBean(name,(int)nowConfirm,(int)confirm,
                    (int)heal,(int)dead);

            result.add(dataBean);
        }

        System.out.println(result);

        return result;
    }
}
