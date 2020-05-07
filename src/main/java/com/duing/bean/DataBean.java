package com.duing.bean;

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
