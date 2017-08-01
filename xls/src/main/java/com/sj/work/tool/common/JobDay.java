package com.sj.work.tool.common;

/**
 * Created by zhaosiji on 2017/8/1.
 */
public class JobDay {

    private String no;
    private String name;
    private String day;
    private String startTime;
    private String endTime;
    private String flag;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String toString() {
        return this.no+"-"+this.name+"-"+this.day+"-"+this.getStartTime()+"-"+this.getEndTime()+"-"+this.flag;
    }
}
