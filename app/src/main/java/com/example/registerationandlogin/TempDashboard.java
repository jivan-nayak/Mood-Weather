package com.example.registerationandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class TempDashboard extends AppCompatActivity {

    private TextView quoteText;
    private Button tempDashboardContinue, tempDashboardRe;

    private String quotes[] = {"Dream Big", "Do Something Big!", "You did not come this far to only come this far",
            "Turn ideas into reality", "Remember why you started", "You can win if you want", "Do what scares you",
            "Everyday is a fresh start", "Don't bunt. Aim out of the ballpark. Aim for the company of immortals.",
    "I have stood on a mountain of no’s for one yes.", "If you believe something needs to exist, if it's " +
            "something you want to use yourself, don't let anyone ever stop you from doing it.",
    "If there is no struggle, there is no progress.", "Courage is like a muscle. We strengthen it by use.",
    "Develop success from failures. Discouragement and failure are two of the surest stepping stones to success.",
    "More is lost by indecision than wrong decision.",
    "If the highest aim of a captain were to preserve his ship, he would keep it in port forever.",
    "You can be the ripest, juiciest peach in the world, and there's still going to be somebody who hates peaches."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_dashboard);

        quoteText = findViewById(R.id.quoteText);
        tempDashboardContinue = findViewById(R.id.tempDashboardContinue);
        tempDashboardRe = findViewById(R.id.tempDashboardRe);

        Random random = new Random();
        int n = quotes.length;
        int randomNum = random.nextInt(n);
        String singleQuote = quotes[randomNum];

        quoteText.setText(singleQuote.toString());

        tempDashboardContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempDashboard.this, Dashboard.class);
                startActivity(intent);
            }
        });

        tempDashboardRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int n = quotes.length;
                int randomNum = random.nextInt(n);
                String singleQuote = quotes[randomNum];

                quoteText.setText(singleQuote.toString());
            }
        });

    }

}