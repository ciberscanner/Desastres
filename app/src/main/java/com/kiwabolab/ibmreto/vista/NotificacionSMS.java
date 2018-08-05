package com.kiwabolab.ibmreto.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiwabolab.ibmreto.R;

public class NotificacionSMS extends AppCompatActivity {
    //----------------------------------------------------------------------------------------------
    //Variables
    private TextView mensaje;
    private ImageView imagen;
    //----------------------------------------------------------------------------------------------
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notificación SMS");
        setContentView(R.layout.activity_notificacionsms);
        mensaje=(TextView)findViewById(R.id.mensaje);
        MensajeEntrante();

    }
    //----------------------------------------------------------------------------------------------
    //
    private void MensajeEntrante(){
        Intent intent = getIntent();

        if(intent.hasExtra("valor")){
            mensaje.setText(getIntent().getStringExtra("valor"));
        }else{
            mensaje.setText("nada de nada");
            Log.v("Error","mensaje vacio");
        }
    }

    //----------------------------------------------------------------------------------------------
    //
    public void MostrarMapa(View v){
        if(!mensaje.getText().toString().isEmpty()){
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("traza",mensaje.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}
