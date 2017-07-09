package com.example.shivang.othello;

import android.content.Context;
import android.widget.Button;

/**
 * Created by shivang on 19/06/17.
 */

public class MyButton extends Button {
    int value;
    int x;
    int y;
    boolean marked;
    public MyButton(Context context) {
        super(context);
        marked = false;
        value = -1;
    }
}
