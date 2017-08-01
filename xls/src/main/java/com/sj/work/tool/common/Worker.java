package com.sj.work.tool.common;

import com.sj.work.tool.util.ExcelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaosiji on 2017/8/1.
 */
public class Worker {

    private String no;
    private String descno;
    private String name;
    private String startTime;
    private String endTime;
    private String department;
    private String errorStatus;
    private String status;
    private String beforeStatus;
    private String longTime;
    private String haveTime;
    private String day;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDescno() {
        return descno;
    }

    public void setDescno(String descno) {
        this.descno = descno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(String beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public String getLongTime() {
        return longTime;
    }

    public void setLongTime(String longTime) {
        this.longTime = longTime;
    }

    public String getHaveTime() {
        return haveTime;
    }

    public void setHaveTime(String haveTime) {
        this.haveTime = haveTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }



    public static void main(String[] args) {
       String str= ExcelUtil.readExcel("F://file/2.xlsx",12);
        Worker workerTime=new Worker();
    }

}
