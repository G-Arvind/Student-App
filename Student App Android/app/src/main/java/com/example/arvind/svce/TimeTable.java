package com.example.arvind.svce;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class TimeTable extends AppCompatActivity {

    Toolbar toolbar;

    Dialog myDialog;

    String url,rno;

    ArrayList<String> list1=new ArrayList<String>(0);
    ArrayList<String>list2=new ArrayList<String>(0);
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Time Table");
        setSupportActionBar(toolbar);
        rno=getIntent().getExtras().getString("rno");
        myDialog = new Dialog(this);

        String[] tt={"Monday","Tuesday","Wednesday","Thursday","Friday"};

        ListView list=(ListView)findViewById(R.id.list);

        ListAdapter listAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tt);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String val=String.valueOf(adapterView.getItemAtPosition(i));
                if(val.equals("Monday"))
                {
                    set("mon");
                }
                else if(val.equals("Tuesday"))
                {
                    set("tue");

                }
                else if(val.equals("Wednesday"))
                {
                    set("wed");
                }
                else if(val.equals("Thursday"))
                {
                    set("thu");
                }
                else if(val.equals("Friday"))
                {
                    set("fri");
                }
            }
        });
    }



    public void set(String day){


        url="http://192.168.1.2:8080/time/"+rno+"/"+day;


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("RESPONE",response);
                try {
                    //   JSONArray array=new JSONArray(response);
                    JSONObject json=new JSONObject(response).getJSONObject("result");
                    Iterator<String> iter = json.keys();
                    while (iter.hasNext()) {
                        list1.add(iter.next());
                        Log.v("RESPONE",list1.get(i));
                        //  String key = iter.next();
                        // Log.v("RESPONSE1111",key);
                        try {
                            list2.add(json.get(list1.get(i)).toString());
                            Log.v("RESPONE",list2.get(i));
                        } catch (JSONException e) {
                            // Something went wrong!
                        }
                        i++;
                    }
                    //  setval();
                    //   Log.v("RESPONSE::", i+"");
                    ShowPopup();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);




    }

    public void ShowPopup() {
        TextView txtclose,h1,h2,h3,h4,h5,h6,h7;
        myDialog.setContentView(R.layout.popup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        h1 =(TextView) myDialog.findViewById(R.id.h1);
        h2 =(TextView) myDialog.findViewById(R.id.h2);
        h3 =(TextView) myDialog.findViewById(R.id.h3);
        h4 =(TextView) myDialog.findViewById(R.id.h4);
        h5 =(TextView) myDialog.findViewById(R.id.h5);
        h6 =(TextView) myDialog.findViewById(R.id.h6);
        h7 =(TextView) myDialog.findViewById(R.id.h7);

        h1.setText("Hour 1: "+list2.get(0));
        h2.setText("Hour 2: "+list2.get(1));
        h3.setText("Hour 3: "+list2.get(2));
        h4.setText("Hour 4: "+list2.get(3));
        h5.setText("Hour 5: "+list2.get(4));
        h6.setText("Hour 6: "+list2.get(5));
        h7.setText("Hour 7: "+list2.get(6));


        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
      //  myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
