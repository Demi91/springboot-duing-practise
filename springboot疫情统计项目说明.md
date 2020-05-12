## Day 1

#### 【课程目标】

1） spring boot的常见应用
2） 业务功能的开发思路
3） 爬虫的底层原理
4） 对技术的应用有一定思考



#### 【项目的诞生】

项目成员：项目经理 PM
产品经理 PD、 UI设计师 UED、 前端工程师 FE、后端工程师 RD、
测试工程师 QA、运维工程师 OP



#### 【功能的诞生】

产品经理 ->  需求评审会  ->  UI设计师（交互） ->  UI评审 ->  技术 ->  技术方案设计 ->  开发（1/3） ->  测试和修改



#### 【爬虫的基础知识】

（一） 搜索引擎

“搜索引擎”     是一种帮助用户搜索他们需要内容的计算机程序

本质：连接人与内容     

<img src="images/搜索引擎架构图.png" style="zoom: 67%;" />

存储数据的处理方式：

如：一篇文章 ->   语义分析 ->   关键字提取 ->   根据关键字及出现次数等 ->  倒排存储

关键字搜索：

【查询分析】  关键字相关的近义词、反义词、关联信息（ip、地址、用户信息）


（二）爬虫分类

通用型爬虫（搜索引擎使用）   

​        采集的数据是整个网站的数据，不论数据是何种形态、格式，使用通用的方法来存储和处理

垂直型爬虫（对特定内容的采集）

​        采集的数据是指定的，格式是指定的，使用个性化的方法来处理

<img src="images/通用爬虫框架图.png" style="zoom: 67%;" />

网站的首页作为种子， 爬虫去采集能够分解出多少不重复的子链接，及其数据。

采集/下载页面 ->  分解为数据本身（存储） 、新的链接（依次向下爬取，类似树的深度遍历）->  直到没有新链接代表采集完成 （链接使用队列来存储）  



（三）爬虫数据的分析

1、浏览器开发者工具

分析数据源
1） 腾讯新闻出品
 https://news.qq.com/zt2020/page/feiyan.htm#/?nojump=1 



工具的打开方式：F12 / Ctrl + Shift + I / 更多工具 - 开发者工具 /  右键 - 检查

选中Network - Preserve log（保存持续的日志）， 重新刷新页面，可以看到网页所有的请求连接和返回数据。



想拿到表格中   国内疫情的数据情况

<img src="images/image-20200507204651951.png" alt="image-20200507204651951" style="zoom:50%;" />



分析的方式：
通过搜索框，搜索想要获得数据的具体数值，如：944/758等等

分析后的请求地址：
https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5 

返回数据的格式是json



2）丁香医生出品
 https://ncov.dxy.cn/ncovh5/view/pneumonia?scene=2&clicktime=1579579384&enterid=1579579384&from=singlemessage&isappinstalled=0 

分析的方式同上

分析后的请求地址：

https://ncov.dxy.cn/ncovh5/view/pneumonia?scene=2&from=singlemessage&isappinstalled=0

返回数据的格式是html



2、postman（模拟http请求的工具）

验证分析出来的请求地址，在排除上下文环境后，是否依然能够拿到数据。

比如有的请求，依赖cookie、依赖动态的参数等等

![image-20200507205115519](images/image-20200507205115519.png)

3、爬虫破解问题

拿到数据是核心的一步。

公开数据只要是非恶意就允许采集的，非恶意是指模仿人的行为采集的行为，不会高并发或者恶意攻击。

隐私数据都是有强大的加密处理的，防爬虫的手段是安全领域内的一大问题。



## Day 2

#### 【解析数据】

##### (一)  认识JSON

JSON = JavaScript Object Notation   （JavaScript对象表示法）

本质上，是存储和交换文本信息的语法。是一种轻量级的文本数据格式。



java领域内解析json的工具：gson 、 fastjson 、jackson



JSON 和 Java实体类

| JSON        | JAVA实体类                    |
| ----------- | ----------------------------- |
| string      | java.lang.String              |
| number      | java.lang.Number (Double)     |
| true\|false | java.lang.Boolean             |
| null        | null                          |
| array       | java.util.List (ArrayList)    |
| object      | java.util.Map (LinkedTreeMap) |



##### (二) Gson

是google推出的，用来在json数据和java对象之间进行转换的类库。

```
Gson  gson = new Gson();
Gson  gson1 = new GsonBuilder().create();

// 将对象obj转化为json字符串
String jsonStr = gson.toJson(Obj);
// 将json字符串转化为java对象
T obj = gson.fromJson(jsonStr,class)
```



使用方式

