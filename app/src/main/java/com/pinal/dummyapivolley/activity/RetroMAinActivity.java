package com.pinal.dummyapivolley.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pinal.dummyapivolley.Model.Category;
import com.pinal.dummyapivolley.Model.Products;
import com.pinal.dummyapivolley.R;
import com.pinal.dummyapivolley.adapter.CategoryAdapter;
import com.pinal.dummyapivolley.adapter.ProductAdapter;
import com.pinal.dummyapivolley.retrofit.ApiService;
import com.pinal.dummyapivolley.retrofit.RetrofitInstance;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroMAinActivity extends AppCompatActivity {

    private RecyclerView recCat, recProduct;
    private List<Category> categoriesList;
    private List<Products.Product> productList;
    private ApiService myApiMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recCat = findViewById(R.id.recCat);
        recProduct = findViewById(R.id.recProduct);

        categoriesList = new ArrayList<>();
        productList = new ArrayList<>();

        fetchCategories();
    }

    private void fetchCategories() {
        RetrofitInstance.getInstance().apiInterface.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("response", "onResponse: " + response.isSuccessful());
                    categoriesList = response.body();
                    setupCategoryAdapter();
                } else {
                    // Handle error
                    Toast.makeText(RetroMAinActivity.this, "Failed to fetch categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Try fetching categories as a string if the previous attempt fails
                fetchCategoriesAsString();
            }
        });
    }


    private void fetchCategoriesAsString() {
        RetrofitInstance.getInstance().apiInterface.getCategoriesAsString().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Manually convert the string response to a list of categories
                    try {
                        JSONArray jsonArray = new JSONArray(response.body());
                        categoriesList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Category category = new Category();
                            category.setCategoryName(jsonArray.getString(i));
                            categoriesList.add(category);
                        }
                        setupCategoryAdapter();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        // Handle JSON parsing error
                        Toast.makeText(RetroMAinActivity.this, "Error parsing categories", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle error
                    Toast.makeText(RetroMAinActivity.this, "Failed to fetch categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Handle failure
                Toast.makeText(RetroMAinActivity.this, "Failed to fetch categories", Toast.LENGTH_SHORT).show();
            }
        });

    }
//    private void fetchProductsForCategory(String category) {
//        RetrofitInstance.getInstance().apiInterface.getProducts().enqueue(new Callback<List<Products.Product>>() {
//            @Override
//            public void onResponse(Call<List<Products.Product>> call, Response<List<Products.Product>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    productList = response.body();
//
//                    // Filter products by category
//                    List<Products.Product> filteredProducts = new ArrayList<>();
//                    for (Products.Product product : productList) {
//                        if (category.equals(product.getCategory())) {
//                            filteredProducts.add(product);
//                        }
//                    }
//
//                    setupProductAdapter(filteredProducts);
//                } else {
//                    // Handle error
//                    Toast.makeText(RetroMAinActivity.this, "Failed to fetch products", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Products.Product>> call, Throwable t) {
//                Toast.makeText(RetroMAinActivity.this, "Failed to fetch products", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

    private void setupCategoryAdapter() {
        recCat.setAdapter(new CategoryAdapter(RetroMAinActivity.this, categoriesList, new CategoryAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, String catName) {
//                fetchProductsForCategory(catName);
            }
        }));
    }

    private void setupProductAdapter(List<Products.Product> products) {
        recProduct.setAdapter(new ProductAdapter(RetroMAinActivity.this, products));
    }
}
