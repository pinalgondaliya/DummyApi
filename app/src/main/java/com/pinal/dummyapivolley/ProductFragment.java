package com.pinal.dummyapivolley;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinal.dummyapivolley.Model.Products;
import com.pinal.dummyapivolley.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    private String categoryName;

    private List<Products.Product> productList;

    public ProductFragment(String categoryName, List<Products.Product> productList) {
        this.categoryName = categoryName;
        this.productList = productList;
    }


    public ProductFragment() {
        // Required empty public constructor
    }


    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

//        List<Products.Product> filteredProducts = new ArrayList<>();
//        for (Products.Product product : productList) {
//            if (categoryName.equals(product.category)) {
//                filteredProducts.add(product);
//            }
//        }

        // Set up the RecyclerView with the filtered products
        ProductAdapter productAdapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(productAdapter);

        return view;
    }
}