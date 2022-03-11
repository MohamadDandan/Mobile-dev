package com.example.game;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Game extends AppCompatActivity {
    int counter=0;
    Boolean a=false;
    Boolean b=false;
    Boolean c=false;
    Boolean d=false;
    DataBase db=new DataBase(Game.this);
    user_Data user_db=new user_Data(Game.this);
    int max ;
    int min ;
    int range  ;
    int id ;
    boolean time_check=true;;
    String Ansewr ;
    TextView Question;
    Button A,B,C,D;
    ArrayList e=new ArrayList();

    MediaPlayer mediaPlayer;


    protected void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Intent i=getIntent();

        mediaPlayer = MediaPlayer.create(this, R.raw.m2);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();

        for(int ix=0;ix<=db.getAllId().toArray().length-1;ix++){
            e.add(db.getAllId().get(ix));
        }


         String User_id= String.valueOf(i.getExtras().get("User_id"));
        Question=(TextView) findViewById(R.id.TV_Question);
         A=(Button) findViewById(R.id.button_A);
         B=(Button) findViewById(R.id.button_B);
         C=(Button) findViewById(R.id.button_C);
         D=(Button) findViewById(R.id.button_D);



        Question(e);





        Button Enter=(Button) findViewById(R.id.enter);



        ImageView de=(ImageView) findViewById(R.id.gifView);
        ObjectAnimator animation = ObjectAnimator.ofFloat(de, "translationX", 1040f);
        animation.setDuration(20000);
        animation.start();



        TextView  time = findViewById(R.id.timer);
        // Time is in millisecond so 50sec = 50000 I have used
        // countdown Interveal is 1sec = 1000 I have used
        CountDownTimer r=new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                time_check=true;
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                time.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                time.setText("00:00:00");
                mediaPlayer.stop();
                Intent ma = new Intent(getApplicationContext(), menu.class);
                ma.putExtra("User", String.valueOf(i.getExtras().get("User")));
                ma.putExtra("User_id",User_id);
                startActivity(ma);
                startActivity(ma);




                String Score=String.valueOf(counter);
                boolean see=user_db.update(User_id,Score );
                if(see){
                    Log.e("see","see is true"+Score);
                }else{
                    Log.e("see","see is false"+Score);
                }

            }
        };
        r.start();
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("this","oo"+i.getExtras().get("User"));
                A.setBackgroundColor(Color.BLUE);
                B.setBackgroundColor(Color.rgb(252,228,236));
                C.setBackgroundColor(Color.rgb(252,228,236));
                D.setBackgroundColor(Color.rgb(252,228,236));
                 a=true;
                 b=false;
                 c=false;
                 d=false;
            }
        });



        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                B.setBackgroundColor(Color.BLUE);
                A.setBackgroundColor(Color.rgb(252,228,236));
                C.setBackgroundColor(Color.rgb(252,228,236));
                D.setBackgroundColor(Color.rgb(252,228,236));
                 a=false;
                 b=true;
                 c=false;
                 d=false;

            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C.setBackgroundColor(Color.BLUE);
                B.setBackgroundColor(Color.rgb(252,228,236));
                A.setBackgroundColor(Color.rgb(252,228,236));
                D.setBackgroundColor(Color.rgb(252,228,236));
                 a=false;
                 b=false;
                 c=true;
                 d=false;

            }
        });


        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                D.setBackgroundColor(Color.BLUE);
                B.setBackgroundColor(Color.rgb(252,228,236));
                C.setBackgroundColor(Color.rgb(252,228,236));
                A.setBackgroundColor(Color.rgb(252,228,236));
                 a=false;
                 b=false;
                 c=false;
                d=true;

            }
        });





       Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Ansewr.equals(A.getText())){
                    A.setBackgroundColor(Color.GREEN);


                }else if(Ansewr.equals(B.getText())){
                    B.setBackgroundColor(Color.GREEN);

                }else if(Ansewr.equals(C.getText())){
                    C.setBackgroundColor(Color.GREEN);

                }else if(Ansewr.equals(D.getText())){
                    D.setBackgroundColor(Color.GREEN);

                }


                if(a && Ansewr.equals(A.getText())){
                    Question(e);
                    counter++;


                }

                else if(b && Ansewr.equals(B.getText())){
                    Question(e);
                    counter++;
                }

                else if(c &&Ansewr.equals(C.getText())){
                    Question(e);
                    counter++;

                }

                else if(d && Ansewr.equals(D.getText())){
                    Question(e);
                    counter++;
                }else{
                    mediaPlayer.stop();
                    Intent ma = new Intent(getApplicationContext(), menu.class);

                    ma.putExtra("User", String.valueOf(i.getExtras().get("User")));
                    ma.putExtra("User_id",User_id);
                    startActivity(ma);

                    r.cancel();

                       }
                String Score=String.valueOf(counter);
                user_db.update(User_id,Score );


            }
        });




    }
    public void Question (ArrayList e){

        max = e.size();
        min = 0;
        range = max - min ;
        id = (int)(Math.random() * range) ;

        Question.setText((db.getAllCotacts(db.getQusetion_COL(), e.get(id))));
        A.setText(db.getAllCotacts(db.getAnswer_a_COL(), e.get(id)));
        B.setText(db.getAllCotacts(db.getAnswer_b_COL(), e.get(id)));
        C.setText(db.getAllCotacts(db.getAnswer_c_COL(), e.get(id)));
        D.setText(db.getAllCotacts(db.getAnswer_d_COL(), e.get(id)));
        Ansewr =db.getAllCotacts(db.getAnswer_COL(), e.get(id));

        e.remove(id);

        Log.e("this","we have the id and we will delete"+id);
        Log.e("this","new array"+e.toString());
        D.setBackgroundColor(Color.rgb(252,228,236));
        B.setBackgroundColor(Color.rgb(252,228,236));
        C.setBackgroundColor(Color.rgb(252,228,236));
        A.setBackgroundColor(Color.rgb(252,228,236));
        a=false;
        b=false;
        c=false;
        d=false;
    }
}
