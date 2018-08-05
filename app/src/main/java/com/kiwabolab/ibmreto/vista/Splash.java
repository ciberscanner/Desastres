package com.kiwabolab.ibmreto.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    //----------------------------------------------------------------------------------------------
    //Variables

    //----------------------------------------------------------------------------------------------
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, Presentation.class);
        startActivity(intent);
        finish();
    }
}