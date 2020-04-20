package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private EditText playerBox;
    private EditText teamBox;
    private EditText rateBox;
    private Button addButton;
    private boolean add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        playerBox = findViewById(R.id.playerBox);
        teamBox = findViewById(R.id.teamBox);
        rateBox = findViewById(R.id.rateBox);
        addButton = findViewById(R.id.addButton);
        Intent i = getIntent();
        add = i.getBooleanExtra("ADD",true);
        if (add) {
            addButton.setText("ADD");
        } else {
            addButton.setText("EDIT");
            playerBox.setText(i.getStringExtra("PLAYER"));
            teamBox.setText(i.getStringExtra("TEAM"));
            rateBox.setText(i.getStringExtra("RATE"));
        }
    }
    public void addPressed(View v) {
        String player = playerBox.getText().toString();
        String team = teamBox.getText().toString();
        String rate = rateBox.getText().toString();
        DatabaseManager dbm = new DatabaseManager(this);
        if (add)
            dbm.insert(player, team, rate);
        else
            dbm.updateByPlayer(player, team);
        finish();
    }
}