package com.example.solemne_gf_nh_rq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toProduct(View view){
        Intent miIntent = new Intent(MainActivity.this, CreateProduct.class);
        startActivity(miIntent);
    }
    public void toCategory(View view){
        Intent miIntent = new Intent(MainActivity.this, create_category.class);
        startActivity(miIntent);
    }
    public void toSupplier(View view){
        Intent miIntent = new Intent(MainActivity.this, create_supplier.class);
        startActivity(miIntent);
    }
}