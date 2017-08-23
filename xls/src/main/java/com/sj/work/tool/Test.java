package com.sj.work.tool;

import com.sj.work.tool.common.JobDay;
import com.sj.work.tool.common.Worker;
import com.sj.work.tool.common.WorkerTime;
import com.sj.work.tool.util.BeanUtil;
import com.sj.work.tool.util.DataUtil;
import com.sj.work.tool.util.ExcelUtil;

import java.util.*;

/**
 * Created by zhaosiji on 2017/8/1.
 */
public class Test {

    static Map<String,JobDay> map=new LinkedHashMap<String, JobDay>();

    static String kongFlag="";
    static String status="有问题请处理";

    public static Map<String,JobDay> accountDay(){

        String workTimeStr= ExcelUtil.readExcel("F://file/1.xlsx", 7);

        String workStr= ExcelUtil.readExcel("F://file/2.xlsx", 12);
        System.out.println(workStr);
        //正常
        List<Worker> workerList= BeanUtil.convertWorker(workStr);

        //异常
        List<WorkerTime> workerTimeList=BeanUtil.convertWorkerTime(workTimeStr);

        //日期序列
        List<String> dateList=dateList();

        //初始化键值对
        Map<String,String> getNameMap=getName(workerTimeList);

        //人名序列
        List<String> nameList=nameList(getNameMap);

        //员工考勤号+时间作为主键
        for (int j = 0; j <nameList.size() ; j++) {
            //遍历日期
            for (int i = 0; i <dateList.size() ; i++) {
                JobDay jobTime=new JobDay();

                String key=nameList.get(j)+"-"+dateList.get(i);

                jobTime.setNo(nameList.get(j));
                jobTime.setDay(dateList.get(i));
                jobTime.setStartTime(kongFlag);
                jobTime.setEndTime(kongFlag);
                jobTime.setName(getNameMap.get(nameList.get(j)));

                jobTime.setFlag(kongFlag);

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
                jobTime.setEndTime(w.getDay() + " " + w.getEndTime());
                jobTime.setName(w.getName());

                //开始时间
                int hour=Integer.valueOf(w.getStartTime().split(":")[0]);
                int fen=Integer.valueOf(w.getStartTime().split(":")[1]);
                if((hour<9&&fen<60)||(hour==9&&fen<10)){

                }else {
                  jobTime.setFlag("迟到"+(hour-9)+"小时"+fen+"分钟");
                    if((hour-9)>4){
                        jobTime.setFlag(status);
                    }
                }

                hour=Integer.valueOf(w.getEndTime().split(":")[0]);
                if(hour<18){
                    jobTime.setFlag("下班时间有问题请处理");
                }

            }

        }

        //异常
        for (int i = 0; i <workerTimeList.size() ; i++) {

            WorkerTime w=workerTimeList.get(i);
            String day=w.getTime().split(" ")[0];
            String key=w.getNo()+"-"+day;
            JobDay jobTime= map.get(key);

            if(map.get(key)!=null){

                if(jobTime.getStartTime().equals(kongFlag)){

                    String time=getTime(w.getTime(),"14:00",0);
                    jobTime.setStartTime(time);
                    jobTime.setName(w.getName());
                }
                if(jobTime.getEndTime().equals(kongFlag)){

                    String time=getTime(w.getTime(),"14:00",1);
                    jobTime.setEndTime(time);
                    jobTime.setName(w.getName());

                }
            }
        }

//        for (Map.Entry<String, JobDay> entry : map.entrySet()) {
//            //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue().toString());
//            System.out.println(entry.getValue().toString());
//
//        }

        return  map;

    }


    private static  String getTime(String time,String compareDay,int type){
       //开始
       if(type==0){
          if( DataUtil.isCompare(time,compareDay)){
              return time;
          }
       }
       //结束
       else{
           if(!DataUtil.isCompare(time,compareDay)){
               return time;
           }
       }

        return "";
    }


    //获取人数键值对
    private static Map<String,String>  getName(List<WorkerTime> workerTimeList){

        Map<String,String> map=new LinkedHashMap<String, String>();

        for (int i = 0; i <workerTimeList.size() ; i++) {
            map.put(workerTimeList.get(i).getNo(),workerTimeList.get(i).getName());
        }

        System.out.println("用户人数是："+map.size());

        return map;
    }



    private static List<String>   nameList(Map<String,String> map){
        List<String> datelist=new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue().toString());
            datelist.add(entry.getKey());
        }
        return datelist;
    }

    private static  List<String>  dateList(){
        List<String> datelist=new ArrayList<String>();

        String year="2017";
        String month="7";
        int num=32;

        for (int i = 1; i <num ; i++) {
                String date=year+"-"+month+"-"+i;
            if(DataUtil.getWeedId(date)<6){
                datelist.add(date.replace("-","/"));
            }
        }

        return datelist;
    }

    public static void main(String[] args) {

        Map<String,JobDay> map=accountDay();

        List<JobDay> list=new ArrayList<JobDay>();

        for (Map.Entry<String, JobDay> entry : map.entrySet()) {
            //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue().toString());
          //  System.out.println(entry.getValue().toString());
            if(entry.getValue().getEndTime().equals("")||entry.getValue().getStartTime().equals(""))
            {
                entry.getValue().setFlag(status);
            }

            list.add(entry.getValue());
        }

        ExcelUtil.writeExcel(list, 6, "F://file/3.xlsx");

    }
}
