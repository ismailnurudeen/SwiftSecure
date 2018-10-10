package com.ss.swiftsecure;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class CrimeReportActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addReportIntent=new Intent(CrimeReportActivity.this,AddReportActivity.class);
            }
        });
        mRecyclerView = findViewById(R.id.crime_report_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CrimeReportRecyclerAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

// Code to Add an item with default animation
//((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);
// Code to remove an item with default animation
//((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ((CrimeReportRecyclerAdapter) mAdapter).setOnItemClickListener(new CrimeReportRecyclerAdapter.MyClickListener(){

            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }
    private ArrayList<CrimeReports> getDataSet() {
        ArrayList results = new ArrayList<CrimeReports>();
        for (int index = 0; index < 20; index++) {
            Bitmap nullBitmap=null; //TODO:Change this to the image of report;
            CrimeReports obj = new CrimeReports("John Kelvin","Nigeria Police Force" ,"I.C.E Benin City",nullBitmap ,"Tue,9:50 PM");;
            results.add(index, obj);
        }
        return results;
    }

}
