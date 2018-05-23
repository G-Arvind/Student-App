package com.example.arvind.svce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Login extends AppCompatActivity {

    EditText regno,pass;
    Button login;
    String rno;

//    String Url;
String responseString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regno=(EditText)findViewById(R.id.regno);
        pass=(EditText)findViewById(R.id.pass);
        login=(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rno=regno.getText().toString().trim();
                String passwo=pass.getText().toString().trim();
             //   Url="127.0.0.1:8080/stud/"+rno+"/"+passwo;

                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                    String URL = "http://192.168.1.2:8080/stud";
                    final JSONObject jsonBody = new JSONObject();
                    jsonBody.put("rno", rno);
                    jsonBody.put("pass", passwo);
                    final String requestBody = jsonBody.toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            // Toast.makeText(Login.this,"message1"+response,Toast.LENGTH_LONG).show();
                            if(response.equals("201")){
                                Intent intent=new Intent(Login.this,MainActivity.class);
                                intent.putExtra("rno",rno);
                                startActivity(intent);
                                finish();
                            }

                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.e("VOLLEY", error.toString());
                            Login.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();
                                    //  Toast.makeText(Login.this, "Hello", Toast.LENGTH_SHORT).show();
                                    // Toast.makeText(Login.this,"message"+error,Toast.LENGTH_LONG).show();
                                }
                            });

                        }

                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return requestBody == null ? null : requestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                                return null;
                            }
                        }

                        @Override
                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
                            responseString = "";
                            if (response != null) {
                                responseString = String.valueOf(response.statusCode);
                                Login.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        //  Toast.makeText(Login.this, "Hello", Toast.LENGTH_SHORT).show();
                                        //        Toast.makeText(Login.this,"message"+responseString,Toast.LENGTH_LONG).show();
                                    }
                                });

                                // can get more details such as response.headers
                            }
//                            Toast.makeText(Login.this,"Success",Toast.LENGTH_LONG).show();
                            return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));

                        }
                    };

                    requestQueue.add(stringRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}

