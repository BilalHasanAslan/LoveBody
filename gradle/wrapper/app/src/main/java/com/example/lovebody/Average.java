package com.example.lovebody;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Average extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average);

        TextView textview = (TextView) findViewById(R.id.textView);

        double ave = 0;
        int num = 0;
        FileInputStream fis=null;
        try {
            fis = openFileInput("Average.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String data;
            while ((data = br.readLine())!=null){
                sb.append(data);

            }
            String avedata = sb.toString();
            if(avedata!=null) {
                //Toast.makeText(getBaseContext(),avedata, Toast.LENGTH_LONG).show();
                ave = Double.parseDouble(avedata.split(" ")[0]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        textview.setText("Average of your total entries is: "+ave);

    }
    public void Back(View view) {
        this.finish();
    }
}
