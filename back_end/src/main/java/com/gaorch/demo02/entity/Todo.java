package com.gaorch.demo02.entity;

import lombok.Data;

@Data
public class Todo {
    private Integer id;
    private Integer userId;
    private String topic;
    private String details;
    private String deadline;
    private boolean status;
}
