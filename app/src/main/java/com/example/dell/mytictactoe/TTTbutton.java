package com.example.dell.mytictactoe;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import com.example.dell.mytictactoe.MainActivity;

/**
 * Created by Dell on 01-02-2018.
 */

public class TTTbutton extends AppCompatButton {
    private int player=MainActivity.Noplayer;
    TTTbutton(Context context){
        super(context);
    }
    public void setplayer(int player){
        this.player=player;
        if(player==1){
            setText("0");
        }
        else if(player==2){
            setText("X");
        }
        setEnabled(false);
    }
    public int getplayer(){
        return player;
    }
    public boolean isempty(){
        return player==MainActivity.Noplayer;
    }
}
