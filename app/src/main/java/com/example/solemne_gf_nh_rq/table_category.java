package com.example.solemne_gf_nh_rq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class table_category extends AppCompatActivity {


    Button btnView;
    ArrayList<String> listaCategoria = new ArrayList<>();
    ListView lstProducto;
    ArrayAdapter<String> adaptador;

    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_category);
        btnView = findViewById(R.id.btnView);
        rq = Volley.newRequestQueue(this);
        lstProducto = findViewById(R.id.seller_table);
        crearListaCategoria();

    }

    public void crearListaCategoria(){
        String url = "http://10.0.2.2:3000/api/categorias";
        JsonArrayRequest requerimiento = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int f=0; f<response.length(); f++){
                    try {
                        JSONObject objeto = new JSONObject(response.get(f).toString());
                        String nombreCategoria = objeto.getString("categoria");
                        listaCategoria.add(nombreCategoria);
                        sleccionarCategoria_Click(f);
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
        adaptador = new ArrayAdapter<String>(table_category.this, android.R.layout.simple_expandable_list_item_1, listaCategoria);
        lstProducto.setAdapter(adaptador);
    }

    private void sleccionarCategoria_Click(Integer id){
        lstProducto.setSelection(0);
        lstProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent miIntent = new Intent(table_category.this, detail_category.class);
                miIntent.putExtra("idItem", i+1);
                startActivity(miIntent);
                //Toast.makeText(getApplicationContext(), "ID= "+ String.valueOf(i+1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back(View view){
        Intent miIntent = new Intent(table_category.this, MainActivity.class);
        startActivity(miIntent);
    }
}