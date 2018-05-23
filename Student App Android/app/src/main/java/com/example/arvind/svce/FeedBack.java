package com.example.arvind.svce;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import static com.example.arvind.svce.R.id.comment;
import static com.example.arvind.svce.R.id.toolbar;

public class FeedBack extends AppCompatActivity {

    Toolbar toolbar;
    RatingBar rbar;
    Button subfb;
    EditText comment,error,feature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        toolbar=(Toolbar)findViewById(R.id.toolbarfb);
        toolbar.setTitle("Feedback");
        setSupportActionBar(toolbar);
        rbar=(RatingBar)findViewById(R.id.rbar);
        subfb=(Button)findViewById(R.id.subfb);
        comment=(EditText)findViewById(R.id.comment);
        error=(EditText)findViewById(R.id.error);
        feature=(EditText)findViewById(R.id.feature);

        subfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(comment.getText().toString().length()==0) {
                    comment.setError("comment is required");
                }
                else
                {
                    sendEmail();
                }

            }
        });
    }
    private void sendEmail() {


        String email="shortfiapp@gmail.com";
        String commentval = comment.getText().toString().trim();
        Float rv=rbar.getRating();
        String ratingval = rv.toString();
        String featval= feature.getText().toString().trim()+" end";
        String errval= error.getText().toString().trim()+" end";
            SendMail sm = new SendMail(this, email, commentval, ratingval, featval, errval);
            sm.execute();
    }

}
