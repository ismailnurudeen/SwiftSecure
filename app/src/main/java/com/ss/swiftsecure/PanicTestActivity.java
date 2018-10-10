package com.ss.swiftsecure;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PanicTestActivity extends AppCompatActivity {
TextView test_tv;
int sequence_count=0;
int[] sequence=new int[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic_test);
        test_tv=findViewById(R.id.panic_test_tv);
      sequence[0]= KeyEvent.KEYCODE_VOLUME_DOWN;
      sequence[1]=KeyEvent.KEYCODE_VOLUME_DOWN;
      sequence[2]=KeyEvent.KEYCODE_VOLUME_UP;
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        ArrayList<Integer> seqArray=new ArrayList<>();
        //If volume down key
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if(seqArray.size()<3)
                seqArray.add(keyCode);
            Toast.makeText(this,"button down",Toast.LENGTH_SHORT).show();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            if(seqArray.size()<3)
                seqArray.add(keyCode);
            Toast.makeText(this,"button up",Toast.LENGTH_SHORT).show();
            return true;
        }
        for(int i=0;i<sequence.length;i++){
            if(sequence[i] == seqArray.get(i)){
                sequence_count++;
            }else {
                sequence_count=0;
                break;
            }
        }
        if(sequence_count==3)
            showPanicText();
        return true;
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_UP) {
                    Toast.makeText(this, "UP", Toast.LENGTH_SHORT).show();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    Toast.makeText(this, "DOWN", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    private void showPanicText() {
        test_tv.setText("Help is On the way");
    }

}
