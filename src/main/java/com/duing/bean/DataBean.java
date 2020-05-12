package com.duing.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor
@NoArgsConstructor
@TableName("illness")
public class DataBean implements Serializable {

    private String area;
    private int nowConfirm;
    private int confirm;
    private int heal;
    private int dead;
}
