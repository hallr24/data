package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    private TextView playerView;
    private TextView teamView;
    private TextView rateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        playerView = findViewById(R.id.playerView);
        teamView = findViewById(R.id.teamView);
        rateView = findViewById(R.id.rateView);

        DatabaseManager dbm = new DatabaseManager(this);
        Intent i = getIntent();
        String player = i.getStringExtra("PLAYER");
        String[] entry = dbm.get(player);
        playerView.setText(entry[0]);
        teamView.setText(entry[1]);
        rateView.setText(entry[2]);

    }

    public void editPressed(View v) {
        Intent i = new Intent(this, AddActivity.class);
        i.putExtra("ADD", false);
        i.putExtra("PLAYER", playerView.getText().toString());
        i.putExtra("TEAM", teamView.getText().toString());
        i.putExtra("RATE", rateView.getText().toString());
        startActivity(i);
    }


}
