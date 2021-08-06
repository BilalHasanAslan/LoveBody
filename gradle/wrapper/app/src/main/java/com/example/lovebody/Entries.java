package com.example.lovebody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Entries extends AppCompatActivity {
    int from=0;
    int pos;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);

        Intent intent = getIntent();
        String time;
        from = (int) intent.getIntExtra("from", 0);

        if(from==1){
            time =intent.getStringExtra("time").toString();
            findViewById(R.id.button4).setVisibility(View.INVISIBLE);
            findViewById(R.id.button2).setVisibility(View.INVISIBLE);
        }else{
            pos = intent.getIntExtra("pos", 0);

            int k = 0;
            String[] allfiles = getApplicationContext().fileList();
            String[] needed = new String[allfiles.length-2];
            for (int i=0;i<allfiles.length;i++){
                if(!allfiles[i].equals("Average.txt")){
                    if(!allfiles[i].equals("instant-run")){

                        needed[k] = allfiles[i];

                        k++;
                    }}

            }
            time = needed[pos];
            if(pos==0){

                findViewById(R.id.button2).setVisibility(View.INVISIBLE);
                findViewById(R.id.button4).setVisibility(View.VISIBLE);

            }else if(pos==needed.length-1){
                //Toast.makeText(getBaseContext(),pos+"", Toast.LENGTH_LONG).show();
                findViewById(R.id.button4).setVisibility(View.INVISIBLE);
                findViewById(R.id.button2).setVisibility(View.VISIBLE);
            }
            else{
                findViewById(R.id.button4).setVisibility(View.VISIBLE);
                findViewById(R.id.button2).setVisibility(View.VISIBLE);
            }


        }

        //Toast.makeText(getBaseContext(),time, Toast.LENGTH_LONG).show();
        FileInputStream fis = null;

        try {
            fis = openFileInput(time);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String data;
            while ((data = br.readLine())!=null){
                sb.append(data).append("\n");

            }
            TextView text = (TextView) findViewById(R.id.lol);
            text.setText(sb.toString());
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



    }
    public void Back(View view) {
        if(from==1){
            this.finish();
        }else{
            Intent intent = new Intent(getBaseContext(), diary.class);
            startActivity(intent);
        }

    }
    private String getDateTime() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        return date;
    }

    public void Previous(View view) {
        pos--;
        Intent intent = new Intent(getBaseContext(), Entries.class);
        intent.putExtra("pos", pos);
        intent.putExtra("from",2);
        startActivity(intent);
    }

    public void Next(View view) {
        pos++;
        Intent intent = new Intent(getBaseContext(), Entries.class);
        intent.putExtra("pos", pos);
        intent.putExtra("from",2);
        startActivity(intent);
    }
}
