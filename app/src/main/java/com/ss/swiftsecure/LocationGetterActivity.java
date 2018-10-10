package com.ss.swiftsecure;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.util.Log;

import org.ankit.gpslibrary.MyTracker;

public class LocationGetterActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission, Manifest.permission.READ_PHONE_STATE},
                        REQUEST_CODE_PERMISSION);
            } else {
                //read location
                getLocation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    System.out.println("permission denied!");
                }
                break;
        }
    }

    void getLocation() {
        MyTracker tracker = new MyTracker(this);
        Log.d("LATITUDE",""+tracker.getLatitude());
        Log.d("LOGITUDE",""+tracker.getLongitude());
        Log.d("LOCATION",""+tracker.getLocation());
        Log.d("ADDRESS",tracker.address);
        Log.d("CITY_NAME",tracker.cityName);
        Log.d("STATE",tracker.state);
        Log.d("COUNTRY",tracker.countryName);
        Log.d("CONTRY_CODE",tracker.countryCode);
        Log.d("IP_ADDRESS",tracker.ipAddress);
        Log.d("MAC_ADDRESS",tracker.macAddress);
    }
}