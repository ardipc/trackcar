package com.ansyah.ardi.trackcar;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView lblRespon;

    ToggleButton btnLamp, btnEngine, btnDoor, btnAlarm;

    private Socket sc;
    {
        try{
            sc = IO.socket("https://trackcar.herokuapp.com/");
        }catch (URISyntaxException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sc.connect();

        lblRespon   = (TextView) findViewById(R.id.lblRespon);

        btnLamp = (ToggleButton) findViewById(R.id.tglLamp);
        btnLamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                JSONObject objLamp = new JSONObject();
                if(b){
                    try{
                        objLamp.put("msg", true);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    btnLamp.setTextOn("ON Lamp");
                }
                else{
                    try{
                        objLamp.put("msg", false);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    btnLamp.setTextOff("OFF Lamp");
                }
                sc.emit("statuslampu", objLamp);
                Log.d("statuslampu", Objects.toString(objLamp));
                lblRespon.setText("Lampu ON");
            }
        });

        btnEngine = (ToggleButton) findViewById(R.id.tglEngine);
        btnEngine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                JSONObject objEngine = new JSONObject();
                if(b){
                    try{
                        objEngine.put("msg", true);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    btnEngine.setTextOn("ON Engine");
                }
                else{
                    try{
                        objEngine.put("msg", false);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    btnEngine.setTextOff("OFF Engine");
                }
                sc.emit("statusengine", objEngine);
                Log.d("statusengine", Objects.toString(objEngine));
                lblRespon.setText("Engine ON");
            }
        });

        btnDoor = (ToggleButton) findViewById(R.id.tglDoor);
        btnDoor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                JSONObject objLamp = new JSONObject();
                if(b){
                    try{
                        objLamp.put("msg", true);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    btnDoor.setTextOn("ON Door");
                }
                else{
                    try{
                        objLamp.put("msg", false);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    btnDoor.setTextOff("OFF Door");
                }
                sc.emit("statusdoor", objLamp);
                Log.d("statusdoor", Objects.toString(objLamp));
                lblRespon.setText("Door ON");
            }
        });

        btnAlarm = (ToggleButton) findViewById(R.id.tglAlarm);
        btnAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                JSONObject objLamp = new JSONObject();
                if(b){
                    try{
                        objLamp.put("msg", true);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    btnAlarm.setTextOn("ON Alarm");
                }
                else{
                    try{
                        objLamp.put("msg", false);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    btnAlarm.setTextOff("OFF Alarm");
                }
                sc.emit("statusalarm", objLamp);
                Log.d("statusAlarm", Objects.toString(objLamp));
                lblRespon.setText("Alarm ON");
            }
        });
    }

}
