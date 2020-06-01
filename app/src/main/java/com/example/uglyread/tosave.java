package com.example.uglyread;

import android.app.Dialog;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class tosave extends Activity {
    int flag=0;
    private class MyOnClickListener implements View.OnClickListener {
        int choose;
        public MyOnClickListener(int choose){
            this.choose=choose;
        }
        @Override
        public void onClick(View v) {

            if(flag==0){

            LitePal.deleteAll(Save.class, "saveId = ?",Integer.toString(choose));
            Save save=new Save(choose,new Statues());
            save.save();
            List<Save> tempsaves=LitePal.where("saveId = ?",Integer.toString(choose)).find(Save.class);
            if(!tempsaves.isEmpty()){
                Toast.makeText(v.getContext(),"已保存",Toast.LENGTH_SHORT ).show();
            }
            }
            else
            { List<Save> saves=LitePal.where("saveId = ?", Integer.toString(choose)).find(Save.class);
             if(saves.isEmpty())
             {Toast.makeText(tosave.this,"空存档",Toast.LENGTH_SHORT).show();
              finish();}
             else {//读入,只需要读入事件状态
             Save temp=new Save();
             temp=saves.get(0);
             temp.help();
             int[] liveEvent=new int[100];
             ArrayList<Integer> liveevent=new ArrayList<>();
             liveevent=temp.getLiveEvent();
             /*int p=0;
             for(Integer each:liveevent){
                 Log.e("every", Integer.toString(each)+Integer.toString(p));
                 p++;
             }
             Log.e("first",Integer.toString(liveevent.size()));*/
             for(int i=0;i<liveevent.size();i++)
                {liveEvent[i]=temp.getLiveEvent().get(i); }

             Statues.setLiveEvent(liveEvent);
             Statues.setEvent_index(temp.getEvent_index());
             ArrayList<Event> eventlist=new ArrayList<>();

             for(int each:temp.getEventlist()) {
                 /*Log.e("every", Integer.toString(each));
                 Log.e("1",Integer.toString(LitePal.where("EventId = ?", Integer.toString(each)).find(Event.class).size()));*/
                 eventlist.addAll(LitePal.where("EventId = ?", Integer.toString(each)).find(Event.class));
             }
            /* for(Event each:eventlist){
                     Log.e("every", Integer.toString(each.getEventId()));
                 }*/
             Statues.setDeath(temp.getDeath());
             Statues.setPeople(temp.getPeople());
             Statues.setEventlist(eventlist);
             Statues.setIsInplement(temp.getIsInplement());
             Statues.setLeft(temp.getLeft());
             Statues.setStage(temp.getStage());
             Statues.setSatisfaction(temp.getSatisfaction());
             Statues.setFame(temp.getFame());
             Statues.setMoney(temp.getMoney());
             Statues.setDay(temp.getDay());
             Log.d("wdnmd3", Integer.toString(temp.getKeyunlock().size()));
             int[] keyunlock=new int[100];
                 for(int i=0;i<temp.getKeyunlock().size();i++)
                 {keyunlock[i]=temp.getKeyunlock().get(i);
                 if(keyunlock[i]==1)
                     Log.d("wdnmd3", Integer.toString(i));}
             Statues.setKeyunlock(keyunlock);
             ArrayList<SingleTech> tosolve=new ArrayList<>();
             int i=0;
             for(int each:temp.getTosolve()) {

                     List<SingleTech> s=LitePal.where("TechId = ?", Integer.toString(each)).find(SingleTech.class);
                     SingleTech s1=s.get(0);
                     s1.setTime(temp.getTechday().get(i));
                     i++;
                     tosolve.add(s1);
                 }
             Statues.setTosolve(tosolve);
             Intent intent=new Intent(tosave.this,first_act.class);
             startActivity(intent);
            }}
            //flag=0;
        }

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent oldintent=getIntent();
        flag=oldintent.getIntExtra("wheretosave", 0);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.save);
        List<Save> saves1=LitePal.where("saveId = ?",Integer.toString(0)).find(Save.class);
        List<Save> saves2=LitePal.where("saveId = ?",Integer.toString(1)).find(Save.class);
        List<Save> saves3=LitePal.where("saveId = ?",Integer.toString(2)).find(Save.class);
        Button auto_save=findViewById(R.id.auto_save);

        if(!saves1.isEmpty())
        {auto_save.setText("自动存档_"+saves1.get(0).getTime());}

        if(flag==0)
            auto_save.setEnabled(false);
        else
            auto_save.setEnabled(true);

            auto_save.setOnClickListener(new MyOnClickListener(0));

        final Button save1=findViewById(R.id.save_1);
        if(!saves2.isEmpty())
        {save1.setText("存档一_"+saves2.get(0).getTime());}
        save1.setOnClickListener(new MyOnClickListener(1){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                if(flag==0)
                    save1.setText("存档一_第"+Statues.getDay()+"天");
                flag=0;
            }
        });

        final Button save2=findViewById(R.id.save_2);
        if(!saves3.isEmpty())
        {save2.setText("存档二_"+saves3.get(0).getTime());}
        save2.setOnClickListener(new MyOnClickListener(2){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                if(flag==0)
                    save2.setText("存档二_第"+Statues.getDay()+"天");
                flag=0;
            }
        });

    }
public void onStop() {
    super.onStop();
    finish();
}
}
