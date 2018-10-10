package com.ss.swiftsecure;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class EmergencyContactActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
    }

    public void contactAgency(View v) {
        switch (v.getId()){
            case R.id.call_police:
                callNow(EmergencyNumbers.POLICE);
                break;
            case R.id.call_fire_services:
                callNow(EmergencyNumbers.FIRE_SERVICES);
                break;
            case R.id.call_road_safty:
                callNow(EmergencyNumbers.ROAD_SAFTY);
                break;

        }
    }

    private void callNow(String contact) {
        Intent callAgent = new Intent(Intent.ACTION_CALL);
        callAgent.setData(Uri.parse("tel:" + Integer.parseInt(contact)));

        if (ActivityCompat.checkSelfPermission(EmergencyContactActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(EmergencyContactActivity.this, "Please grant the  permission to call", Toast.LENGTH_SHORT).show();
            requestPermission();
        } else {
            startActivity(callAgent);
        }
    }
        private void requestPermission() {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }

}
