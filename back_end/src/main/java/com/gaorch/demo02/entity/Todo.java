package com.gaorch.demo02.entity;

public class Todo {

    private Integer id;
    private String topic;
    private String details;
    private String deadline;
    private boolean status;

    public Integer getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getDetails() {
        return details;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", details='" + details + '\'' +
                ", deadline='" + deadline + '\'' +
                ", status=" + status +
                '}';
    }
}
