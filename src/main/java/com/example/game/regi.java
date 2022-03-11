package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class regi extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        ConstraintLayout layout=(ConstraintLayout)findViewById(R.id.reg);
        //database for user data
        user_Data da=new user_Data(regi.this);

        //control
        EditText username=(EditText)findViewById(R.id.ET_User);

        EditText password=(EditText)findViewById(R.id.ET_Pass);

        Button Enter=(Button) findViewById(R.id.LogIn);

        //read temp data for design
        SharedPreferences sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        int mode=sp.getInt("key1",9);

        // design switch between design color green and blue
        if(mode==1){
            layout.setBackgroundResource(R.drawable.background2);
            Enter.setBackgroundResource(R.drawable.shadow2);
            username.setBackgroundResource(R.drawable.rounded_corner_edit_view2);
            password.setBackgroundResource(R.drawable.rounded_corner_edit_view2);

        }
        else{
            layout.setBackgroundResource(R.drawable.background);
            Enter.setBackgroundResource(R.drawable.shadow);
            username.setBackgroundResource(R.drawable.rounded_corner_edit_view);
            password.setBackgroundResource(R.drawable.rounded_corner_edit_view);
            }



        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(user.isEmpty()||pass.isEmpty()){
                    Toast.makeText(regi.this,"fill the requrment2 ",Toast.LENGTH_SHORT).show() ;
                }
                else {
                    boolean chick = da.add_user(user, pass);
                    if (chick) {
                        //login after register
                        if (da.checkUserExist(user, pass) > 0) {
                            Intent i = new Intent(getApplicationContext(), menu.class);
                            i.putExtra("User", user);
                            i.putExtra("mode", mode);
                            i.putExtra("User_id", da.getid(user, pass));
                            startActivity(i);
                        }
                    }

                }

            }
        });



    }


}