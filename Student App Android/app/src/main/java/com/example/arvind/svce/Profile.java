package com.example.arvind.svce;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class Profile extends AppCompatActivity {

    TextView profname,profdep,profyr,profsec,profnum;
    Button profexit;
    String rno,url;
    String prno,pname,psec,pyr,pdep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        rno=getIntent().getExtras().getString("rno");
        url="http://192.168.1.2:8080/stud/"+rno;
        profname=(TextView) findViewById(R.id.profname);
        profdep=(TextView) findViewById(R.id.profdep);
        profyr=(TextView) findViewById(R.id.profyr);
        profsec=(TextView) findViewById(R.id.profsec);
        profnum=(TextView) findViewById(R.id.profnum);
        profexit=(Button)findViewById(R.id.profexit);

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

                    prno=json.get("rno").toString();
                    psec=json.get("sec").toString();
                    pdep=json.get("dep").toString();
                    pyr=json.get("yr").toString();
                    pname=json.get("name").toString();

                    //Toast.makeText(getApplicationContext(),json.get("rno").toString(),Toast.LENGTH_LONG).show();

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

     profexit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             final AlertDialog.Builder builder=new AlertDialog.Builder(Profile.this);
             builder.setTitle("Exit");
             builder.setMessage("Are you sure ?");
             builder.setCancelable(true);

             builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.dismiss();
                 }
             });
             builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     finish();
                 }
             });

             builder.show();
         }
     });


    }

    void setval()
    {
        profname.setText(pname);
        profdep.setText(pdep.toUpperCase());
        profyr.setText(pyr);
        profnum.setText(prno);
        profsec.setText(psec.toUpperCase());
    }


}
