package com.sj.work.tool;

import com.sj.work.tool.common.JobDay;
import com.sj.work.tool.common.Worker;
import com.sj.work.tool.common.WorkerTime;
import com.sj.work.tool.util.BeanUtil;
import com.sj.work.tool.util.ExcelUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaosiji on 2017/8/1.
 */
public class Test {

    static Map<String,JobDay> map=new HashMap<String, JobDay>();

    public static Map<String,JobDay> accountDay(){

        String workTimeStr= ExcelUtil.readExcel("F://file/1.xlsx", 7);

        String workStr= ExcelUtil.readExcel("F://file/2.xlsx",12);

        //正常
        List<Worker> workerList= BeanUtil.convertWorker(workStr);

        //异常
        List<WorkerTime> workerTimeList=BeanUtil.convertWorkerTime(workTimeStr);

        //日期序列
        List<String> dateList=dateList();

        //人名序列
        List<String> nameList=nameList();

        //员工考勤号+时间作为主键
        for (int j = 0; j <nameList.size() ; j++) {
            //遍历日期
            for (int i = 0; i <dateList.size() ; i++) {
                JobDay jobTime=new JobDay();

                String key=nameList.get(j)+"-"+dateList.get(i);

                jobTime.setNo(nameList.get(j));
                jobTime.setDay(dateList.get(i));

                jobTime.setStartTime("0");
                jobTime.setEndTime("0");
                jobTime.setName(nameList.get(j));

                jobTime.setFlag("无");
                map.put(key,jobTime);
            }
        }

        //正常
        for (int i = 0; i <workerList.size() ; i++) {
            Worker w=workerList.get(i);
            String key=w.getNo()+"-"+w.getDay();
            JobDay jobTime= map.get(key);

            if(map.get(key)!=null){
                jobTime.setStartTime(w.getDay() + " " + w.getStartTime());
                jobTime.setEndTime(w.getDay()+" "+w.getEndTime());
                jobTime.setName(w.getName());
            }

        }

        //异常
        for (int i = 0; i <workerTimeList.size() ; i++) {
            WorkerTime w=workerTimeList.get(i);
            String key=w.getNo()+"-"+w.getTime().split(" ")[0];
            JobDay jobTime= map.get(key);

            if(map.get(key)!=null){

                if(jobTime.getStartTime().equals("0")){
                    jobTime.setStartTime(w.getTime());
                    jobTime.setName(w.getName());
                }
                if(jobTime.getEndTime().equals("0")){
                    jobTime.setEndTime(w.getTime());
                    jobTime.setName(w.getName());

                }

            }

        }

        for (Map.Entry<String, JobDay> entry : map.entrySet()) {
            //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue().toString());
            System.out.println(entry.getValue().toString());

        }


        return  map;
    }



    private static List<String>   nameList(){
        List<String> datelist=new ArrayList<String>();
        datelist.add("29");
        datelist.add("31");
        return datelist;
    }

    private static  List<String>  dateList(){
        List<String> datelist=new ArrayList<String>();
        datelist.add("2017/7/1");
        datelist.add("2017/7/2");
        datelist.add("2017/7/3");
        datelist.add("2017/7/4");
        datelist.add("2017/7/5");
        datelist.add("2017/7/6");
        datelist.add("2017/7/7");
        datelist.add("2017/7/8");
        datelist.add("2017/7/9");
        datelist.add("2017/7/10");
        datelist.add("2017/7/11");
        datelist.add("2017/7/12");
        datelist.add("2017/7/13");
        datelist.add("2017/7/14");
        datelist.add("2017/7/15");
        datelist.add("2017/7/16");
        datelist.add("2017/7/17");
        datelist.add("2017/7/18");
        datelist.add("2017/7/19");
        datelist.add("2017/7/20");
        datelist.add("2017/7/21");
        datelist.add("2017/7/22");
        datelist.add("2017/7/23");
        datelist.add("2017/7/24");
        datelist.add("2017/7/25");
        datelist.add("2017/7/26");
        datelist.add("2017/7/27");
        datelist.add("2017/7/28");
        datelist.add("2017/7/29");
        datelist.add("2017/7/30");
        datelist.add("2017/7/31");
        return datelist;
    }

    public static void main(String[] args) {
        accountDay();
    }
}
