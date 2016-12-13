package com.example.igx.problem1;

import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivity extends AppCompatActivity  implements SensorEventListener  {
    private SensorManager sm;
    private Sensor sensor_gravity;
    private Sensor sensor_accelerometer;
    private Sensor sensor_gyroscope;
    private Sensor sensor_linear_acceleration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor_gravity = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensor_accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor_gyroscope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensor_linear_acceleration = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        Button btn_getLocation = (Button) findViewById(R.id.btn_getLocation);
        Button btn_getSensors = (Button) findViewById(R.id.btn_getSensors);
        Button btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);

        final TextView text_selectedData = (TextView) findViewById(R.id.text_selectedData);
        final TextView text_selectedType = (TextView) findViewById(R.id.text_selectedType);
        final EditText edit_phoneNumber = (EditText) findViewById(R.id.edit_phoneNumber);

        final Intent Location_intent = new Intent();
        ComponentInfo Locationinfo = new ComponentInfo(GPS 정보);

        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "LOCATION 눌렸습니다.",Toast.LENGTH_LONG).show();
                text_selectedType.setText("LOCATION");
                Location_intent.setComponent(Locationinfo);
                Location_intent.putExtra("위치",//경도 )
                Location_intent.putExtra("위치2" , // 위도)
                         startActivityForResult(Location_intent,REQ_MENU);
            }
        });

        btn_getSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "SENSOR 눌렸습니다.",Toast.LENGTH_LONG).show();
                text_selectedType.setText("SENSOR");
                sm.getSensorList(4);

            }
        });

        btn_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"SMS 화면으로 갑니다.",Toast.LENGTH_LONG).show();
                startActivityForResult(inent);
            //메세지 창으로 넘어가는 앱 실행

            }
        });

    }
    protected void onResume(){
        super.onResume();
        sm.registerListener(this, sensor_gravity, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_linear_acceleration, SensorManager.SENSOR_DELAY_NORMAL);
    };
    protected void onPause(){
        super.onPause();
        sm.unregisterListener(this);
    };
    int dir_UP=0;
    int dir_DOWN=0;
    double acceleration;
    double gravity;
    int count=0;
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            acceleration =Math.sqrt(x*x+y*y+z*z);
        }
        if(event.sensor.getType()==Sensor.TYPE_GRAVITY){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            gravity =Math.sqrt(x*x+y*y+z*z);
        }
        if (acceleration - gravity>5){
            dir_UP = 1;
        }
        if(dir_UP ==1 && gravity - acceleration>5){
            dir_DOWN = 1;
        }
        if(dir_DOWN ==1){
            count++;
            ((TextView) findViewById(R.id.text_selectedData)).setText(count+" Steps!!!");

            dir_UP = 0;
            dir_DOWN = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
