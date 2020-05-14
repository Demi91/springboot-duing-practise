package com.duing.controller;

import com.duing.bean.*;
import com.duing.handler.GraphHandler;
import com.duing.service.DataService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String list(Model model) {
        List<DataBean> list = dataService.list();
        model.addAttribute("dataList", list);
        return "list";
    }

//    @GetMapping("/list/{id}")
//    public String listById(Model model, @PathVariable String id) {
//        List<DataBean> list = dataService.listById(Integer.parseInt(id));
//        model.addAttribute("dataList", list);
//        return "list";
//    }


    @GetMapping("/graph")
    public String graph(Model model) {
        List<GraphBean> list = GraphHandler.getGraphData();
        //  进一步改造数据格式
        //  因为前端需要的数据是  x轴所有数据的数组和y轴所有数据的数组

        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> nowConfirmList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            GraphBean graphBean = list.get(i);
            dateList.add(graphBean.getDate());
            nowConfirmList.add(graphBean.getNowConfirm());
        }

        model.addAttribute("dateList", new Gson().toJson(dateList));
        model.addAttribute("nowConfirmList", new Gson().toJson(nowConfirmList));
        return "graph";
    }

    @GetMapping("/graphAdd")
    public String graphAdd(Model model) {
        List<GraphAddBean> list = GraphHandler.getGraphAddData();

        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> addConfirmList = new ArrayList<>();
        ArrayList<Integer> addSuspectList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            GraphAddBean graphAddBean = list.get(i);
            dateList.add(graphAddBean.getDate());
            addConfirmList.add(graphAddBean.getAddConfirm());
            addSuspectList.add(graphAddBean.getAddSuspect());
        }

        model.addAttribute("dateList", new Gson().toJson(dateList));
        model.addAttribute("addConfirmList", new Gson().toJson(addConfirmList));
        model.addAttribute("addSuspectList", new Gson().toJson(addSuspectList));
        return "graphAdd";
    }


    @GetMapping("/graphColumnar")
    public String graphColumnar(Model model) {
        List<GraphColumnarBean> list = GraphHandler.getGraphColumnarData();
        Collections.sort(list);

        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Integer> fromAbroadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            GraphColumnarBean bean = list.get(i);
            nameList.add(bean.getArea());
            fromAbroadList.add(bean.getFromAbroad());
        }

        model.addAttribute("nameList", new Gson().toJson(nameList));
        model.addAttribute("fromAbroadList", new Gson().toJson(fromAbroadList));
        return "graphColumnar";
    }


    @GetMapping("/graphPie")
    public String graphPie(Model model) {
        List<GraphPieBean> list = GraphHandler.getGraphPieData();
        model.addAttribute("list", new Gson().toJson(list));
        return "graphPie";
    }
}
