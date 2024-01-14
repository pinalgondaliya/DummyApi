package com.pinal.dummyapivolley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pinal.dummyapivolley.R;

import java.util.ArrayList;

public class DisplayImageAdapter extends RecyclerView.Adapter<DisplayImageAdapter.ViewHolder> {
    Context context;
    ArrayList<String> list;

    public DisplayImageAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position)).into(holder.immg_ssss);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView immg_ssss;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            immg_ssss = itemView.findViewById(R.id.immg_ssss);
        }
    }
}
