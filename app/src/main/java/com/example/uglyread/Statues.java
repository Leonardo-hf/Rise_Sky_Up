package com.example.uglyread;

import android.util.Log;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Statues  {
    private static int fame=1;
    private static int satisfaction=1;
    private static int money=1;
    private static int left=25;
    private static int stage=1;
    private static int day=1;
    private static int death=0;
    private static int people=10000;
    private static ArrayList<SingleTech> tosolve =new ArrayList<SingleTech>();//等待落实的政策
    private static ArrayList<Integer> isInplement = new ArrayList<Integer>();//政策列表，是否被实施
    private static ArrayList<SingleTech> techlist = new ArrayList<SingleTech>();//储存所有政策
    private static ArrayList<Event> eventlist = new ArrayList<Event>();//储存这一天/下一天的所有事件
    private static int event_index=0;
    private static int[] LiveEvent=new int[100];//现有未使用的event，0/1表示，1表示未使用
    private static int[] keyunlock=new int[100];//所有key的状态，0/1表示

    //////////////////////////////////////////////////////////////LiveEvent
    static {
        for(int n=0;n<Statues.LiveEvent.length;n++)//初始化key集
        {
            Statues.LiveEvent[n]=1;
        }
    }

    public static int[] getLiveEvent() {
        return LiveEvent;
    }

    public static void setLiveEvent(int[] liveEvent) {
        LiveEvent = liveEvent;
    }

    public static void setLiveEvent(int eventId){
        int[] LiveEvent=Statues.getLiveEvent();
        LiveEvent[eventId]=0;
        Statues.setLiveEvent(LiveEvent);
    }
    ///////////////////////////////////////////////////////////key
    static {
        keyunlock[0]=1;
        for(int n=1;n<Statues.keyunlock.length;n++)//初始化key集
        {
            Statues.keyunlock[n]=0;
        }
    }
    public static int[] getKeyunlock() {
        return keyunlock;
    }
    public static void setKeyunlock(int[] key) {
        /*int[] keyunlock=new int[100];
        for(int n=0;n<key.length;n++)
        {
            keyunlock[key[n]]=1;
        }*/
        Statues.keyunlock=key;
    }
    public static void changeKeyunlock(int index,int possible)//修改已解锁key集
    {int[] keyunlock=Statues.keyunlock;
    keyunlock[index]=possible;
    Statues.keyunlock=keyunlock;
    }
    //////////////////////////////////////////////////////////////////////////
    public static int getEvent_index() {
        return event_index;
    }

    public static void setEvent_index(int event_index) {
        Statues.event_index = event_index;
    }

    public static ArrayList<Event> getEventlist() {
        return eventlist;
    }

    public static void setEventlist(ArrayList<Event> eventlist) {
        Statues.eventlist = eventlist;
    }

    public static ArrayList<SingleTech> getTechlist() {
        return techlist;
    }

    public static void setTechlist(ArrayList<SingleTech> techlist) {
        Statues.techlist = techlist;
    }

    public static ArrayList<Integer> getIsInplement() {
        return isInplement;
    }

    public static void setIsInplement(ArrayList<Integer> isInplement) {
        Statues.isInplement = isInplement;
    }

    public static void addTosolve(SingleTech tech) {
        Statues.tosolve.add(tech);
    }
    public static void solvetask() {
        for(SingleTech tech:tosolve){
            if(tech.getTime()==0){
                Statues.fame+=tech.getEffectonFame();
                Statues.satisfaction+=tech.getEffectonSatisfaction();
                Statues.money+=tech.getEffectonMoney();
                //关于key
            }
        }
    }

    public static void setDeath(int death) {
        Statues.death = death;
    }

    public static void setTosolve(ArrayList<SingleTech> tosolve) {
        Statues.tosolve=tosolve;
    }
    public static void setMoney(int money) { Statues.money = money; }
    public static void addDay()
    {
        Statues.day++;
    }
    public static void setDay(int day) { Statues.day = day; }
    public static void setFame(int fame){
        Statues.fame =fame;
    }
    public static void setSatisfaction(int satisfaction){
        Statues.satisfaction =satisfaction;
    }
    public static void setLeft(int left){Statues.left=left;}

    public static void setPeople(int people) {
        Statues.people = people;
    }
    public static void setPeople() {
        int cut;
        double temp=0;
        Random cutrandom=new Random();
        double cutrand=1.0*cutrandom.nextInt(50)/100+0.75;
        int increase;
        if(keyunlock[56]==1||keyunlock[57]==1)
            cut=4*fame-500;
        else if(keyunlock[54]==1||keyunlock[55]==1)
            cut=5*fame-1000;
        else if(keyunlock[52]==1||keyunlock[53]==1)
            cut=-400+fame*2;
        else
            cut=-50+fame;
        cut=(int)(cut*cutrand);
        Log.e("wdnmd",Double.toString(cutrand));
        if(cut>0)
            cut=0;
        increase=(int)Math.round(1.0*(10000-people)*cutrandom.nextInt(5)/100);
        Log.e("wdnmd",Integer.toString(cut));
        Statues.people=Statues.people+cut+increase;
    }
    public static void setStage(int stage) {
        Statues.stage = stage;
    }
    public static void addStage() {
        Statues.stage = Statues.stage+1;
    }
    public static int getFame(){
        return fame;
    }
    public static int getSatisfaction(){
        return satisfaction;
    }
    public static ArrayList<SingleTech> getTosolve() { return tosolve; }
    public static int getDay() { return day; }
    public static int getMoney() { return money; }
    public static int getLeft(){return left;}

    public static int getDeath() {
        return death;
    }

    public static int getPeople() {
        return people;
    }

    public static int getStage() {
        return stage;
    }

    public static void addMoney(){
        Random random=new Random();
        int much=(75-fame)*(random.nextInt(50)+75)/100;
        Log.d("wdnmd2", Integer.toString(much));
        money=money+much;
    }
}


