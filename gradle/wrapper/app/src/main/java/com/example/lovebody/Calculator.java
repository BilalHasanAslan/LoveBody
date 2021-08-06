package com.example.lovebody;

import android.content.Context;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void Back(View view) {
        this.finish();
    }

    double n=0;
    double n1=0;
    double n2=0;
    double n3=0;

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() != 0;
    }


    public double food() {
        String v,v1,v2,v3;
         n=0;
         n1=0;
         n2=0;
         n3=0;

        EditText editText4 = (EditText) findViewById(R.id.editText4);
        if (isEmpty(editText4)) {
            //Toast.makeText(getBaseContext(),editText4.getText().toString(), Toast.LENGTH_LONG).show();
             v = editText4.getText().toString();
             n = Double.parseDouble(v);

        }

        EditText editText = (EditText) findViewById(R.id.editText);

        if (isEmpty(editText)) {
             v1 = editText.getText().toString();
             n1 = Double.parseDouble(v1);
        }

        EditText editText2 = (EditText) findViewById(R.id.editText2);

        if (isEmpty(editText2)) {
             v2 = editText2.getText().toString();
             n2 = Double.parseDouble(v2);
        }

        EditText editText8 = (EditText) findViewById(R.id.editText8);

        if (isEmpty(editText8)) {
             v3 = editText8.getText().toString();
             n3 = Double.parseDouble(v3);
        }

        return  Math.round((n1+n2+n3+n) * 100.0) / 100.0;
    }

    double n4=0;
    double n5=0;
    double n6=0;
    double n7=0;

    public double Exercise(){
         n4=0;
         n5=0;
         n6=0;
         n7=0;


    EditText editText5 = (EditText) findViewById(R.id.editText5);

        if(isEmpty(editText5)) {
            String v4 = editText5.getText().toString();
         n4 = Double.parseDouble(v4);
    }

    EditText editText6 = (EditText) findViewById(R.id.editText6);

        if(isEmpty(editText6)) {
            String v5 = editText6.getText().toString();
         n5 = Double.parseDouble(v5);
    }

    EditText editText3 = (EditText) findViewById(R.id.editText3);

        if(isEmpty(editText3)) {
            String v6 = editText3.getText().toString();
         n6 = Double.parseDouble(v6);
    }

    EditText editText7 = (EditText) findViewById(R.id.editText7);

        if(isEmpty(editText7)) {
            String v7 = editText7.getText().toString();
         n7 =Double.parseDouble(v7);
    }

        return Math.round((n4 + n5 + n6 + n7) * 100.0) / 100.0;
    }





    public void Calculate(View view) {
        TextView lol = (TextView) findViewById(R.id.textView21);
        TextView lol1 = (TextView) findViewById(R.id.textView22);
        TextView lol2 = (TextView) findViewById(R.id.textView23);

        lol.setText("Total Food:\n "+ food());
        lol1.setText("Total Exercises:\n "+Exercise() );
        lol2.setText("Net Value:\n "+ (food()-Exercise()));
    }

    private String getDateTime() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        return date;
    }

    String time = null;
    public void Save(View view) {
        time = getDateTime();
        Calculate(view);
        String data = "\n\n"+getDateTime() +"\n\nFood\nBreakfast: "+n + "\nLunch: "+n1+"\nDinner: "+n2+"\nSnack: "+n3+"\n\nExercises\nGym: "+n4+"\nJogging: "+n5+"\nSwimming: "+n6+"\nBiking: "+n7+"\n\nFood Total: "+food()+"\nExercises Total: "+Exercise()+"\nNet Total: "+(food()-Exercise());

        FileOutputStream fos = null;

        try {
            fos = openFileOutput(time, MODE_PRIVATE);
            fos.write(data.getBytes());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        ave(food()-Exercise());
        int k = 1;
        Intent intent = new Intent(getBaseContext(), Entries.class);
        intent.putExtra("time", time);
        intent.putExtra("from",k);
        startActivity(intent);

    }

    public void ave(double netValue){

        try {
            FileOutputStream fos = new FileOutputStream("Average.txt",true);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream fis = null;
        double ave = 0;
        int num = 0;

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
                num = Integer.parseInt(avedata.split(" ")[1]);
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

        ave = (ave + netValue)/(num+1);
        num++;
        ave = Math.round(ave * 100.0) / 100.0;
        String temp = ave +" "+ num;

        try {
            FileOutputStream fos = openFileOutput("Average.txt", Context.MODE_PRIVATE);
            fos.write(temp.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}
