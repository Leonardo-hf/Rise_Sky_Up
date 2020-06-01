package com.example.uglyread;

import android.util.Log;
import android.widget.ArrayAdapter;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class Save extends LitePalSupport {
    private int saveId;
    //private String world;世界标签<wait>
    private String time;//显示日期
    //private Statues statues;//储存状态
    private int people;
    private int fame;
    private int left;
    private int satisfaction;
    private int money;
    private int day;
    private int stage;
    private int death;
    private ArrayList<Integer> tosolve =new ArrayList<>();//等待落实的政策
    private ArrayList<Integer> isInplement =new ArrayList<>();//政策列表，是否被实施
    //private ArrayList<Integer> techlist ;//储存所有政策
    private ArrayList<Integer> eventlist=new ArrayList<>() ;//储存这一天/下一天的所有事件
    private int event_index;
    private ArrayList<Integer> LiveEvent =new ArrayList<>();//现有未使用的event，0/1表示，1表示未使用
    private ArrayList<Integer> keyunlock=new ArrayList<>();//所有key的状态，0/1表示
    private ArrayList<Integer> techday=new ArrayList<>();//
    public Save(){

    }
    public void help(){
        for(int i=0;i<eventlist.size();i++)
            Log.e("help", "eventnow:"+Integer.toString(eventlist.get(i)));
        for(int i=0;i<LiveEvent.size();i++)
            if(LiveEvent.get(i)==0)
                Log.e("help", "usedEvent:"+Integer.toString(i));
        for(int i=0;i<keyunlock.size();i++)
            if(keyunlock.get(i)==1)
                Log.e("help", "getkey:"+Integer.toString(i));
        for(int i=0;i<tosolve.size();i++)
            Log.e("help", tosolve.get(i)+"(tech):(day)"+techday.get(i));
    }
    public Save(int saveId,Statues temp){
        this.people=temp.getPeople();
        this.saveId=saveId;
       // this.statues=temp;
        this.left=temp.getLeft();
        this.fame=temp.getFame();
        this.money=temp.getMoney();
        this.satisfaction=temp.getSatisfaction();
        this.day=temp.getDay();
        this.event_index=temp.getEvent_index();
        this.isInplement=temp.getIsInplement();
        this.stage=temp.getStage();
        this.time="第"+temp.getDay()+"天";
        /*Log.e("Save", "Id:"+Integer.toString(saveId));
        Log.e("Save","Choose:"+ Integer.toString(temp.getEvent_index()));*/
        ///////////////////////////////////////////////eventlist
        for(Event each:temp.getEventlist())
        {
            this.eventlist.add(each.getEventId());
        }

        ///////////////////////////////////////////////techlist
        /*ArrayList<Integer> alltech=new ArrayList<>();
        for(SingleTech each:temp.getTechlist())
        {
            eventlist.add(each.getTechId());
        }
        this.techlist=alltech;*/
        ///////////////////////////////////////////////tosolve
        for(SingleTech each:temp.getTosolve())
        {
            this.tosolve.add(each.getTechId());
            this.techday.add(each.getTime());
        }
        ///////////////////////////////////////////////liveEvent
        for(int i=0;i<100;i++)
        {
            this.LiveEvent.add(temp.getLiveEvent()[i]);
        }
        //Log.e("save",Integer.toString(this.LiveEvent.size()));
        ///////////////////////////////////////////////keyunlock
        for(int i=0;i<100;i++)
        {
           this.keyunlock.add(temp.getKeyunlock()[i]);
        }
    }
    public int getSaveId() {
        return saveId;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<Integer> getTechday() {
        return techday;
    }

    public int getEvent_index() {
        return event_index;
    }

    public ArrayList<Integer> getEventlist() {
        return eventlist;
    }


    public ArrayList<Integer> getIsInplement() {
        return isInplement;
    }

    public int getMoney() {
        return money;
    }

    public int getDay() {
        return day;
    }

    public int getFame() {
        return fame;
    }

    public int getLeft() {
        return left;
    }

    public int getPeople() {
        return people;
    }

    public int getStage() {
        return stage;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public ArrayList<Integer> getKeyunlock() {
        return keyunlock;
    }

    public int getDeath() {
        return death;
    }
/*public ArrayList<Integer> getTechlist() {
        return techlist;
    }*/

    public ArrayList<Integer> getTosolve() {
        return tosolve;
    }

    public ArrayList<Integer> getLiveEvent() {
        return LiveEvent;
    }
}
