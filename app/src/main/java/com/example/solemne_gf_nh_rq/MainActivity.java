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

    public void toCreateProduct(View view){
        Intent miIntent = new Intent(MainActivity.this, CreateProduct.class);
        startActivity(miIntent);
    }
    public void toCreateCategory(View view){
        Intent miIntent = new Intent(MainActivity.this, create_category.class);
        startActivity(miIntent);
    }
    public void toCreateSupplier(View view){
        Intent miIntent = new Intent(MainActivity.this, create_supplier.class);
        startActivity(miIntent);
    }
    public void toTableProduct(View view){
        Intent miIntent = new Intent(MainActivity.this, table_product.class);
        startActivity(miIntent);
    }
    public void toTableCategory(View view){
        Intent miIntent = new Intent(MainActivity.this, table_category.class);
        startActivity(miIntent);
    }
    public void toTableSupplier(View view){
        Intent miIntent = new Intent(MainActivity.this, table_supplier.class);
        startActivity(miIntent);
    }
}