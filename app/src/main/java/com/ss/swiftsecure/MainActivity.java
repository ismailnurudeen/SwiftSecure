package com.ss.swiftsecure;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {
    private static final int SMS_RQ_CODE = 101;
    private RippleView mReportButton, mEmergencyButton, mCrimeButton, mDangerButton;
    private ImageButton mReport, mEmergency, mCrime, mDanger,mSecurityAlertBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mReportButton = findViewById(R.id.report_button);
        mEmergencyButton = findViewById(R.id.emergency_button);
        mCrimeButton = findViewById(R.id.crime_button);
        mDangerButton = findViewById(R.id.danger_zone_button);
        mReport = findViewById(R.id.report);
        mEmergency = findViewById(R.id.emergency);
        mCrime = findViewById(R.id.crime);
        mDanger = findViewById(R.id.danger_zone);
        mSecurityAlertBtn=findViewById(R.id.security_alert);
        Animation animation = new AlphaAnimation(1, 0.5f);
        animation.setDuration(1000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        mSecurityAlertBtn.startAnimation(animation);
        mSecurityAlertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertSecurity();
            }
        });
        mReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent reportIntent=new Intent(MainActivity.this,ReportToAuthorityActivity.class);
            startActivity(reportIntent);
            }
        });
        mEmergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emergencyIntent=new Intent(MainActivity.this,EmergencyContactActivity.class);
                startActivity(emergencyIntent);
            }
        });
        mCrimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crimeIntent=new Intent(MainActivity.this,CrimeReportActivity.class);
                startActivity(crimeIntent);
            }
        });
        mDangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangerIntent=new Intent(MainActivity.this,DangerZoneActivity.class);
                startActivity(dangerIntent);
                Toast.makeText(getApplicationContext(),"Danger Zones not Currently Available",Toast.LENGTH_LONG).show();
            }
        });
    }
int alertType=0;
  private void alertSecurity() {
if(alertType==0){
    emailAgent("police@ng.com","Help");
}else if(alertType==1) {
    sendSMS(EmergencyNumbers.POLICE,"I am in trouble");
 }
}
    private void emailAgent(String email,String msg) {
//        Intent emailAgent = new Intent(Intent.ACTION_SEND);
//        emailAgent.setData(Uri.parse("mailto:"));
//        emailAgent.setType("text/plain");
//        emailAgent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
//        emailAgent.putExtra(Intent.EXTRA_SUBJECT, "I Need Help");
//        startActivity(Intent.createChooser(emailAgent, "Send an Email"));

        BackgroundMail.newBuilder(this)
                .withUsername(email)
                .withPassword("password12345")
                .withMailto("ibrightstar247@gmail.com")
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("I Need Help")
                .withBody(msg)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        //do some magic
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                      Log.d("BG_EMAIL","SENT...");
                    }
                })
                .send();
    }
    private void sendSMS(String phoneNumber,String msg) {
        requestPermission();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, msg, null, null);
//        Intent smsIntent=new Intent(Intent.ACTION_VIEW);
//        smsIntent.setData(Uri.parse("smsto:"));
//        smsIntent.putExtra("address",phoneNumber);
//        smsIntent.putExtra("sms_body",msg);
//        smsIntent.setType("vnd.android-dir/mms-sms");
//        try {
//            startActivity(smsIntent);
//        }catch (android.content.ActivityNotFoundException anf){
//            Toast.makeText(getApplicationContext(),"SMS failed,Please Try Again",Toast.LENGTH_SHORT).show();;
//        }
      }
        private void requestPermission() {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)){

                }else {
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SMS_RQ_CODE);
                }
            }
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == SMS_RQ_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.screen_lock_switch:
                startActivity(new Intent(MainActivity.this,LocationGetterActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void listenForPanic() {

    }
}
