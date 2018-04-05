package com.example.akshansh.demoapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean ironManTurn=true;
    int gameState[]={2,2,2,2,2,2,2,2,2};
    int winningPos[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean isActive=true;
    public void dropIn(View view)
    {
        ImageView imageView=(ImageView)view;

        int tappedCounter=Integer.parseInt(imageView.getTag().toString());
        if(isActive&&gameState[tappedCounter]==2) {
            imageView.setTranslationY(-1000);
            if (ironManTurn) {
                imageView.setImageResource(R.drawable.ironman);
                gameState[tappedCounter] = 0;
                ironManTurn = false;
            } else {
                imageView.setImageResource(R.drawable.captainamerica);
                gameState[tappedCounter] = 1;
                ironManTurn = true;
            }
            imageView.animate().translationYBy(1000).rotation(360).setDuration(1000);
            TextView textView=findViewById(R.id.textView);
            for(int []winningPosition:winningPos)
            {

                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=2)
                {
                    isActive=false;
                    if(gameState[winningPosition[0]]==0)
                    {
                        textView.setText("IRONMAN WON!!!");
                    }
                    else
                    {
                        textView.setText("CAPTAIN AMERICA!!!");
                    }

                    LinearLayout playAgainLayout=findViewById(R.id.playAgainLayout);
                    playAgainLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    boolean gameIsOver=true;
                    for(int counterState:gameState)
                    {
                        if(counterState==2)
                        {
                            gameIsOver=false;
                        }
                    }
                    if(gameIsOver)
                    {
                        textView.setText("IT'S A DRAW...");
                    }
                }

            }

        }

    }
    public void playAgain(View view)
    {
        isActive=true;
        LinearLayout playAgainLayout=findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);
        ironManTurn=true;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        GridLayout grid=findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++)
        {
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
