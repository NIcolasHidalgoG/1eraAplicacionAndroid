package com.example.solemne_gf_nh_rq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class create_category extends AppCompatActivity {

    EditText txtName;
    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        txtName = findViewById(R.id.category_name);
        rq = Volley.newRequestQueue(this);
    }

    public void insert(View v) {
        String url="http://10.0.2.2:3000/api/categorias";
        JSONObject parametros = new JSONObject();
        try {
            parametros.put("categoria", txtName.getText().toString());
        } catch(JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.POST, url,
                parametros, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(create_category.this, "Agregado correctamente", Toast.LENGTH_LONG).show();
                txtName.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(create_category.this, "ERROR", Toast.LENGTH_LONG).show();
            }
        });
        rq.add(requerimiento);
    }

    public void back(View view){
        Intent miIntent = new Intent(create_category.this, MainActivity.class);
        startActivity(miIntent);
    }
}