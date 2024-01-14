package com.pinal.dummyapivolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pinal.dummyapivolley.adapter.DisplayImageAdapter;

import java.util.ArrayList;

public class DisplayImageActivity extends AppCompatActivity {
    RecyclerView recgrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        recgrid = findViewById(R.id.recgrid);

        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");


        recgrid.setAdapter(new DisplayImageAdapter(DisplayImageActivity.this,myList));

    }
}