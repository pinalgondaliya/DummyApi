package com.pinal.dummyapivolley.adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pinal.dummyapivolley.EmptyFragment;
import com.pinal.dummyapivolley.Model.Category;
import com.pinal.dummyapivolley.Model.Products;
import com.pinal.dummyapivolley.ProductFragment;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.pinal.dummyapivolley.Model.Category;
import com.pinal.dummyapivolley.Model.Products;
import com.pinal.dummyapivolley.ProductFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final List<Category> categories;
    private final List<List<Products.Product>> productsByCategoryList;

    public ViewPagerAdapter(FragmentActivity fragmentActivity, List<Category> categories, List<List<Products.Product>> productsByCategoryList) {
        super(fragmentActivity);
        this.categories = categories;
        this.productsByCategoryList = productsByCategoryList;
    }

    @Override
    public Fragment createFragment(int position) {
        String categoryName = categories.get(position).getCategoryName();
        List<Products.Product> productList = productsByCategoryList.get(position);

        return new ProductFragment(categoryName,productList);
    }



    @Override
    public int getItemCount() {
        return categories.size();
    }

    public CharSequence getPageTitle(int position) {
        return categories.get(position).getCategoryName();
    }
}