1）引入gson依赖

```
<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.6</version>
        </dependency>
```

2）确认要转化的数据格式

```
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class DataBean {

    private String area;
    private int nowConfirm;
    private int confirm;
    private int heal;
    private int dead;
}

```

3）解析文本数据

```java
public class DataHandler {

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

```

##### 



## Day 3

##### (三)  将数据展示在页面中

1、编写service和controller

```
import com.duing.bean.DataBean;

import java.util.List;

public interface DataService {

    List<DataBean> list();
}

```



```
import com.duing.bean.DataBean;
import com.duing.handler.DataHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Override
    public List<DataBean> list() {
        List<DataBean> result = null;

        try {
            result = DataHandler.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

```



```
import com.duing.bean.DataBean;
import com.duing.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String list(Model model){
        List<DataBean> list = dataService.list();
        model.addAttribute("dataList",list);
        return "list";
    }
    
}

```



2、编写页面（注意已引入thymeleaf的maven依赖）

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>


<h2>国内疫情情况如下</h2>
<br>

<table>
    <thead>
    <tr>
        <th>地区</th>
        <th>现有确诊</th>
        <th>累计确诊</th>
        <th>治愈</th>
        <th>死亡</th>
    </tr>
    </thead>
    <tbody>
    <tr th:each="data:${dataList}">
        <td th:text="${data.area}">name</td>
        <td th:text="${data.nowConfirm}">nowConfirm</td>
        <td th:text="${data.confirm}">confirm</td>
        <td th:text="${data.heal}">heal</td>
        <td th:text="${data.dead}">dead</td>
    </tr>
    </tbody>
</table>

</body>
</html>
```



##### (四) 转为实时数据

涉及知识点：用java代码模拟http请求

1、复习get和post请求

​     分别在使用场景、参数传递方式、数据大小限制、安全性等方面的异同

2、HttpURLConnection

```
连接时间和读取时间
 连接时间： 发送请求端 连接到  url目标地址端的时间
           受到距离长短和网络速度的影响
 读取时间： 指连接成功后  获取数据的时间
           受到数据量和服务器处理速度的影响
```

1） 通过创建url打开远程链接 (HttpURLConnection)
2)    设置相关参数（超时时间和请求头等等）
3） 发送请求
4） 接收结果（使用InputStream和BufferedReader）

```
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
```



## Day 4

##### (五) 使用Jsoup解析html格式数据

1、Jsoup
      是html的解析器，可以解析html文本和直接解析URL地址。
      本质上是通过DOM、CSS以及类似jQuery的方法来取出和操作数据。

```

        Document document = Jsoup.parse(htmlStr);
        System.out.println(document);

        //通过标签名找到元素
        Elements elements = document.getElementsByTag("p");
        System.out.println(elements);

        document.getElementsById    //通过id找到元素
        Element element = document.select("a[href]");  
        //还支持使用正则表达式查找元素

```



2、提供不同数据源的切换查询

1）增加了controller方法

```
    @GetMapping("/list/{id}")
    public String listById(Model model, @PathVariable String id) {
        List<DataBean> list = dataService.listById(Integer.parseInt(id));
        model.addAttribute("dataList", list);
        return "list";
    }
```

@PathVariavle  将接收到的地址数据，映射到方法的参数中

2）完善service

```
    @Override
    public List<DataBean> listById(int id) {
        if (id == 2) {
            return JsoupHandler.getData();
        }
        return list();
    }
```

3)  处理数据的方法

```
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
```



4) 验证

分别访问 http://localhost:8080/list/1  和  http://localhost:8080/list/2
通过省份的名称来区分不同渠道的数据结果



##### (六) 增加数据存储逻辑

1、引入相关的依赖

```
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.0</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.2.0</version>
        </dependency>
```

2、配置数据库

```
spring.datasource.url=jdbc:mysql://localhost:3306/epidemic?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=123456
```

3、使用mybatis-plus进行增删改查的操作



4、初始化数据存储的逻辑

@PostConstruct
修饰的方法，在服务器加载Servlet时运行，而且只执行一次



@Scheduled

1） @Scheduled(fixedRate = 10000)   指定频率的执行任务   从方法执行开始就计时
        假设方法执行5s    那么第一次执行开始过了10s后，开始第二次执行

2） @Scheduled(fixedDelay = 10000)   指定间隔的执行任务    从方法执行完成开始计时
        假设方法执行5s    那么第一次执行完成过了10s后，开始第二次执行

3） cron表达式
        把六个位置用空格分隔，指代不同单位的时间，执行的规律
        秒、分钟、小时、日期、月份、星期、(年，可选)









































