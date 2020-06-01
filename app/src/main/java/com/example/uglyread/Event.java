package com.example.uglyread;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Event extends LitePalSupport {
    private String name;//图片的名称
    private String text;//文本
    private String buttontext1;//按钮一文本
    private String buttontext2;//按钮二文本
    private String buttontext3;//按钮三文本
    private boolean buttonstatue1;//按钮一是否使用
    private boolean buttonstatue2;//按钮二是否使用
    private boolean buttonstatue3;//按钮三是否使用
    private int eventId;//事件编号-用于载入事件
    private int ImageId;//事件
    private int effectonLeft;//选项一对研发时间的影响
    private int effectonMoney;//选择一对经济的影响
    private int effectonFame;//选择一对名誉的影响
    private int effectonSatisfaction;//选择一对民意的影响
    private int effectonLeft2;//选项二对研发时间的影响
    private int effectonMoney2;//选择二对经济的影响
    private int effectonFame2;//选择二对名誉的影响
    private int effectonSatisfaction2;//选择二对民意的影响
    private int effectonLeft3;//选项三对研发时间的影响
    private int effectonMoney3;//选择三对经济的影响
    private int effectonFame3;//选择三对名誉的影响
    private int effectonSatisfaction3;//选择三对民意的影响
    private int requireonMoney;//解锁所需要的经济
    private int requireonFame;//解锁所需要的声誉
    private int requireonSatisfaction;//解锁所需要的民意
    private ArrayList<Integer> key=new ArrayList<>();//事件解锁所需要的key
    private ArrayList<Integer> effectionKey1=new ArrayList<>();//选项一解锁的key
    private ArrayList<Integer> effectionKey2=new ArrayList<>();//选项二解锁的key
    private ArrayList<Integer> effectionKey3=new ArrayList<>();//选项三解锁的key


    public Event(String name,String text,String buttontext1,String buttontext2,String buttontext3,boolean buttonstatue1,boolean buttonstatue2,
                 boolean buttonstatue3,int eventId,int effectonLeft,int effectonMoney,int effectonFame,int effectonSatisfaction, int effectonLeft2,int effectonMoney2,int effectonFame2,
                 int effectonSatisfaction2,int effectonLeft3,int effectonMoney3,int effectonFame3,int effectonSatisfaction3,int requireonMoney, int requireonFame,int requireonSatisfaction)
    {   this.name=name;
        this.text=text;
        this.setImageId(name);
        this.eventId=eventId;
        this.buttonstatue1=buttonstatue1;
        this.buttonstatue2=buttonstatue2;
        this.buttonstatue3=buttonstatue3;
        this.buttontext1=buttontext1;
        this.buttontext2=buttontext2;
        this.buttontext3=buttontext3;
        this.effectonFame=effectonFame;
        this.effectonMoney=effectonMoney;
        this.effectonSatisfaction=effectonSatisfaction;
        this.effectonFame2=effectonFame2;
        this.effectonMoney2=effectonMoney2;
        this.effectonSatisfaction2=effectonSatisfaction2;
        this.effectonFame3=effectonFame3;
        this.effectonMoney3=effectonMoney3;
        this.effectonSatisfaction3=effectonSatisfaction3;
        this.requireonFame=requireonFame;
        this.requireonMoney=requireonMoney;
        this.requireonSatisfaction=requireonSatisfaction;
        this.effectonLeft=effectonLeft;
        this.effectonLeft2=effectonLeft2;
        this.effectonLeft3=effectonLeft3;
    }
    public Event(String name,String text,String buttontext1,int eventId,int effectonLeft,int effectonMoney,int effectonFame,int effectonSatisfaction){
        this.name=name;
        this.text=text;
        this.setImageId(name);
        this.eventId=eventId;
        this.buttonstatue1=true;
        this.buttonstatue2=false;
        this.buttonstatue3=false;
        this.buttontext1=buttontext1;
        this.buttontext2="";
        this.buttontext3="";
        this.effectonLeft=effectonLeft;
        this.effectonFame=effectonFame;
        this.effectonMoney=effectonMoney;
        this.effectonSatisfaction=effectonSatisfaction;
        this.requireonFame=0;
        this.requireonMoney=0;
        this.requireonSatisfaction=0;
    }
    public void initKey(int... key){
        for(int mkey:key){
            this.key.add(mkey);
        }
    }
    public void initeffectionKey1(int... key){
        for(int mkey:key){
            this.effectionKey1.add(mkey);
        }
    }
    public void initeffectionKey2(int... key){
        for(int mkey:key){
            this.effectionKey2.add(mkey);
        }
    }
    public void initeffectionKey3(int... key){
        for(int mkey:key){
            this.effectionKey3.add(mkey);
        }
    }

    public ArrayList<Integer> getEffectionKey1() {
        return effectionKey1;
    }

    public ArrayList<Integer> getEffectionKey2() {
        return effectionKey2;
    }

    public ArrayList<Integer> getEffectionKey3() {
        return effectionKey3;
    }

    public ArrayList<Integer> getKey() {
        return key;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public int getRequireonFame() {
        return requireonFame;
    }

    public int getRequireonMoney() {
        return requireonMoney;
    }

    public int getRequireonSatisfaction() {
        return requireonSatisfaction;
    }

    public void setRequireonFame(int requireonFame) {
        this.requireonFame = requireonFame;
    }

    public void setRequireonMoney(int requireonMoney) {
        this.requireonMoney = requireonMoney;
    }

    public void setRequireonSatisfaction(int requireonSatisfaction) {
        this.requireonSatisfaction = requireonSatisfaction;
    }

    public int getEffectonLeft(){return effectonLeft;}

    public int getEffectonLeft2(){return effectonLeft2;}

    public int getEffectonLeft3(){return effectonLeft3;}

    public void setEffectonLeft(int effectonLeft){
        this.effectonLeft=effectonLeft;
    }

    public void setEffectonLeft2(int effectonLeft){
        this.effectonLeft2=effectonLeft;
    }

    public void setEffectonLeft3(int effectonLeft){
        this.effectonLeft3=effectonLeft;
    }

    public int getEffectonFame2() {
        return effectonFame2;
    }

    public void setEffectonSatisfaction2(int effectonSatisfaction2) {
        this.effectonSatisfaction2 = effectonSatisfaction2;
    }

    public void setEffectonSatisfaction3(int effectonSatisfaction3) {
        this.effectonSatisfaction3 = effectonSatisfaction3;
    }

    public void setEffectonFame3(int effectonFame3) {
        this.effectonFame3 = effectonFame3;
    }

    public void setEffectonMoney3(int effectonMoney3) {
        this.effectonMoney3 = effectonMoney3;
    }

    public void setEffectonMoney2(int effectonMoney2) {
        this.effectonMoney2 = effectonMoney2;
    }

    public void setEffectonFame2(int effectonFame2) {
        this.effectonFame2 = effectonFame2;
    }

    public int getEffectonSatisfaction3() {
        return effectonSatisfaction3;
    }

    public int getEffectonSatisfaction2() {
        return effectonSatisfaction2;
    }

    public int getEffectonFame3() {
        return effectonFame3;
    }

    public int getEffectonMoney3() {
        return effectonMoney3;
    }

    public int getEffectonMoney2() {
        return effectonMoney2;
    }

    public boolean isButtonstatue1() {
        return buttonstatue1;
    }

    public boolean isButtonstatue2() {
        return buttonstatue2;
    }

    public boolean isButtonstatue3() {
        return buttonstatue3;
    }

    public String getButtontext1() {
        return buttontext1;
    }

    public String getButtontext2() {
        return buttontext2;
    }

    public String getButtontext3() {
        return buttontext3;
    }

    public void setButtonstatue1(boolean buttonstatue1) {
        this.buttonstatue1 = buttonstatue1;
    }

    public void setButtonstatue2(boolean buttonstatue2) {
        this.buttonstatue2 = buttonstatue2;
    }

    public void setButtonstatue3(boolean buttonstatue3) {
        this.buttonstatue3 = buttonstatue3;
    }

    public void setButtontext1(String buttontext1) {
        this.buttontext1 = buttontext1;
    }

    public void setButtontext2(String buttontext2) {
        this.buttontext2 = buttontext2;
    }

    public void setButtontext3(String buttontext3) {
        this.buttontext3 = buttontext3;
    }

    public String getText() { return text; }

    public void setText(String text) {this.text = text; }

    public int getEffectonFame() {
        return effectonFame;
    }

    public int getEffectonMoney() {
        return effectonMoney;
    }

    public int getEffectonSatisfaction() {
        return effectonSatisfaction;
    }

    public int getImageId() {
        return ImageId;
    }


    public void setEffectonFame(int effectonFame) {
        this.effectonFame = effectonFame;
    }

    public void setEffectonMoney(int effectonMoney) {
        this.effectonMoney = effectonMoney;
    }

    public void setEffectonSatisfaction(int effectonSatisfaction) {
        this.effectonSatisfaction = effectonSatisfaction;
    }

    public void setImageId(String name) {
        Field field = null;
        try {
            field = R.drawable.class.getField(name);
            int res_ID = field.getInt(field.getName());
            this.ImageId = res_ID;
        } catch (Exception e) {
        }

    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

