package com.duing.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraphColumnarBean implements Comparable<GraphColumnarBean> {

    private String area;
    private int fromAbroad;

    @Override
    public int compareTo(GraphColumnarBean o) {
        return o.getFromAbroad() - this.getFromAbroad();
    }
}
