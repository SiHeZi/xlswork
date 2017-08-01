package com.sj.work.tool.common;

import com.sj.work.tool.util.BeanUtil;
import com.sj.work.tool.util.ExcelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaosiji on 2017/8/1.
 */
public class WorkerTime {

    private String no;
    private String descno;
    private String name;
    private String time;
    private String workStatus;
    private String updateStatus;
    private String errorStatus;
    private String action;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }



    public static void main(String[] args) {
       String str= ExcelUtil.readExcel("F://file/1.xlsx", 7);
        List<WorkerTime> workerTimeList= BeanUtil.convertWorkerTime(str);

        for (int i = 0; i <workerTimeList.size() ; i++) {
            System.out.println(workerTimeList.get(i).getNo()+"-"+workerTimeList.get(i).getTime().split(" ")[0]);
        }

        System.out.println("2017/7/23 13:46".split(" ")[0]);
    }

}
