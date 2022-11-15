package com.ngtechnology.estacionamento.handler.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;
    private String type;
    private String title;

    private String detail;

    public Problem(){

    }

    public Problem(Integer status, String type, String title,
                   String detail){
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Problem withBuilderStatus(Integer status){
        setStatus(status);
        return this;
    }

    public Problem withBuilderType(String type){
        setType(type);
        return this;
    }

    public Problem withBuilderTitle(String title){
        setTitle(title);
        return this;
    }

    public Problem withBuilderDetail(String detail){
        setDetail(detail);
        return this;
    }

}
