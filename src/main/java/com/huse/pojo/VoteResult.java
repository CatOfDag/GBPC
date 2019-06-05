package com.huse.pojo;

import lombok.Data;

@Data
public class VoteResult {
    private int id;
    private String name;
    private String votealias;
    private double score;
    //德
    private int v1count;
    private int v2count;
    private int v3count;
    private int v4count;
    //能
    private int a1count;
    private int a2count;
    private int a3count;
    private int a4count;
    //勤
    private int d1count;
    private int d2count;
    private int d3count;
    private int d4count;
    //绩
    private int f1count;
    private int f2count;
    private int f3count;
    private int f4count;
    //廉
    private int h1count;
    private int h2count;
    private int h3count;
    private int h4count;

}
