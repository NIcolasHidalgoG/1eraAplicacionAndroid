package com.example.solemne_gf_nh_rq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class detail_product extends AppCompatActivity {

    TextView textView2, textView3;
    Bundle bundle;
    Integer id;

    private RequestQueue rq;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        bundle = getIntent().getExtras();
        id = bundle.getInt("idItem");
        //Toast.makeText(this, "HOLAAAA= "+ id, Toast.LENGTH_SHORT).show();
        rq = Volley.newRequestQueue(this);
        detalleProducto();
    }

    public void detalleProducto() {
        textView2.setText("");
        String idPro = id.toString();
        String url = "http://10.0.2.2:3000/api/productos/" + idPro;
        JsonArrayRequest requerimiento = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() == 1){
                            try {
                                JSONObject objeto = new JSONObject(response.get(0).toString());
                                textView2.setText(objeto.getString("nombre"));
                                textView3.setText(objeto.getString("precio"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(requerimiento);
    }


    public void back(View view){
        Intent miIntent = new Intent(detail_product.this, table_product.class);
        startActivity(miIntent);
    }
}