package com.example.solemne_gf_nh_rq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateProduct extends AppCompatActivity {

    EditText txtName, txtPrice,txtSupplier, txtCat;
    Button btnSave;
    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        txtName = findViewById(R.id.product_name);
        txtPrice = findViewById(R.id.product_price);
        txtSupplier = findViewById(R.id.product_supp);
        txtCat = findViewById(R.id.product_cat);
        btnSave = findViewById(R.id.product_create);
        rq = Volley.newRequestQueue(this);
    }

    public void insert(View v) {
        String url="http://10.0.2.2:3000/api/productos";
        JSONObject parametros = new JSONObject();
        try {
            parametros.put("nombre", txtName.getText().toString());
            parametros.put("precio", txtPrice.getText().toString());
            parametros.put("proveedor", txtSupplier.getText().toString());
            parametros.put("categoria", txtCat.getText().toString());
        } catch(JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.POST, url,
                parametros, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(CreateProduct.this, "Agregado correctamente", Toast.LENGTH_LONG).show();
                txtName.setText("");
                txtPrice.setText("");
                txtSupplier.setText("");
                txtCat.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateProduct.this, "ERROR", Toast.LENGTH_LONG).show();
            }
        });
        rq.add(requerimiento);
    }

    public void back(View view){
        Intent miIntent = new Intent(CreateProduct.this, MainActivity.class);
        startActivity(miIntent);
    }

    public void cam(View view){
        Intent miIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(miIntent);
    }
}