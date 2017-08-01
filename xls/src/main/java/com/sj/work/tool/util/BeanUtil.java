package com.sj.work.tool.util;

import com.sj.work.tool.common.Worker;
import com.sj.work.tool.common.WorkerTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaosiji on 2017/8/1.
 */
public class BeanUtil {



    public static List<WorkerTime> convertWorkerTime(String str) {

        String[] strs=str.split("\\$");

        List<WorkerTime> workerlist=new ArrayList<WorkerTime>();

        for (int i = 0; i < strs.length; i++) {
            WorkerTime worker=new WorkerTime();

            worker.setNo(strs[i].split("\\^")[0]);
            worker.setDescno(strs[i].split("\\^")[1]);
            worker.setName(strs[i].split("\\^")[2]);
            worker.setTime(strs[i].split("\\^")[3]);
            worker.setWorkStatus(strs[i].split("\\^")[4]);
            worker.setUpdateStatus(strs[i].split("\\^")[5]);
            worker.setErrorStatus(strs[i].split("\\^")[6]);
            workerlist.add(worker);
        }
        return workerlist;
    }



    public static List<Worker> convertWorker(String str) {

        String[] strs=str.split("\\$");

        List<Worker> workerlist=new ArrayList<Worker>();

        for (int i = 0; i < strs.length; i++) {
            Worker worker=new Worker();
            worker.setNo(strs[i].split("\\^")[0]);
            worker.setDescno(strs[i].split("\\^")[1]);
            worker.setName(strs[i].split("\\^")[2]);
            worker.setStartTime(strs[i].split("\\^")[3]);
            worker.setEndTime(strs[i].split("\\^")[4]);
            worker.setDepartment(strs[i].split("\\^")[5]);
            worker.setErrorStatus(strs[i].split("\\^")[6]);
            worker.setStatus(strs[i].split("\\^")[7]);
            worker.setBeforeStatus(strs[i].split("\\^")[8]);
            worker.setLongTime(strs[i].split("\\^")[9]);
            worker.setHaveTime(strs[i].split("\\^")[10]);
            worker.setDay(strs[i].split("\\^")[11]);
            workerlist.add(worker);
        }
        return workerlist;
    }
}
