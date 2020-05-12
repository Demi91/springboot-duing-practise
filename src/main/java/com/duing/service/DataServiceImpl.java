package com.duing.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duing.bean.DataBean;
import com.duing.handler.DataHandler;
import com.duing.handler.JsoupHandler;
import com.duing.mapper.DataMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl extends ServiceImpl<DataMapper,DataBean>
        implements DataService {

//    @Override
//    public List<DataBean> list() {
//        List<DataBean> result = null;
//
//        try {
//            result = DataHandler.getData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//
//    @Override
//    public List<DataBean> listById(int id) {
//        if (id == 2) {
//            return JsoupHandler.getData();
//        }
//        return list();
//    }
}
