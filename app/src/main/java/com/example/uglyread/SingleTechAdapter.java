package com.example.uglyread;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class SingleTechAdapter extends RecyclerView.Adapter<SingleTechAdapter.ViewHolder>{
    private List<SingleTech> mTechList;
    private Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View techView;
        ImageView techimage;
        TextView techtext;
        Button techbutton;
        //int techbuttonflag = 0;
        public ViewHolder(View view){
            super(view);
            techView=view;
            techimage=(ImageView)view.findViewById(R.id.techimage);
            techtext=(TextView)view.findViewById(R.id.techtext);
            techbutton=(Button)view.findViewById(R.id.techbutton);
        }
    }
    public SingleTechAdapter(List<SingleTech> techList,Context context){
        mTechList=techList;
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singletech,parent,false);
        final ViewHolder holder= new ViewHolder(view);
        holder.techView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                SingleTech tech = mTechList.get(position);
                Toast.makeText(v.getContext(),"这是政策： "+tech.getName(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,SingleTech_detail.class);
                String content=LitePal.where("TechId = ?",Integer.toString(tech.getTechId())).find(SingleTech.class).get(0).getDetail();
                intent.putExtra("content",content);
                mContext.startActivity(intent);
            }
        });
        holder.techbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SingleTech tech = mTechList.get(position);
                //  if(holder.techbuttonflag==0){
                Toast.makeText(v.getContext(),"你执行了"+tech.getName()+"\n该措施将在"+tech.getTime()+"天后落实",Toast.LENGTH_SHORT).show();
                //tech.setTime(tech.getTime()-1);
                ArrayList<Integer> isInplement=Statues.getIsInplement();
                isInplement.set(position,1);
                Statues.setIsInplement(isInplement);
                Statues.addTosolve(tech);
                holder.techbutton.setEnabled(false);
                holder.techbutton.setText("进行中!");}
                //       holder.techbuttonflag++;

        });
        return holder;
    }

    public void  onBindViewHolder(ViewHolder holder, final int position){
        final SingleTech singleTech=mTechList.get(position);
        holder.techimage.setImageResource(singleTech.getImageId());
        holder.techtext.setText(singleTech.getName());
        if((Statues.getIsInplement()).get(position) ==0)
            holder.techbutton.setText("执行");
        else if(Statues.getIsInplement().get(position)==1)
            {holder.techbutton.setText("进行中!");
            holder.techbutton.setEnabled(false);}
        else
        { holder.techbutton.setEnabled(false);
            holder.techbutton.setText("已落实");
        }
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return  mTechList.size();
    }
}
