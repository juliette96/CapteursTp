package com.example.demasj.capteurstp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private List interList;
    private Sensor accelerometre;
    private TextView axeX;
    private TextView axeY;
    private TextView axeZ;
    private double var1;
    private Float var2;
    private Float diff;
    private View back;
    private TextView blabla;
    private TextView direction;
    private TextView direction2;
    double x;
    double y;
    double z;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView vue = findViewById(R.id.vuue);
        ListView liste = findViewById(R.id.myList);
        axeX = findViewById(R.id.axeX);
        axeY = findViewById(R.id.axeY);
        axeZ = findViewById(R.id.axeZ);
        blabla = findViewById(R.id.blabla);
        direction = findViewById(R.id.direction);
        direction2 = findViewById(R.id.direction2);
        back = findViewById(R.id.background);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        interList = new ArrayList();

        for(int i = 0; i < deviceSensors.size(); i++) {
            System.out.println("Élément à l'index " + i + " = " + deviceSensors.get(i).getName());
            interList.add(deviceSensors.get(i).getName());
        }

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, interList);
        liste.setAdapter(adapter);*/

        ArrayAdapter<Sensor> adapter = new ArrayAdapter<Sensor>(MainActivity.this, android.R.layout.simple_list_item_1, deviceSensors);
        liste.setAdapter(adapter);

        accelerometre  = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometre != null) {
            vue.setText("IL Y A UN ACCELEROMETRE");
        } else {
            vue.setText("IL N'Y A PAS D'ACCELEROMETRE");
        }

        mSensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_UI);
        var2 = new Float(0);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        axeX.setText(""+event.values[0]);
        axeY.setText(""+event.values[1]);
        axeZ.setText(""+event.values[2]);

        double valx = x;
        double valz = z;

        double x = event.values[0];
        double y = event.values[1];
        double z = event.values[2];



        double xx = x*x;
        double yy = y*y;
        double zz = z*z;

        var1 = xx+yy+zz;

        blabla.setText(""+var1);

        if (var1 < 150) {
            back.setBackgroundColor(0xffA3CB38); //vert
        } else if (var1 <300) {
            back.setBackgroundColor(0xffF79F1F); //rouge
        } else {
            back.setBackgroundColor(0xffEA2027); //orange
        }

        if ( valx < x ) {
            direction.setText("GAUCHE");
        } else if ( valx > x ) {
            direction.setText("DROITE");
        }

        if ( valz < z ) {
            direction2.setText("HAUT");
        } else if ( valz > z ) {
            direction2.setText("BAS");
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
