package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        Intent i=getIntent();

        String get_user_name= String.valueOf(i.getExtras().get("User"));
        String get_user_id= String.valueOf(i.getExtras().get("User_id"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        TextView username=(TextView) findViewById(R.id.name_pla);
        username.setText(get_user_name) ;

        Button play=(Button) findViewById(R.id.play);
        Button record=(Button) findViewById(R.id.record);

        //read temp data for design
        SharedPreferences sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int mode=sp.getInt("key1",9);

        // design switch between design color green and blue
        if(mode==1){
            play.setBackgroundResource(R.drawable.shadow2);
            record.setBackgroundResource(R.drawable.shadow2);
        }
        else{
            play.setBackgroundResource(R.drawable.shadow);
            record.setBackgroundResource(R.drawable.shadow);
        }

        // change button name according to the user (id=1=>admin,id=?=>player)
        if(get_user_id.equals("1"))
            play.setText("Add question");
        else
            play.setText("Play");

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(get_user_id.equals("1")){
                    Intent i = new Intent(getApplicationContext(), AddQuestion.class);
                    i.putExtra("mode",mode);
                    i.putExtra("User",get_user_name);
                    i.putExtra("User_id",get_user_id);
                    startActivity(i);
                }else{
                    Intent i = new Intent(getApplicationContext(), Game.class);
                    i.putExtra("User",get_user_name);
                    i.putExtra("User_id",get_user_id);
                    startActivity(i);
                }
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), score.class);
                i.putExtra("User",get_user_name);
                i.putExtra("User_id",get_user_id);
                startActivity(i);
            }
        });




    }
}