package com.example.hisabbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }


    private String[] data;
    public Adapter(String[] data){
        this.data=data;

    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext()) ;
        View view=inflater.inflate(R.layout.recycler_view_author,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String datas=data[position];
        int a=position+1;
        holder.category_text.setText(a+".  "+datas);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView category_text;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            category_text=itemView.findViewById(R.id.text_view_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position =getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
