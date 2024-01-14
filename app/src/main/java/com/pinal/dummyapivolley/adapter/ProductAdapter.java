package com.pinal.dummyapivolley.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.pinal.dummyapivolley.DisplayImageActivity;
import com.pinal.dummyapivolley.Model.Products;
import com.pinal.dummyapivolley.R;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<Products.Product> products;
    ArrayList<String> images = new ArrayList<String>();

    public ProductAdapter(Context context, List<Products.Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.title.setText(products.get(position).getTitle());
        holder.description.setText(products.get(position).getDescription());
        holder.brand.setText(products.get(position).getBrand());


        Glide.with((FragmentActivity) this.context).load(this.products.get(position).thumbnail).listener(new RequestListener<Drawable>() { // from class: com.example.photoareditor.Adapter.Sticker_Category_Adapter.1
            @Override
            public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.loading_view.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.loading_view.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.imgthumb);

        holder.itemView.setOnClickListener(v ->{
            images = products.get(position).images;
            Intent intent = new Intent(context, DisplayImageActivity.class);
            intent.putExtra("mylist", images);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imgthumb;
        TextView title,description,brand;
        ProgressBar loading_view;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgthumb = itemView.findViewById(R.id.imgthumb);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            brand = itemView.findViewById(R.id.brand);
            loading_view = itemView.findViewById(R.id.loading_view);
        }
    }
}
