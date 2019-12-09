package com.example.jsonobjectvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView mtv_result;
    Button btn_send;
    RequestQueue mQueue;
    ArrayList<Employee> data;
    ListView lv;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mtv_result = findViewById(R.id.mtv_result);
        btn_send = findViewById(R.id.btn_send);
        lv = findViewById(R.id.lv);
        mQueue = Volley.newRequestQueue(this);

        data = new ArrayList<>();


        jsonParse();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
    private void jsonParse () {
        String URL = "https://api.myjson.com/bins/kp9wz";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("employees");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject employee = jsonArray.getJSONObject(i);
                        String firstName = employee.getString("firstname");
                        int age = employee.getInt("age");
                        String mail = employee.getString("mail");
                        data.add(new Employee(firstName, age,mail));
                        //mtv_result.append(data + " \n \n ");
                        adapter = new EmployeeAdapter(MainActivity.this, data);
                        lv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }
}
