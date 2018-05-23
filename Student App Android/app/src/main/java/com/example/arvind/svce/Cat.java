package com.example.arvind.svce;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.marcinmoskala.arcseekbar.ArcSeekBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Cat extends AppCompatActivity {

    String rno,url;
    int i=0;

    ArrayList<String>list1=new ArrayList<String>(0);
    ArrayList<String>list2=new ArrayList<String>(0);

    ArcSeekBar sub1,sub2,sub3,sub4,sub5,sub6;
    TextView mark1,mark2,mark3,mark4,mark5,mark6;
    TextView marknum1,marknum2,marknum3,marknum4,marknum5,marknum6;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("CAT");
        setSupportActionBar(toolbar);
        rno=getIntent().getExtras().getString("rno");
        url="http://192.168.1.2:8080/cat/"+rno;

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("RESPONE",response);
                progressDialog.dismiss();
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
                    setval();
                 //   Log.v("RESPONSE::", i+"");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);




    }

    void setval()
    {
        sub1=(ArcSeekBar)findViewById(R.id.sub1);
        sub2=(ArcSeekBar)findViewById(R.id.sub2);
        sub3=(ArcSeekBar)findViewById(R.id.sub3);
        sub4=(ArcSeekBar)findViewById(R.id.sub4);
        sub5=(ArcSeekBar)findViewById(R.id.sub5);
        sub6=(ArcSeekBar)findViewById(R.id.sub6);

        mark1=(TextView)findViewById(R.id.mark1);
        mark2=(TextView)findViewById(R.id.mark2);
        mark3=(TextView)findViewById(R.id.mark3);
        mark4=(TextView)findViewById(R.id.mark4);
        mark5=(TextView)findViewById(R.id.mark5);
        mark6=(TextView)findViewById(R.id.mark6);

        marknum1=(TextView)findViewById(R.id.marknum1);
        marknum2=(TextView)findViewById(R.id.marknum2);
        marknum3=(TextView)findViewById(R.id.marknum3);
        marknum4=(TextView)findViewById(R.id.marknum4);
        marknum5=(TextView)findViewById(R.id.marknum5);
        marknum6=(TextView)findViewById(R.id.marknum6);

        sub1.setEnabled(false);
        sub2.setEnabled(false);
        sub3.setEnabled(false);
        sub4.setEnabled(false);
        sub5.setEnabled(false);
        sub6.setEnabled(false);

        sub1.setProgressColor(Color.parseColor("#26D864"));
        sub1.setProgressBackgroundColor(Color.RED);
        sub2.setProgressColor(Color.parseColor("#26D864"));
        sub2.setProgressBackgroundColor(Color.RED);
        sub3.setProgressColor(Color.parseColor("#26D864"));
        sub3.setProgressBackgroundColor(Color.RED);
        sub4.setProgressColor(Color.parseColor("#26D864"));
        sub4.setProgressBackgroundColor(Color.RED);
        sub5.setProgressColor(Color.parseColor("#26D864"));
        sub5.setProgressBackgroundColor(Color.RED);
        sub6.setProgressColor(Color.parseColor("#26D864"));
        sub6.setProgressBackgroundColor(Color.RED);


        sub1.setProgress(Integer.parseInt(list2.get(0))*2);
        sub2.setProgress(Integer.parseInt(list2.get(2))*2);
        sub3.setProgress(Integer.parseInt(list2.get(4))*2);
        sub4.setProgress(Integer.parseInt(list2.get(6))*2);
        sub5.setProgress(Integer.parseInt(list2.get(8))*2);
        sub6.setProgress(Integer.parseInt(list2.get(10))*2);


        StringBuilder sb = new StringBuilder(list1.get(0));
        sb.deleteCharAt(6);
        mark1.setText(sb.toString());
        StringBuilder sb1 = new StringBuilder(list1.get(1));
        sb1.deleteCharAt(6);
        mark2.setText(sb1.toString());
        StringBuilder sb2 = new StringBuilder(list1.get(2));
        sb2.deleteCharAt(6);
        mark3.setText(sb2.toString());
        StringBuilder sb3 = new StringBuilder(list1.get(3));
        sb3.deleteCharAt(6);
        mark4.setText(sb3.toString());
        StringBuilder sb4 = new StringBuilder(list1.get(4));
        sb4.deleteCharAt(6);
        mark5.setText(sb4.toString());
        StringBuilder sb5 = new StringBuilder(list1.get(5));
        sb5.deleteCharAt(6);
        mark6.setText(sb5.toString());

        marknum1.setText(list2.get(0)+"/"+list2.get(1));
        marknum2.setText(list2.get(2)+"/"+list2.get(3));
        marknum3.setText(list2.get(4)+"/"+list2.get(5));
        marknum4.setText(list2.get(6)+"/"+list2.get(7));
        marknum5.setText(list2.get(8)+"/"+list2.get(9));
        marknum6.setText(list2.get(10)+"/"+list2.get(11));
    }
}
