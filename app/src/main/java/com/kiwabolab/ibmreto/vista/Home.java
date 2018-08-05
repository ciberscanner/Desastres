package com.kiwabolab.ibmreto.vista;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kiwabolab.ibmreto.R;
import com.kiwabolab.ibmreto.modelo.AlertaAPI;
import com.kiwabolab.ibmreto.modelo.ClimaAPI;
import com.kiwabolab.ibmreto.modelo.Servidor;
import com.kiwabolab.ibmreto.util.SmsBroadcastReceiver;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //----------------------------------------------------------------------------------------------
    //Variables
    public static final String OTP_REGEX = "[0-9]{1,6}";
    private SmsBroadcastReceiver smsBroadcastReceiver;

     int SMS_PERMISSION_CODE = 1001;

     private TextView mensaje;


    private final IntentFilter intentFilter = new IntentFilter();
    WifiP2pManager.Channel mChannel;
    WifiP2pManager mManager;


    private RequestQueue requestQueue;
    private Servidor servidor;

    private TextView ciudad,temperatura, uv, descripcion;
    private TextView alerta;
    private ImageView icono1, icono2;

    //----------------------------------------------------------------------------------------------
    //Constructor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Activaste el modo busqueda", Snackbar.LENGTH_LONG)
                        .setAction("Acción", null).show();


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        servidor= new Servidor();
        SetRecursoClima();

        requestQueue = Volley.newRequestQueue(this);
        getReport();
        getAlertasClima();


    }
    //----------------------------------------------------------------------------------------------
    //
    private void SetRecursoClima(){
        ciudad =(TextView)findViewById(R.id.txt_ciudad);
        temperatura =(TextView)findViewById(R.id.txt_temperatura);
        icono1=(ImageView)findViewById(R.id.image_icon1);
        descripcion=(TextView)findViewById(R.id.txt_descripcion);
        uv=(TextView)findViewById(R.id.txt_uv);

        alerta =(TextView)findViewById(R.id.txt_tituloalerta);

    }
    //----------------------------------------------------------------------------------------------
    //solicita a la api el reporte del clima
    private void getReport(){
        String lat="4.716804";
        String lon="-74.036240";
        String json= servidor.getUrlClima(lat,lon);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, json,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.v("Respuesta", response);
                        if (!response.isEmpty()) {

                                Gson gson = new Gson();
                                ClimaAPI reports = gson.fromJson(response, ClimaAPI.class);

                                if (!reports.getObservation().getObsName().isEmpty()) {
                                    //Log.v("MENSAJE",response);
                                    MostrarEstadoClima(reports);
                                } else {
                                    Toast.makeText(getApplicationContext(), "No hay registros para tu posición",
                                            Toast.LENGTH_LONG).show();
                                }

                        } else {
                            Toast.makeText(getApplicationContext(), "Error en la conexión",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network),
                        Toast.LENGTH_SHORT).show();
                Log.v("Respuesta: ", "error");
                //getUserString = "";
            }
        }

        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                String creds = String.format("%s:%s",servidor.user,servidor.pass);
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;

            }
        } ;
        requestQueue.add(stringRequest);
    }

    //----------------------------------------------------------------------------------------------
    //solicita a la api el reporte del clima
    private void getAlertasClima(){
        String lat="4.716804";
        String lon="-74.036240";
        String json= servidor.getUrlClima(lat,lon);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, json,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.v("Respuesta", response);
                        if (!response.isEmpty()) {

                            Gson gson = new Gson();
                            AlertaAPI reports = gson.fromJson(response, AlertaAPI.class);

                            if (reports.getMetadata().getStatusCode().equals("200")) {
                                //Log.v("MENSAJE",response);
                                MostrarAlertas(reports);
                            } else {
                                Toast.makeText(getApplicationContext(), "No hay registros para tu posición",
                                        Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Error en la conexión",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network),
                        Toast.LENGTH_SHORT).show();
                Log.v("Respuesta: ", "error");
                //getUserString = "";
            }
        }

        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                String creds = String.format("%s:%s",servidor.user,servidor.pass);
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;

            }
        } ;
        requestQueue.add(stringRequest);
    }
    //----------------------------------------------------------------------------------------------
    //
    private void MostrarAlertas(AlertaAPI alertas){
        Log.v("RESPONDE",alertas.getMetadata().getStatusCode());
        if(alertas.getAlerts()== null){
            alerta.setText("No hay Alertas en su Área");
        }else{
            alerta.setText("Se Registran las Siguientes alertas");
        }
    }

    //----------------------------------------------------------------------------------------------
    //
    private void MostrarEstadoClima(ClimaAPI clima){
        ciudad.setText(clima.getObservation().getObsName());
        temperatura.setText("Temperatura: "+clima.getObservation().getTemp()+"Cº");
        descripcion.setText("Descripción: "+clima.getObservation().getWxPhrase());
        uv.setText("Exposición Ultravioleta: "+clima.getObservation().getUvDesc());

    }
    //----------------------------------------------------------------------------------------------
    //
    private void ConectarRed(Context context){

    }
    //----------------------------------------------------------------------------------------------
    //

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, Prevencion.class);
            startActivity(intent);

        }else if (id == R.id.nav_auxilios) {
            Intent intent = new Intent(this, PrimerosAuxilios.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //----------------------------------------------------------------------------------------------
    //

}
