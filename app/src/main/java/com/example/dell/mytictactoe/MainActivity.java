package com.example.dell.mytictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout rootlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootlayout=findViewById(R.id.root_layout);
        initgame();
    }
    public static int size=3;
    public static int Player_0=1;
    public static int Player_X=2;
    //to show button is empty;
    public static int Noplayer=-1;
    public static int currentplayer;
    public boolean gameover=false;
    TTTbutton button[][];
    TTTbutton tbutton;
    public void initgame(){
        button=new TTTbutton[size][size];
            currentplayer = Player_0;
            setupboard();
    }
    public void setupboard(){
        rootlayout.removeAllViews();
        LinearLayout rowlayout;
        for(int i=0;i<size;i++){
            rowlayout=new LinearLayout(this);
            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            rowlayout.setLayoutParams(params);
            for(int j=0;j<size;j++){
               tbutton=new TTTbutton(this);
               LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1);
               tbutton.setLayoutParams(params1);
               button[i][j]=tbutton;
               tbutton.setOnClickListener(this);
               rowlayout.addView(tbutton);
            }
            rootlayout.addView(rowlayout);
        }
    }
    @Override
    public void onClick(View view){
         tbutton=(TTTbutton) view;
        if(!gameover) {
            tbutton.setplayer(currentplayer);
            checkgameover();
            toggleplayer();
        }
    }
    public void checkgameover(){
         //rows
        for(int i=0;i<size;i++){
            TTTbutton firstbutton=button[i][0];
            TTTbutton currentbutton;
            boolean rowsame=true;
            for(int j=0;j<size;j++){
                currentbutton=button[i][j];
                if(firstbutton.getplayer()!=currentbutton.getplayer()||currentbutton.isempty()){
                    rowsame=false;
                    break;
                }
            }
            if(rowsame){
                  gameover=true;
                  ongameover(firstbutton.getplayer());
                  return;
            }
        }
        //cols
        for(int i=0;i<size;i++){
            TTTbutton firstbutton=button[0][i];
            TTTbutton currentbutton;
            boolean colsame=true;
            for(int j=0;j<size;j++){
                currentbutton=button[j][i];
                if(firstbutton.getplayer()!=currentbutton.getplayer()||currentbutton.isempty()){
                    colsame=false;
                    break;
                }
            }
            if(colsame){
                gameover=true;
                ongameover(firstbutton.getplayer());
                return;
            }
        }
        //draw
        boolean ft=true;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                    TTTbutton b=button[i][j];
                    if(b.isempty()){
                        ft=false;
                        break;
                    }
            }
        }
        if(ft){
            ongameover(-1);
            return;
        }
        //diagonals
        boolean d=true;
        TTTbutton f=button[0][0];
        for(int i=0;i<size;i++){
            TTTbutton c=button[i][i];
                if(c.isempty()||c.getplayer()!=f.getplayer()){
                  d=false;
                  break;
            }
        }
        if(d){
            gameover=true;
            ongameover(f.getplayer());
        }
    }

    public void ongameover(int getplayer) {
        if(getplayer==1){
            Toast.makeText(this,"PLAYER 0 WINS",Toast.LENGTH_LONG).show();
        }
        else if(getplayer==2){
            Toast.makeText(this,"PLAYER X WINS",Toast.LENGTH_LONG).show();
        }
        else if(getplayer==-1){
            Toast.makeText(this,"draw",Toast.LENGTH_LONG).show();
        }
       for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                button[i][j].setEnabled(false);
            }
       }
    }
    public void toggleplayer(){
         currentplayer=currentplayer==Player_0?Player_X:Player_0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ttt_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.reset){
            initgame();
        }
        if(id==R.id.three){
            size=3;
            initgame();
            item.setChecked(true);
        }
        if(id==R.id.four){
            size=4;
            initgame();
            item.setChecked(true);
        }
        if(id==R.id.five){
            size=5;
            initgame();;
            item.setChecked(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
