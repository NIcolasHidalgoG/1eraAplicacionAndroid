package com.example.solemne_gf_nh_rq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class detail_category extends AppCompatActivity {

    TextView textView6;
    Bundle bundle;
    Integer id;
    Button delete_category;

    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        textView6 = findViewById(R.id.textView6);
        delete_category = findViewById(R.id.delete_category);

        bundle = getIntent().getExtras();
        id = bundle.getInt("idItem");
        //Toast.makeText(this, "HOLAAAA= "+ id, Toast.LENGTH_SHORT).show();
        rq = Volley.newRequestQueue(this);
        detalleCategoria();
    }

    public void detalleCategoria() {
        textView6.setText("");
        String idCat = id.toString();
        String url = "http://10.0.2.2:3000/api/categorias/" + idCat;
        JsonArrayRequest requerimiento = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() == 1){
                            try {
                                JSONObject objeto = new JSONObject(response.get(0).toString());
                                textView6.setText(objeto.getString("categoria"));
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
    public void eliminarCategoria(View view){
        String idCat = id.toString();
        String url = "http://10.0.2.2:3000/api/categorias/" + idCat;
        JSONObject parametros = new JSONObject();
        try {
            parametros.put("id_categoria", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.DELETE, url, parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String resu=response.get("id_categoria").toString();
                            if (resu.equals("1")){
                                Toast.makeText(detail_category.this, "Se eliminó el categoria", Toast.LENGTH_SHORT).show();

                            } else
                                Toast.makeText(detail_category.this, "No existe codigo", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        Intent miIntent = new Intent(detail_category.this, table_category.class);
        startActivity(miIntent);
    }
}