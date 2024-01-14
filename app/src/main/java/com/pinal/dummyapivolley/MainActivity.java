package com.pinal.dummyapivolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pinal.dummyapivolley.Model.Category;
import com.pinal.dummyapivolley.Model.Products;
import com.pinal.dummyapivolley.adapter.CategoryAdapter;
import com.pinal.dummyapivolley.adapter.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String URl = "https://dummyjson.com/products/categories";
    private List<Category> categoriesList = new ArrayList<>();
    private List<Products.Product> productList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();
    RecyclerView recCat,recProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recCat = findViewById(R.id.recCat);
        recProduct = findViewById(R.id.recProduct);
        CatgoryData();
    }

    public void CatgoryData() {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        String category = jsonArray.getString(i);
                        Category category1 = new Category();
                        category1.setCategoryName(category);
                        categoriesList.add(category1);

                    }
                    fetchProductsForCategory("smartphones");
                    recCat.setAdapter(new CategoryAdapter(MainActivity.this, categoriesList, new CategoryAdapter.onItemClickListener() {
                        @Override
                        public void onItemClick(int position, String catName) {
                            fetchProductsForCategory(catName);
                        }
                    }));
                    Log.e("categoriesList", "CatgoryData: " + String.valueOf(categoriesList.size()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection...", Toast.LENGTH_SHORT).show();
            }
        });

        MyRequestQueue.add(stringRequest);
    }

    private void fetchProductsForCategory(String category) {
        String baseUrl = "https://dummyjson.com/products";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray productsArray = obj.getJSONArray("products");
                    List<Products.Product> productList = new ArrayList<>();

                    for (int i = 0; i < productsArray.length(); i++) {
                        JSONObject jsonObject = productsArray.getJSONObject(i);

                        String productCategory = jsonObject.getString("category");
                        if (category.equals(productCategory)) {
                            Products.Product product = new Products.Product();
                            product.title = jsonObject.getString("title");
                            product.description = jsonObject.getString("description");
                            product.brand = jsonObject.getString("brand");
                            product.category = productCategory; // Use the retrieved category
                            product.thumbnail = jsonObject.getString("thumbnail");
                            product.price = jsonObject.getInt("price");
                            product.discountPercentage = jsonObject.getDouble("discountPercentage");
                            product.rating = jsonObject.getDouble("rating");
                            product.stock = jsonObject.getInt("stock");
                            product.images = new ArrayList<>();

                            JSONArray imagesArray = jsonObject.getJSONArray("images");
                            for (int j = 0; j < imagesArray.length(); j++) {
                                String imageStr = imagesArray.getString(j);
                                product.images.add(imageStr);
                            }

                            productList.add(product);
                        }
                    }

                    Log.e("categoriesList", "CatgoryData: product" + String.valueOf(productList.size()));
                    recProduct.setAdapter(new ProductAdapter(MainActivity.this, productList));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("categoriesList", "CatgoryData: error" + error.getMessage());
                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection...", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }

}