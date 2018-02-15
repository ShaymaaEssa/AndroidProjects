package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button threePointsA;
    Button twoPointsA;
    Button freeTrhowA;

    Button threePointsB;
    Button twoPointsB;
    Button freeTrhowB;

    Button reset;

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        threePointsA = (Button) findViewById(R.id.btn_threepointsa);
        twoPointsA = (Button) findViewById(R.id.btn_twopointsa);
        freeTrhowA = (Button) findViewById(R.id.btn_freethrowa);

        threePointsB = (Button) findViewById(R.id.btn_threepointsb);
        twoPointsB = (Button) findViewById(R.id.btn_twopointsb);
        freeTrhowB = (Button) findViewById(R.id.btn_freethrowb);

        reset = (Button) findViewById(R.id.btn_reset);

        threePointsA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamA +=3;
                displayForTeamA(scoreTeamA);
            }
        });

        twoPointsA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamA +=2;
                displayForTeamA(scoreTeamA);
            }
        });

        freeTrhowA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamA +=1;
                displayForTeamA(scoreTeamA);
            }
        });

        threePointsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamB +=3;
                displayForTeamB(scoreTeamB);
            }
        });

        twoPointsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamB +=2;
                displayForTeamB(scoreTeamB);
            }
        });

        freeTrhowB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamB +=1;
                displayForTeamB(scoreTeamB);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamA =0;
                scoreTeamB =0;
                displayForTeamA(scoreTeamA);
                displayForTeamB(scoreTeamB);
            }
        });
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}
