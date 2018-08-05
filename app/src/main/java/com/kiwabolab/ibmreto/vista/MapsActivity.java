package com.kiwabolab.ibmreto.vista;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kiwabolab.ibmreto.R;
import com.kiwabolab.ibmreto.util.PermissionUtils;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMyLocationClickListener, LocationListener,
        ActivityCompat.OnRequestPermissionsResultCallback {
    //----------------------------------------------------------------------------------------------
    //Variables
    private LocationManager lm = null;
    double mLatitude=0;
    double mLongitude=0;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;


    private GoogleMap mMap;

    //----------------------------------------------------------------------------------------------
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    //----------------------------------------------------------------------------------------------
    //
    private void MensajeEntrante(){
        Intent intent = getIntent();

        if(intent.hasExtra("traza")){
            String traza=getIntent().getStringExtra("traza");

            String icono=""+traza.substring(0,3);
            traza=traza.substring(4,traza.length());
            String mensaje= ""+traza.substring(0,traza.indexOf('*'));

            traza = traza.substring(traza.indexOf('*'),traza.length());

            String lat=traza.substring(1,traza.indexOf(','));
            String lon =traza.substring(traza.indexOf(',')+1,traza.length());

            Log.v("Esto es ", icono +"/"+mensaje+"/"+lat+"/"+lon);


            LatLng MELBOURNE = new LatLng(Float.valueOf(lat),Float.valueOf(lon));
            Marker melbourne = mMap.addMarker(new MarkerOptions()
                    .position(MELBOURNE)
                    .title("Refugio")
                    .snippet(mensaje)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin_hospital)));

            //mMap.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(lat), Float.valueOf(lon))).title(mensaje));

        }else{
            Marcas();
        }
    }
    //----------------------------------------------------------------------------------------------
    //
    private void Marcas(){
         LatLng MELBOURNE = new LatLng(4.683225,-74.055277);
         Marker melbourne = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE)
                .title("Inundación")
                .snippet("Población afectada: 0")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin_fuegoinundacion)));

        LatLng MELBOURNE2 = new LatLng(4.687012, -74.033567);

        Marker melbourne2 = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE2)
                .title("Incendio Forestal")
                .snippet("Población afectada: 22")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin_fuego)));

        LatLng MELBOURNE3 = new LatLng(4.716804, -74.036240);

        Marker melbourne3 = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE3)
                .title("Deslizamientos")
                .snippet("Población afectada: 50")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin_fuegoderrumbe)));


        LatLng MELBOURNE4 = new LatLng(4.653032, -74.050085);
        Marker melbourne4 = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE4)
                .title("Terremoto")
                .snippet("Población afecta: 137,400")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin_sismo)));

    }
    //----------------------------------------------------------------------------------------------
    //
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng colombia = new LatLng(4.713982, -73.184969);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(colombia));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );

        //mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);

        enableMyLocation();
        getLocationGPS();

        MensajeEntrante();
    }
    //----------------------------------------------------------------------------------------------
    //
    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    //----------------------------------------------------------------------------------------------
    /** Get latitude and longitude of GPS_PROVIDER */
    private boolean getLocationGPS() {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                return true;
            }
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location loc = lm
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);

            LatLng coordinate = new LatLng(loc.getLatitude(),loc.getLongitude());
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 8);
            mMap.animateCamera(yourLocation);

            Log.v("Informe: ", "Datos del GPS obtenidos correctamente");



            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            String provider = lm.getBestProvider(criteria, true);

            // Getting Current Location From GPS
            Location location = lm.getLastKnownLocation(provider);

            if(location!=null){
                onLocationChanged(location);
            }

            //lm.requestLocationUpdates(provider, 20000, 0, this);


        } catch (Exception e) {
            // Log.v("Informe: ", "NO se pudo obtener datos del GPS");
            return false;
        }
        return true;
    }
    //----------------------------------------------------------------------------------------------
    //Recalcula la posicion del usuario si este se desplaza
    @Override
    public void onLocationChanged(Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
        LatLng latLng = new LatLng(mLatitude, mLongitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));

    }
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
    //----------------------------------------------------------------------------------------------
    //Muestra la informacion de la posicion del usuario al presionar el pin de su ubicacion
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Mi Posición:\n" + location, Toast.LENGTH_LONG).show();
    }
}
