package com.example.game;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class score extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        user_Data db = new user_Data(score.this);
        ListView lv=(ListView) findViewById(R.id.listView_id);
        ListView lv2=(ListView) findViewById(R.id.listView_name);
        ListView lv3=(ListView) findViewById(R.id.listView);
        final List<String> id_list = new ArrayList<String>(Arrays.asList(db.getdata( db.getIdCol())));
        final List<String> NAME_list = new ArrayList<String>(Arrays.asList(db.getdata(db.getusername_COLL() )));
        final List<String> record_list = new ArrayList<String>(Arrays.asList(db.getdata(db.getrecord_COLL() )));

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, id_list);

        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, NAME_list);

        final ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, record_list);

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter1);
        lv2.setAdapter(arrayAdapter2);
        lv3.setAdapter(arrayAdapter3);



    }
}
