package com.example.lovebody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    public void launchCalc(View view) {
        Intent intent =new Intent(this,Calculator.class);
        startActivity(intent);
    }

    public void launchAve(View view) {
        Intent intent =new Intent(this,Average.class);
        startActivity(intent);
    }


    public void launchDiary(View view) {
        Intent intent =new Intent(this,diary.class);
        startActivity(intent);
    }
}
