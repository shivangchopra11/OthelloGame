package com.example.shivang.othello;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout main_layout = (LinearLayout)findViewById(R.id.home);
        Button b1 = (Button)findViewById(R.id.new_game);
        //Button b2 = (Button)findViewById(R.id.level);
        b1.setOnClickListener(this);
        //b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.new_game) {
            Intent i = new Intent(this,game.class);
            startActivity(i);
        }
//        if(v.getId()==R.id.level) {
//            Intent i = new Intent(this,Level.class);
//            startActivity(i);
//        }

    }
}
