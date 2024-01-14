package com.pinal.dummyapivolley.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pinal.dummyapivolley.Model.Category;
import com.pinal.dummyapivolley.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    List<Category> categoryList;
    private int newpos;
    private final SharedPreferences settings;
    private onItemClickListener onItemClickListener;

    public interface onItemClickListener{
        void onItemClick(int position,String catName);
    }


    public CategoryAdapter(Context context2, List<Category> categoryList,onItemClickListener onItemClickListener) {
        this.context = context2;
        this.categoryList = categoryList;
        this.onItemClickListener = onItemClickListener;
        this.settings = context2.getSharedPreferences("MyPrefsFile", 0);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.settings.edit().putBoolean("my_first_time", true).apply();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.loading_view.setVisibility(View.GONE);
        holder.sticker_category.setText(categoryList.get(position).getCategoryName());
        if (this.newpos == position) {
            holder.stroke.setVisibility(View.VISIBLE);
        } else {
            holder.stroke.setVisibility(View.GONE);
        }


        holder.itemView.setOnClickListener(v -> {
            CategoryAdapter.this.newpos = position;
            CategoryAdapter.this.notifyDataSetChanged();
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position,categoryList.get(position).getCategoryName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView sticker_category;
        ProgressBar loading_view;
        RelativeLayout stroke;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.stroke = (RelativeLayout) itemView.findViewById(R.id.stroke);
            sticker_category = itemView.findViewById(R.id.sticker_category);
            loading_view = itemView.findViewById(R.id.loading_view);
        }
    }
}
