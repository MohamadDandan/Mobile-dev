package com.example.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AddQuestion extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        DataBase db=new DataBase(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_question);

        ConstraintLayout layout=(ConstraintLayout)findViewById(R.id.aq);

        SharedPreferences sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        int mode=sp.getInt("key1",9);

        EditText Question=(EditText) findViewById(R.id.ET_Question);

        EditText A=(EditText) findViewById(R.id.ET_Answer_A);
        EditText B=(EditText) findViewById(R.id.ET_Answer_B);
        EditText C=(EditText) findViewById(R.id.ET_Answer_C);
        EditText D=(EditText) findViewById(R.id.ET_Answer_D);

        EditText Answer=(EditText) findViewById(R.id.ET_Answer);

        Button Add=(Button) findViewById(R.id.button_Add);
        Button Cancel=(Button) findViewById(R.id.button_Cancel);

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question.setText("");
                A.setText("");
                B.setText("");
                C.setText("");
                D.setText("");
                Answer.setText("");
            }
        });

        if(mode==1){
            layout.setBackgroundResource(R.drawable.background2);
            Add.setBackgroundResource(R.drawable.shadow2);
            Cancel.setBackgroundResource(R.drawable.shadow2);
            Question.setBackgroundResource(R.drawable.rounded_corner_edit_view2);
            A.setBackgroundResource(R.drawable.rounded_corner_edit_view2);
            B.setBackgroundResource(R.drawable.rounded_corner_edit_view2);
            C.setBackgroundResource(R.drawable.rounded_corner_edit_view2);
            D.setBackgroundResource(R.drawable.rounded_corner_edit_view2);
            Answer.setBackgroundResource(R.drawable.rounded_corner_edit_view2);

        }
        else{
            layout.setBackgroundResource(R.drawable.background);
            Add.setBackgroundResource(R.drawable.shadow);
            Cancel.setBackgroundResource(R.drawable.shadow);
            Question.setBackgroundResource(R.drawable.rounded_corner_edit_view);
            A.setBackgroundResource(R.drawable.rounded_corner_edit_view);
            B.setBackgroundResource(R.drawable.rounded_corner_edit_view);
            C.setBackgroundResource(R.drawable.rounded_corner_edit_view);
            D.setBackgroundResource(R.drawable.rounded_corner_edit_view);
            Answer.setBackgroundResource(R.drawable.rounded_corner_edit_view);}

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = Question.getText().toString();
                String a = A.getText().toString();
                String b = B.getText().toString();
                String c = C.getText().toString();
                String d = D.getText().toString();
                String solution = Answer.getText().toString();

                if(question.isEmpty() && a.isEmpty() && b.isEmpty() && c.isEmpty() && d.isEmpty() && solution.isEmpty()){
                    Toast.makeText(AddQuestion.this,"complete all data",Toast.LENGTH_SHORT).show();
                    return;
                }
                db.addNewQusetion(question,a,b,c,d,solution);
                Toast.makeText(AddQuestion.this, "question is added.", Toast.LENGTH_SHORT).show();
                Question.setText("");
                A.setText("");
                B.setText("");
                C.setText("");
                D.setText("");
                Answer.setText("");


            }
        });
    }
}
