package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        protected void onCreate(Bundle savedInstanceState) {
            //database for user data
            user_Data db=new user_Data(MainActivity.this);

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ConstraintLayout layout=(ConstraintLayout)findViewById(R.id.main_layout);

            // random generator number range{0 to 1}
            int max = 2;
            int min = 0;
            int range = max - min ;
            int mode = (int)(Math.random() * range) ;

            //save temp data for design
            SharedPreferences sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putInt("key1",mode );

            /// control
            EditText username=(EditText)findViewById(R.id.ET_User);

            TextView reg=(TextView)findViewById(R.id.Register);

            EditText password=(EditText)findViewById(R.id.ET_Pass);

            Button Enter=(Button) findViewById(R.id.LogIn);


            // design switch between design color green and blue
            if(mode==1){
                layout.setBackgroundResource(R.drawable.background2);
                Enter.setBackgroundResource(R.drawable.shadow2);
                username.setBackgroundResource(R.drawable.rounded_corner_edit_view2);
                password.setBackgroundResource(R.drawable.rounded_corner_edit_view2);
                reg.setTextColor(Color.rgb(0,128,0));
            }
            else{
                layout.setBackgroundResource(R.drawable.background);
                Enter.setBackgroundResource(R.drawable.shadow);
                username.setBackgroundResource(R.drawable.rounded_corner_edit_view);
                password.setBackgroundResource(R.drawable.rounded_corner_edit_view);
                reg.setTextColor(Color.BLUE);
            }



            Enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String user=username.getText().toString();
                    String pass=password.getText().toString();
                    if(db.checkUserExist(user,pass)>0){
                        //if user was admin go to admin menu
                        if(user.equals("admin")&&pass.equals("admin")){
                            Intent i = new Intent(getApplicationContext(), menu.class);
                            editor.putInt("key2",1 );
                            i.putExtra("User",user);
                            i.putExtra("mode",mode);
                            i.putExtra("User_id",db.getid(user,pass));
                            startActivity(i);
                        }
                        //else user go to player menu
                        else{
                            Intent i = new Intent(getApplicationContext(), menu.class);
                            editor.putInt("key2", 0);
                            i.putExtra("User",user);
                            i.putExtra("mode",mode);
                            i.putExtra("User_id",db.getid(user,pass));
                            startActivity(i);
                        }

                    }
                }
            });

            //go to register page to add new player
            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), regi.class);
                    i.putExtra("mode",mode);

                    startActivity(i);
                }
            });
            editor.commit();
        }

    }

