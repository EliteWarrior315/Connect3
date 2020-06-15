package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity

{
    int yellowWinCount = 0;
    int redWinCount = 0;
    boolean gameActive=true;
    int[]positions = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};// 0: Red 1: Yellow 2: Empty
    int[][] winningPositions = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
    boolean redChance=true;
    int c=0;
        public void placeCounter(View view) {
            //c++;
            ImageView counter = (ImageView) view;
            int tappedCounter = Integer.valueOf(counter.getTag().toString());
            Log.i("TappedCounter:::",tappedCounter+"");

            if (positions[tappedCounter] == 2 && gameActive) {
                c++;
                counter.setTranslationY(-1500);
                //Toast.makeText(this, tappedCounter + "", Toast.LENGTH_SHORT).show();
                if (redChance) {
                    counter.setImageResource(R.drawable.red);
                    redChance = false;
                    positions[tappedCounter] = 0;

                } else {
                    counter.setImageResource(R.drawable.yellow);
                    redChance = true;
                    positions[tappedCounter] = 1;
                }
                counter.animate().translationYBy(1500).rotation(3600).setDuration(200);

                //Log.i("`Info", "Counter dropped");
                for (int[] winningPosition : winningPositions) {

                    if (positions[winningPosition[0]] == positions[winningPosition[1]] && positions[winningPosition[1]] == positions[winningPosition[2]] && positions[winningPosition[0]] != 2) {
                        //Toast.makeText(this, "Someone has won", Toast.LENGTH_SHORT).show();
                        String winner="";
                        gameActive=false;
                        Button b1 = findViewById(R.id.button);
                        TextView tv = findViewById(R.id.textView);
                        if(redChance) {
                            winner = getResources().getString(R.string.yellow);
                            tv.setTextColor(0xFFE4C115);
                            yellowWinCount++;
                           TextView yellowWins = findViewById(R.id.yellowWins);
                           yellowWins.setText("Yellow: "+yellowWinCount);
                        }
                        else {
                            winner = getResources().getString(R.string.red);
                            tv.setTextColor(Color.RED);
                            redWinCount++;
                            TextView redWins = findViewById(R.id.redWins);
                            redWins.setText("Red: "+redWinCount);
                        }
                        tv.setText(winner+" Wins!!!");
                        tv.setVisibility(View.VISIBLE);
                        b1.setVisibility(View.VISIBLE);

                    }

                }


            }
            if(gameActive && c==9)
            {
                Button b1 = findViewById(R.id.button);
                TextView tv = findViewById(R.id.textView);
                tv.setText("It's a DRAW!!!");
                tv.setTextColor(Color.GREEN);
                tv.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
            }/*else{
                if(!redChance && c!=9 )
                    new Handler().postDelayed(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      player2();
                                                  }
                                              }, 400);

            }*/

        }

        public void restart(View view) {
            Button b1 = findViewById(R.id.button);
            TextView tv = findViewById(R.id.textView);
            b1.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);
            androidx.gridlayout.widget.GridLayout grid = findViewById(R.id.gridLayout);
            for (int i = 0; i < grid.getChildCount(); i++) {
                //Log.i("info",grid.getChildCount()+"");
                ImageView counter = (ImageView) grid.getChildAt(i);
                counter.setImageDrawable(null);
            }
                for(int i=1;i<=9;i++)
                    positions[i]=2;
                redChance=true;
                gameActive=true;
                c=0;
            }
            public void reset(View view){
                restart(view);
                redWinCount=0;
                yellowWinCount=0;
                TextView yellowWins = findViewById(R.id.yellowWins);
                yellowWins.setText("Yellow: "+yellowWinCount);
                TextView redWins = findViewById(R.id.redWins);
                redWins.setText("Red: "+redWinCount);
            }
            public void player2(){
                int empty[] = new int[9-c];
                Log.i("info","Array Created");
                int i;
                int j=0;
                for(i=1;i<positions.length;i++){
                    if (positions[i] == 2)
                        empty[j++] = i;
                    }
                for(i=0;i<empty.length;i++)
                    Log.i("Array VAlue ",empty[i]+"");
                //generate a random  number
                Random  r = new Random();
                int random  = r.nextInt((empty.length));
                int chosenCell = empty[random];
                Log.i("Random no: ",random+"");

                Log.i("Chosen Cell is: ",chosenCell+"");
                androidx.gridlayout.widget.GridLayout grid = findViewById(R.id.gridLayout);
                //for (i = 0; i < grid.getChildCount(); i++) {
                    //Log.i("info", grid.getChildCount() + "");
                    ImageView counter = (ImageView) grid.getChildAt(chosenCell-1);
                    Log.i("Tag:",counter.getTag()+"");
                    View a = (View) counter;
                    //redChance=true;
                    placeCounter(a);

                }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
