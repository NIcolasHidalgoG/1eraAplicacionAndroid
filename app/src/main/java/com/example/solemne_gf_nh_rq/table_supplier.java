package com.example.solemne_gf_nh_rq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class table_supplier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_supplier);
    }
    public void back(View view){
        Intent miIntent = new Intent(table_supplier.this, MainActivity.class);
        startActivity(miIntent);
    }
}