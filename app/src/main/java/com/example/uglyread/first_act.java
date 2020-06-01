package com.example.uglyread;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class first_act extends AppCompatActivity{

    String Tag="first_act";
    protected void onCreate(Bundle savedInstanceState)
    {super.onCreate(savedInstanceState);
    setContentView(R.layout.first_layout);
    ActionBar actionBar=getSupportActionBar();
    if (actionBar!=null)
        actionBar.hide();
    //切换页面后的数据恢复******************************************************
        //new Save(0,new Statues()).help();
        ArrayList<Event> eventlist=Statues.getEventlist();
        //载入初始事件列表-copy自CoreLayout.java，做的太差
        if(eventlist.isEmpty())
        {//Log.e(Tag,"problem here!");
            ArrayList<Event> re = new ArrayList<Event>();//清空事件列表
            List<Event> events = LitePal.where("requireonFame < ? and requireonMoney < ? and requireonSatisfaction < ?",
                    Integer.toString(Statues.getFame()),Integer.toString(Statues.getMoney()),Integer.toString(Statues.getSatisfaction())).find(Event.class);
        int[] keyunlock=Statues.getKeyunlock();//根据key是否解锁进行二次筛选,以及事件的空闲状况<update>
        for(Event each:events){
            if(Statues.getLiveEvent()[each.getEventId()]==1){//事件未被使用
            int flag=1;
            ArrayList<Integer> requirekey=each.getKey();
            Log.e("first",Integer.toString(requirekey.size()));
            if(!requirekey.isEmpty())//判断为空
            {for(int key:requirekey){
                if(keyunlock[key]!=1)
                {flag=0;
                    break;}
            }}
            if(flag==1)//满足条件
            {re.add(each);
            //LitePal.deleteAll(Event.class,"eventId = ?",Integer.toString(each.getEventId()));}//删除数据<update>不需要删除
                Statues.setLiveEvent(each.getEventId());//改变事件使用状态 1->0
        }
        Statues.setEventlist(re);}
        }
        Statues.changeKeyunlock(51,1);
        }
        new Save(0,new Statues()).help();
        //*********************************************************************/
        eventlist=Statues.getEventlist();
        /*for(Event each:eventlist)
            Log.e(Tag,Integer.toString(each.getEventId()));*/
        if(Statues.getEvent_index()>=eventlist.size()) {
            Toast.makeText(first_act.this, "死档或这个存档已通关", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {Event event=eventlist.get(Statues.getEvent_index());
        ImageView imageView=(ImageView)findViewById(R.id.humanimage);//重设图片
        imageView.setImageResource(event.getImageId());
        TextView textView=(TextView)findViewById(R.id.coretext);//重设文本
        textView.setText(event.getText());
        Button choice1=(Button)findViewById(R.id.choice1);//重设按钮-状态-文本
        Button choice2=(Button)findViewById(R.id.choice2);
        Button choice3=(Button)findViewById(R.id.choice3);
        choice1.setEnabled(event.isButtonstatue1());
        choice1.setText(event.getButtontext1());
        choice2.setEnabled(event.isButtonstatue2());
        choice2.setText(event.getButtontext2());
        choice3.setEnabled(event.isButtonstatue3());
        choice3.setText(event.getButtontext3());
        //*********************************************************************
        TextView textView1=(TextView)findViewById(R.id.left_time);
        textView1.setText("距疫苗研发成功还有"+Statues.getLeft()+"个阶段");
        TextView text=(TextView)findViewById(R.id.data);
        text.setText("经济:"+Statues.getMoney()+"\n警戒:"+Statues.getFame()+"\t民意:"+Statues.getSatisfaction());
        TextView title=(TextView)findViewById(R.id.textc) ;
        title.setText(" 时间:第"+Statues.getDay()+"天 安全指数:"+Statues.getPeople());
        //new Save().help();
    Button technique = (Button)findViewById(R.id.technique);
    technique.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent=new Intent(first_act.this,second_act.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    });
        choice1.setOnClickListener(new MyOnClickListener1());
        choice2.setOnClickListener(new MyOnClickListener2());
        choice3.setOnClickListener(new MyOnClickListener3());}
    }
    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ArrayList<Event> eventlist = Statues.getEventlist();
            Event event ;
            //按钮选择对数值的影响放在子类之中，做的太差

            if (Statues.getEvent_index() == eventlist.size() - 1)//下一天
            {
                Statues.setEvent_index(0);
                Statues.setDay(Statues.getDay() + 1);//日期变动
                Toast.makeText(getApplicationContext(), "这是第" + Statues.getDay() + "天", Toast.LENGTH_SHORT).show();

                //疫情感染状况计算公式<wait>
                Random ymstudio=new Random();
                int judge=ymstudio.nextInt(10);
                if(judge<5)
                     Statues.setLeft(Statues.getLeft()-1);
                Statues.setPeople();
                Statues.addMoney();

                if (Statues.getTosolve() != null && !Statues.getTosolve().isEmpty()) {


                    Iterator iterator = Statues.getTosolve().iterator();
                    while (iterator.hasNext()) {
                        SingleTech singleTech = (SingleTech) iterator.next();
                        int time = singleTech.getTime() - 1;
                        if (time <= 0) {
                            iterator.remove();
                            Toast.makeText(first_act.this, singleTech.getName() + "落实了!", Toast.LENGTH_SHORT).show();
                            Statues.setLeft(Statues.getLeft()+singleTech.getEffectionLeft());//改变研发时间
                            Statues.setMoney(Statues.getMoney()+singleTech.getEffectonMoney());//改变经济
                            Statues.setFame(Statues.getFame()+singleTech.getEffectonFame());//改变声誉
                            Statues.setSatisfaction(Statues.getSatisfaction()+singleTech.getEffectonSatisfaction());//改变民意
                            Statues.setDeath(Statues.getDeath()+singleTech.getEffectionDeath());//改变死亡率
                            if(singleTech.getKey()!=0)
                                Statues.changeKeyunlock(singleTech.getKey(),1);
                            ArrayList<Integer> isInplement=Statues.getIsInplement();
                            isInplement.set(singleTech.getTechId(),2);
                            Statues.setIsInplement(isInplement);
                        } else {
                            singleTech.setTime(time);
                        }
                    }
                    ArrayList<SingleTech> temp=Statues.getTosolve();
                    for(int i=0;i<temp.size();){
                        if(temp.get(i).getTime()<=0)
                            temp.remove(i);
                        else
                            i++;
                    }
                    Statues.setTosolve(temp);
                }
                ArrayList<Event> re = new ArrayList<Event>();//新建事件列表
                //加入特殊日期

                Event changeday = new Event("event_0", "忙碌的一天结束了，辛苦辛苦！", "进入下一天！", "", "", true, false,
                        false, 0, 0,0,0,0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0);
                re.add(changeday);
                //读取数据库，获取新的事件列表
                //根据数值进行一次筛选
                List<Event> events = LitePal.where("requireonFame <= ? and requireonMoney <= ? and requireonSatisfaction <= ?",
                        Integer.toString(Statues.getFame()), Integer.toString(Statues.getMoney()), Integer.toString(Statues.getSatisfaction())).find(Event.class);
                int[] keyunlock = Statues.getKeyunlock();//根据key是否解锁进行二次筛选,以及事件的空闲状况<update>
                for (Event each : events) {
                    if (Statues.getLiveEvent()[each.getEventId()] == 1) {//事件未被使用
                        int flag = 1;
                        ArrayList<Integer> requirekey = each.getKey();
                        //Log.e("core",Integer.toString(requirekey.size()));
                        if (!requirekey.isEmpty())//判断为空
                        {
                            for (int key : requirekey) {
                                if (keyunlock[key] != 1) {
                                    flag = 0;
                                    break;
                                }
                            }
                        }
                        if (flag == 1)//满足条件
                        {
                            re.add(each);
                        }
                    }
                }
                if(re.size()>4)
                    {   ArrayList<Event> temp=new ArrayList<>();
                        temp.add(changeday);
                        Random choosevent=new Random();
                        int index1,index2,index3;
                        index1=choosevent.nextInt(re.size()-1)+1;
                        while((index2=choosevent.nextInt(re.size()-1)+1)==index1)
                            continue;
                        while(((index3=choosevent.nextInt(re.size()-1)+1)==index1)||(index3==index2))
                            continue;
                        temp.add(re.get(index1));
                        temp.add(re.get(index2));
                        temp.add(re.get(index3));
                        re=temp;}
                else{Statues.setStage(Statues.getStage()+1);
                    Statues.changeKeyunlock(Statues.getStage()+50, 1);}
                Statues.setEventlist(re);
                for(int i=1;i<re.size();i++){
                    Statues.setLiveEvent(re.get(i).getEventId());//改变事件使用状态 1->0
                }
                //game over<wait>
                if(Statues.getLeft()<=0)//战胜病毒——疫苗结局
                    {
                    ArrayList<Event> goodend=new ArrayList<>();
                    Event good=new Event("end_good", "疫苗研发成功了，并以最快的速度在全世界接种，人类获救！", "Thanks for playing!", 101,0,0,0,0 );
                    goodend.add(good);
                    Statues.setEventlist(goodend);
                    Button goodendchoose=findViewById(R.id.choice1);
                    goodendchoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(first_act.this,"胜利！", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                }
                else if(Statues.getPeople()<=0){
                    ArrayList<Event> badend=new ArrayList<>();
                    Event bad=new Event("end_bad", "你的国家终究败在病毒手下，你携着仅存的百姓，逃到了其他国家。至少今天，人类的火种仍在地球上燃烧。", "请务必再试一次", 102,0,0,0,0 );
                    badend.add(bad);
                    Statues.setEventlist(badend);
                    Button goodendchoose=findViewById(R.id.choice1);
                    goodendchoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(first_act.this,"失败...", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                }
                else if(keyunlock[41]==1){
                    ArrayList<Event> spend1=new ArrayList<>();
                    Event sp1=new Event("end_sp1", "时光荏苒，岁月如梭，无论结局如何，可以肯定的是，这里早已不是你的天空", "...", 103,0,0,0,0 );
                    spend1.add(sp1);
                    Statues.setEventlist(spend1);
                    Button spend1choose=findViewById(R.id.choice1);
                    spend1choose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(first_act.this,"成功存活！", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                }
                else if(keyunlock[14]==1){
                    Random todeath=new Random();
                    if(todeath.nextInt(100)<20){
                    ArrayList<Event> spend2=new ArrayList<>();
                    Event sp2=new Event("end_sp2", "生前何必久梦，死后必当长眠。什么？你问这是哪里，这汤你还买不买了？", "不...", 104,0,0,0,0 );
                    spend2.add(sp2);
                    Statues.setEventlist(spend2);
                    Button spend2choose=findViewById(R.id.choice1);
                    spend2choose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(first_act.this,"Death!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });}
                    else
                        keyunlock[14]=0;
                }
                else if (keyunlock[42]==1) {
                    ArrayList<Event> normalend=new ArrayList<>();
                    Event normal=new Event("end_normal", "国内的疫情已经被你压制的死死地，而疫苗的成功指日可待，恭喜，让我们的付出没有白费！", "这一切都物超所值", 105,0,0,0,0 );
                    normalend.add(normal);
                    Statues.setEventlist(normalend);
                    Button normalchoose=findViewById(R.id.choice1);
                    normalchoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(first_act.this,"胜利！", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                }
                else{
                    if(Statues.getMoney()<200||Statues.getSatisfaction()<0)
                        Statues.setDeath(1000);
                    int stand=new Random().nextInt(100);
                    Log.d("wdnmd1",Integer.toString(stand)+Integer.toString(Statues.getDeath()));
                    if(Statues.getDeath()<0)
                        if(stand<-1*Statues.getDeath())
                        {ArrayList<Event> spend3=new ArrayList<>();
                        Event sp3=new Event("end_sp3", "或是巫术或是科技，总有一种神秘的力量让你顷刻间战胜了那曾经不可一世的病毒，所以下一步该做的是征服地球么？", "向星辰大海出发！", 106,0,0,0,0 );
                        spend3.add(sp3);
                        Statues.setEventlist(spend3);
                        Button spend3choose=findViewById(R.id.choice1);
                        spend3choose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(first_act.this,"胜利？！", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });}
                    if(Statues.getDeath()>0)
                            if(stand<Statues.getDeath())
                            {//Log.d("wdnmd2", "???");
                                ArrayList<Event> spend4=new ArrayList<>();
                                Event sp4=new Event("end_sp4", "民族主义，经济危机，政治暴乱，帝国的大厦于顷刻间倒塌，究竟是伤痕累累，不堪重负！", "只是没想到会是今天", 107,0,0,0,0 );
                                spend4.add(sp4);
                                Statues.setEventlist(spend4);
                                Button spend4choose=findViewById(R.id.choice1);
                                spend4choose.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(first_act.this,"溃败！", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                });}
                }

                //自动存档，保存所有数据，以及事件触发状态
                LitePal.deleteAll(Save.class,"saveId = ?","0");
                Save save = new Save(0, new Statues());
                save.save();
                eventlist = Statues.getEventlist();//更新一下这个类里的eventlist
            }
            //通常情况，载入下一个event
            else
                Statues.setEvent_index(Statues.getEvent_index() + 1);
            TextView textView1= findViewById(R.id.left_time);
            textView1.setText("距疫苗研发成功还有"+Statues.getLeft()+"个阶段");
            TextView textview=(TextView)findViewById(R.id.data);
            textview.setText("经济:"+Statues.getMoney()+"\n警戒:"+Statues.getFame()+"\t民意:"+Statues.getSatisfaction());
            TextView title=(TextView)findViewById(R.id.textc) ;
            title.setText(" 时间:第"+Statues.getDay()+"天 安全指数:"+Statues.getPeople());
                /*Save helps=new Save(0,new Statues());
                helps.help();*/
            event = eventlist.get(Statues.getEvent_index());
            ImageView imageView = (ImageView) findViewById(R.id.humanimage);//重设图片
            Animation test = new AlphaAnimation(0.2f,1.0f);
            test.setDuration(800);
            imageView.startAnimation(test);
            imageView.setImageResource(event.getImageId());
            TextView textView = (TextView) findViewById(R.id.coretext);//重设文本
            //textView.setText(event.getText());
            new JiangeUtil(textView, event.getText(),800).startTv(1);//逐字显示的动画效果
            Button choice1 = (Button) findViewById(R.id.choice1);//重设按钮-状态-文本
            Button choice2 = (Button) findViewById(R.id.choice2);
            Button choice3 = (Button) findViewById(R.id.choice3);
            choice1.setEnabled(event.isButtonstatue1());
            choice1.setText(event.getButtontext1());
            choice2.setEnabled(event.isButtonstatue2());
            choice2.setText(event.getButtontext2());
            choice3.setEnabled(event.isButtonstatue3());
            choice3.setText(event.getButtontext3());
        }
    }

    private class MyOnClickListener1 extends MyOnClickListener {
        @Override
        public void onClick(View v) {

            ArrayList<Event> eventlist=Statues.getEventlist();
            Event event=eventlist.get(Statues.getEvent_index());
            Statues.setLeft(Statues.getLeft()+event.getEffectonLeft());//改变研发时间
            Statues.setMoney(Statues.getMoney()+event.getEffectonMoney());//改变经济
            Statues.setFame(Statues.getFame()+event.getEffectonFame());//改变声誉
            Statues.setSatisfaction(Statues.getSatisfaction()+event.getEffectonSatisfaction());//改变民意
            if(!event.getEffectionKey1().isEmpty())//解锁key
            {
                for(int key:event.getEffectionKey1())
                    Statues.changeKeyunlock(key,1);
            }
            super.onClick(v);
        }
    }
    private class MyOnClickListener2 extends MyOnClickListener {
        @Override
        public void onClick(View v) {

            ArrayList<Event> eventlist=Statues.getEventlist();
            Event event=eventlist.get(Statues.getEvent_index());
            Statues.setLeft(Statues.getLeft()+event.getEffectonLeft2());//改变研发时间
            Statues.setMoney(Statues.getMoney()+event.getEffectonMoney2());//改变经济
            Statues.setFame(Statues.getFame()+event.getEffectonFame2());//改变声誉
            Statues.setSatisfaction(Statues.getSatisfaction()+event.getEffectonSatisfaction2());//改变民意
            if(!event.getEffectionKey2().isEmpty())//解锁key
            {
                for(int key:event.getEffectionKey2())
                    Statues.changeKeyunlock(key,1);
            }
            super.onClick(v);
        }
    }
    private class MyOnClickListener3 extends MyOnClickListener {
        @Override
        public void onClick(View v) {

            ArrayList<Event> eventlist=Statues.getEventlist();
            Event event=eventlist.get(Statues.getEvent_index());
            Statues.setLeft(Statues.getLeft()+event.getEffectonLeft3());//改变研发时间
            Statues.setMoney(Statues.getMoney()+event.getEffectonMoney3());//改变经济
            Statues.setFame(Statues.getFame()+event.getEffectonFame3());//改变声誉
            Statues.setSatisfaction(Statues.getSatisfaction()+event.getEffectonSatisfaction3());//改变民意
            if(!event.getEffectionKey3().isEmpty())//解锁key
            {
                for(int key:event.getEffectionKey3())
                    Statues.changeKeyunlock(key,1);
            }
            super.onClick(v);
        }
    }
}