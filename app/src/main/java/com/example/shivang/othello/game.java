package com.example.shivang.othello;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class game extends AppCompatActivity implements View.OnClickListener{

    public static int n=8;
    public static LinearLayout mainLayout;
    public static LinearLayout[] rowLayout;
    public static MyButton[][] buttons;
    public static int PLAYER_1 = 2;
    public static int PLAYER_2 = 2;
    public static int turn = 0;
    public static TextView tv1;
    public static TextView tv2;
    public static int[] arr_x = {-1,0,1};
    public static int[] arr_y = {-1,0,1};
    public static boolean changed=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mainLayout = (LinearLayout) findViewById(R.id.game);
        setUpBoard();
    }
    public void setUpBoard() {
        mainLayout.removeAllViews();
        rowLayout = new LinearLayout[n+1];
        buttons = new MyButton[n][n];
        for(int i=0;i<n+1;i++) {
            rowLayout[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params;
            if(i==n)
                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,2f);
            else
                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1f);
            params.setMargins(1,1,1,1);
            rowLayout[i].setLayoutParams(params);
            rowLayout[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(rowLayout[i]);
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                buttons[i][j] = new MyButton(this);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1f);
                params1.setMargins(1,1,1,1);
                buttons[i][j].setLayoutParams(params1);
                if(i%2 == 0) {
                    if(j%2==0)
                        buttons[i][j].setBackgroundColor(getResources().getColor(R.color.green));
                    else
                        buttons[i][j].setBackgroundColor(getResources().getColor(R.color.lime));
                }
                else {
                    if(j%2==0)
                        buttons[i][j].setBackgroundColor(getResources().getColor(R.color.lime));
                    else
                        buttons[i][j].setBackgroundColor(getResources().getColor(R.color.green));

                }
                buttons[i][j].setOnClickListener(this);
                rowLayout[i].addView(buttons[i][j]);
            }
        }
        tv1 = new TextView(this);
        tv2 = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1f);
        params.setMargins(1,1,1,1);
        tv1.setLayoutParams(params);
        tv1.setText("PLAYER 1 : " + PLAYER_1);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv1.setTextColor(getResources().getColor(R.color.black));
        tv2.setTextColor(getResources().getColor(R.color.white));
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv2.setText("PLAYER 2 : " + PLAYER_2);
        tv1.setTextSize(35);
        tv2.setTextSize(35);
        tv1.setBackgroundColor(getResources().getColor(R.color.golden));
        tv2.setBackgroundColor(getResources().getColor(R.color.golden));
        tv2.setLayoutParams(params);
        rowLayout[n].addView(tv1);
        rowLayout[n].addView(tv2);
        resetBoard();
    }

    private void resetBoard() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                buttons[i][j].value=-1;
                buttons[i][j].x=i;
                buttons[i][j].y=j;
            }
        }
        PLAYER_1 = 2;
        PLAYER_2 = 2;
        turn = 0;
        tv1.setText("PLAYER 1 : " + PLAYER_1);
        tv2.setText("PLAYER 2 : " + PLAYER_2);
        buttons[3][3].value = 0;
        buttons[3][3].setBackgroundResource(R.drawable.button_black_dark);
        buttons[3][4].value = 1;
        buttons[3][4].setBackgroundResource(R.drawable.button_white_light);
        buttons[4][3].value = 1;
        buttons[4][3].setBackgroundResource(R.drawable.button_white_light);
        buttons[4][4].value = 0;
        buttons[4][4].setBackgroundResource(R.drawable.button_black_dark);
    }


    @Override
    public void onClick(View v) {
        MyButton cur = (MyButton) v;
        if (cur.value == -1) {
            for(int a=0;a<3;a++) {
                for(int b=0;b<3;b++) {
                    int c = cur.x;
                    int d = cur.y;
                    if((a==1 && b==1)|| (c==0&&a==0) || (d==0&&b==0) || (c==n-1&&a==2) || (d==n-1&&b==2)) {
                        continue;
                    }
                    else {
                        if(buttons[c+arr_x[a]][d+arr_y[b]].value==turn || buttons[c+arr_x[a]][d+arr_y[b]].value==-1){
                            continue;
                        }
                        else {
//                            c += arr_x[a];
//                            d += arr_y[b];
                            while(buttons[c][d].value!=turn && c+arr_x[a]>=0 && c+arr_x[a]<=n-1 && d+arr_y[b]>=0 && d+arr_y[b]<=n-1) {
                                c += arr_x[a];
                                d += arr_y[b];
                            }
                            if(buttons[c][d].value==turn) {
                                changed = true;
                                int e=cur.x+arr_x[a];
                                int f=cur.y+arr_y[b];
                                if(turn == 0) {
                                    while(e!=c || f!=d) {
                                        if(e%2==0) {
                                            if(f%2==0) {
                                                buttons[e][f].setBackgroundResource(R.drawable.button_black_dark);
                                                buttons[e][f].value=0;
                                            }
                                            else {
                                                buttons[e][f].setBackgroundResource(R.drawable.button_black_light);
                                                buttons[e][f].value=0;
                                            }
                                        }
                                        else {
                                            if(f%2!=0) {
                                                buttons[e][f].setBackgroundResource(R.drawable.button_black_dark);
                                                buttons[e][f].value=0;
                                            }
                                            else {
                                                buttons[e][f].setBackgroundResource(R.drawable.button_black_light);
                                                buttons[e][f].value=0;
                                            }
                                        }

                                        PLAYER_1++;
                                        PLAYER_2--;
                                        e+=arr_x[a];
                                        f+=arr_y[b];
                                    }
                                    //turn = 1;
                                }
                                else {
                                    while(e!=c || f!=d) {
                                        if(e%2==0) {
                                            if(f%2==0) {
                                                buttons[e][f].setBackgroundResource(R.drawable.button_white_dark);
                                                buttons[e][f].value=1;
                                            }
                                            else {
                                                buttons[e][f].setBackgroundResource(R.drawable.button_white_light);
                                                buttons[e][f].value=1;
                                            }
                                        }
                                        else {
                                            if(f%2!=0) {
                                                buttons[e][f].setBackgroundResource(R.drawable.button_white_dark);
                                                buttons[e][f].value=1;
                                            }
                                            else {
                                                buttons[e][f].setBackgroundResource(R.drawable.button_white_light);
                                                buttons[e][f].value=1;
                                            }
                                        }
                                        PLAYER_2++;
                                        PLAYER_1--;
                                        tv1.setText("PLAYER 1 : " + PLAYER_1);
                                        tv2.setText("PLAYER 2 : " + PLAYER_2);
                                        e+=arr_x[a];
                                        f+=arr_y[b];
                                    }
                                    //turn = 0;
                                }
                            }
                        }
                    }
                }
            }
            if(changed==true) {
                if(turn == 0) {
                    int e = cur.x;
                    int f = cur.y;
                    if(e%2==0) {
                        if(f%2==0) {
                            buttons[e][f].setBackgroundResource(R.drawable.button_black_dark);
                            buttons[e][f].value=0;
                        }
                        else {
                            buttons[e][f].setBackgroundResource(R.drawable.button_black_light);
                            buttons[e][f].value=0;
                        }
                    }
                    else {
                        if(f%2!=0) {
                            buttons[e][f].setBackgroundResource(R.drawable.button_black_dark);
                            buttons[e][f].value=0;
                        }
                        else {
                            buttons[e][f].setBackgroundResource(R.drawable.button_black_light);
                            buttons[e][f].value=0;
                        }
                    }
                    PLAYER_1++;
                    //PLAYER_2--;

                }
                else {
                    int e = cur.x;
                    int f = cur.y;
                    if(e%2==0) {
                        if(f%2==0) {
                            buttons[e][f].setBackgroundResource(R.drawable.button_white_dark);
                            buttons[e][f].value=1;
                        }
                        else {
                            buttons[e][f].setBackgroundResource(R.drawable.button_white_light);
                            buttons[e][f].value=1;
                        }
                    }
                    else {
                        if(f%2!=0) {
                            buttons[e][f].setBackgroundResource(R.drawable.button_white_dark);
                            buttons[e][f].value=1;
                        }
                        else {
                            buttons[e][f].setBackgroundResource(R.drawable.button_white_light);
                            buttons[e][f].value=1;
                        }
                    }
                    PLAYER_2++;
                    //PLAYER_1--;
                }
                tv1.setText("PLAYER 1 : " + PLAYER_1);
                tv2.setText("PLAYER 2 : " + PLAYER_2);
                changed=false;
                if(turn ==  0)
                    turn = 1;
                else
                    turn = 0;
            }

        }
    }
}
