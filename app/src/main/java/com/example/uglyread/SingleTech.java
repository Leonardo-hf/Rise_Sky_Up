package com.example.uglyread;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;

public class SingleTech extends LitePalSupport {
    private int TechId;
    private String name;
    private int ImageId;
    private int time;
    private int effectonMoney;
    private int effectonFame;
    private int effectonSatisfaction;
    private int effectionLeft;
    private int effectionDeath;
    private int key;
    private String detail;

    public SingleTech(String name, int ImageId, int time,int effectionLeft, int effectonMoney,int effectonFame ,int effectonSatisfaction,int effectionDeath, int key,int techId,String detail)
        {this.name=name;
         this.ImageId=ImageId;
         this.time=time;
         this.effectionLeft=effectionLeft;
         this.effectonFame=effectonFame;
         this.effectonMoney=effectonMoney;
         this.effectonSatisfaction=effectonSatisfaction;
         this.key=key;
         this.TechId=techId;
         this.detail=detail;
        }
    public void setTime(int time){this.time=time;}
    public int getTime(){
        return time;
    }
    public int getEffectonMoney(){
        return effectonMoney;
    }
    public int getEffectonFame(){
        return effectonFame;
    }
    public int getEffectonSatisfaction(){
        return effectonSatisfaction;
    }
    public int getKey(){
        return key;
    }
    public String getName(){
        return name;
    }
    public int getTechId() { return TechId; }
    public int getImageId(){
        return ImageId;
    }
    public String getDetail() { return detail; }

    public int getEffectionDeath() {
        return effectionDeath;
    }

    public int getEffectionLeft() {
        return effectionLeft;
    }
}

