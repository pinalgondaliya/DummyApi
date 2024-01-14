package com.pinal.dummyapivolley.retrofit;


import com.pinal.dummyapivolley.Model.Category;
import com.pinal.dummyapivolley.Model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products/categories")
    Call<List<Category>> getCategories();

    @GET("products")
    Call<List<Products.Product>> getProducts();

    @GET("products/categories")
    Call<String> getCategoriesAsString();
}
