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

public class detail_trolley extends AppCompatActivity {

    TextView textView15, textView16;
    Bundle bundle;
    Integer id;
    Button delete_trolley;

    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trolley);

        textView15 = findViewById(R.id.textView15);
        textView16 = findViewById(R.id.textView16);
        delete_trolley = findViewById(R.id.delete_trolley);

        bundle = getIntent().getExtras();
        id = bundle.getInt("idItem");
        //Toast.makeText(this, "HOLAAAA= "+ id, Toast.LENGTH_SHORT).show();
        rq = Volley.newRequestQueue(this);
        detalleCarrito();
    }

    public void detalleCarrito() {
        textView15.setText("");
        String idVen = id.toString();
        String url = "http://10.0.2.2:3000/api/carrito/productos-precio/" + idVen;
        JsonArrayRequest requerimiento = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() == 1){
                            try {
                                JSONObject objeto = new JSONObject(response.get(0).toString());
                                textView15.setText(objeto.getString("nombre"));
                                textView16.setText(objeto.getString("precio"));
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

    public void eliminarCarrito(View view){
        String idVen = id.toString();
        String url = "http://10.0.2.2:3000/api/carrito/" + idVen;
        JSONObject parametros = new JSONObject();
        try {
            parametros.put("id_carrito", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "ALO= "+ parametros, Toast.LENGTH_SHORT).show();
        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.DELETE, url, parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String resu=response.get("id_carrito").toString();
                            if (resu.equals("1")){
                                Toast.makeText(detail_trolley.this, "Se eliminó el producto", Toast.LENGTH_SHORT).show();

                            } else
                                Toast.makeText(detail_trolley.this, "No existe codigo", Toast.LENGTH_SHORT).show();
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
        Intent miIntent = new Intent(detail_trolley.this, table_trolley.class);
        startActivity(miIntent);
    }
}
