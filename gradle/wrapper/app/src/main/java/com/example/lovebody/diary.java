package com.example.lovebody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class diary extends AppCompatActivity {

    ListView listView;

    String[] allfiles;

    int k =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        allfiles = getApplicationContext().fileList();
        String[] needed = new String[allfiles.length-2];
        for (int i=0;i<allfiles.length;i++){
            if(!allfiles[i].equals("Average.txt")){
                if(!allfiles[i].equals("instant-run")){

                needed[k] = allfiles[i];

                k++;
            }}

        }

        listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, needed);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getBaseContext(),i+"", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), Entries.class);
                intent.putExtra("pos", i);
                intent.putExtra("from",2);
                startActivity(intent);
            }
        });


    }



    public void Back(View view) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}
