package com.gaorch.demo02.entity;

import lombok.Data;

@Data
public class Blog {
    private Integer id;
    private String username;
    private String time;
    private String title;
    private String content;
}
