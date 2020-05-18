package com.duing.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraphPieBean implements Comparable<GraphPieBean> {

    private String name;
    private int value;

    @Override
    public int compareTo(GraphPieBean o) {
        return this.getValue() - o.getValue();
    }
}