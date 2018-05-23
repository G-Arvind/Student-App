package com.example.arvind.svce;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String rno;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    CardView catcard, attcard, calccard, cgpacard, complaincard, ttcard;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Svce");
        setSupportActionBar(toolbar);
        rno = getIntent().getExtras().getString("rno");
        //  Toast.makeText(getApplicationContext(),rno,Toast.LENGTH_LONG).show();
        drawerLayout = (DrawerLayout) findViewById(R.id.draw);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        navigationView=(NavigationView)findViewById(R.id.nv);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        catcard = (CardView) findViewById(R.id.catcard);
        attcard = (CardView) findViewById(R.id.attcard);
        calccard = (CardView) findViewById(R.id.calccard);
        cgpacard = (CardView) findViewById(R.id.cgpacard);
        complaincard = (CardView) findViewById(R.id.complaincard);
        ttcard = (CardView) findViewById(R.id.ttcard);
        catcard.setOnClickListener(this);
        attcard.setOnClickListener(this);
        calccard.setOnClickListener(this);
        cgpacard.setOnClickListener(this);
        complaincard.setOnClickListener(this);
        ttcard.setOnClickListener(this);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case(R.id.myaccount):
                        Intent in = new Intent(getApplicationContext(),Profile.class);
                        in.putExtra("rno", rno);
                        startActivity(in);
                        break;
                    case(R.id.feedback):
                        Intent in1 = new Intent(getApplicationContext(),FeedBack.class);
                        startActivity(in1);
                        break;
                    case(R.id.logout):
                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.logout) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        if (item.getItemId() == R.id.feedback) {
            return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.catcard:
                Intent intent = new Intent(MainActivity.this, Cat.class);
                intent.putExtra("rno", rno);
                startActivity(intent);
                break;
            case R.id.attcard:
                Intent intent1 = new Intent(MainActivity.this, Attendance.class);
                intent1.putExtra("rno", rno);
                startActivity(intent1);
                break;
            case R.id.calccard:
                break;
            case R.id.cgpacard:
                break;
            case R.id.complaincard:
                Intent intent2 = new Intent(MainActivity.this, Complain.class);
                intent2.putExtra("rno", rno);
                startActivity(intent2);
                break;
            case R.id.ttcard:
                Intent intent3 = new Intent(MainActivity.this, TimeTable.class);
                intent3.putExtra("rno", rno);
                startActivity(intent3);
                break;
        }
    }
}

