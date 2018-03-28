package com.ansyah.ardi.trackcar;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button btnOn, btnOff;
    TextView lblRespon;

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

        btnOn       = (Button) findViewById(R.id.btnON);
        btnOff      = (Button) findViewById(R.id.btnOFF);
        lblRespon   = (TextView) findViewById(R.id.lblRespon);

        btnOn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                JSONObject obj1 = new JSONObject();
                try{
                    obj1.put("msg", 1);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                sc.emit("statuslampu", obj1);
                Log.d("statuslampu", Objects.toString(obj1));
                lblRespon.setText("Lampu ON");
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                JSONObject obj2 = new JSONObject();
                try{
                    obj2.put("msg", 0);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                sc.emit("statuslampu", obj2);
                Log.d("statuslampu", Objects.toString(obj2));
                lblRespon.setText("Lampu OFF");
            }
        });
    }
}
