package com.kiwabolab.ibmreto.vista;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kiwabolab.ibmreto.R;
import com.kiwabolab.ibmreto.modelo.Servidor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reportar extends  Activity {
    //----------------------------------------------------------------------------------------------
    //Variables
    private int REQUEST_IMAGE_CAPTURE = 1;
    private File mPhotoFile = null;
    private ImageView mPhotoView;
    private RequestQueue requestQueue;
    public static final int IMG_HEIGHT = 480;
    public static final int IMG_WIDTH = 800;
    public static final String TMP_IMAGE = "pp.jpg";
    private String encodedImage = "";

    private Servidor servidor;
    private ProgressBar progreso;


    private String level="";
    private final String type ="Accidente";
    private EditText description;
    private Button enviar;

    private ImageView a,b,c;

    private boolean tomofoto= false;

    //-------------GPS
    private String lat ="";
    private String lon = "";
    private LocationManager lm = null;
    //-------------PICTURE
    private Bitmap myBitmap;
    private Uri picUri;
    private final static int ALL_PERMISSIONS_RESULT = 107;
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();

    private View mProviderDetails, contenido;

    //----------------------------------------------------------------------------------------------
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar);

        servidor = new Servidor();
        progreso = (ProgressBar) findViewById(R.id.progreso);
        progreso.getIndeterminateDrawable().setColorFilter(0xffe74c3c, android.graphics.PorterDuff.Mode.MULTIPLY);

        contenido = (View) findViewById(R.id.contenido);

        a =(ImageView)findViewById(R.id.level1);
        b =(ImageView)findViewById(R.id.level2);
        c =(ImageView)findViewById(R.id.level3);

        requestQueue = Volley.newRequestQueue(this);


        description = (EditText)findViewById(R.id.description);
        enviar =(Button)findViewById(R.id.enviar);


        setUsuario();

        mPhotoFile = new File(getExternalCacheDir(), TMP_IMAGE);
        mPhotoView = (ImageView)findViewById(R.id.imagetrafico);
        if (mPhotoFile.exists())
            mPhotoView.setImageBitmap(BitmapFactory.decodeFile(mPhotoFile.getAbsolutePath()));


        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocationGPS();
    }

    public interface OnPermissionRequested {
        void onGranted();
    }


    Reportar.OnPermissionRequested mPermissionRequest;
    // -----------------------------------------------------------------------------------
    //Solicitud de permirmiso a camara
    protected void requestPermission(String permission, Reportar.OnPermissionRequested listener) {

        mPermissionRequest = listener;

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                Toast.makeText(this, "Permission denied, try again later please.", Toast.LENGTH_SHORT).show();
            } else {
                // request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{permission}, 2456);
            }
        } else {
            mPermissionRequest.onGranted();
        }
    }

    // -----------------------------------------------------------------------------------
    //Tomar foto
    public void takePhoto(View view){
        requestPermission(Manifest.permission.CAMERA,
                new Reportar.OnPermissionRequested() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 2);
                    }
                });

    }
    // -----------------------------------------------------------------------------------
    //Respuesta a la accion de tomar una fotografia
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        mPhotoView.setImageBitmap(thumbnail);

        Bitmap bitmap;

        tomofoto = true;
        Bitmap scaledPhoto;

        picUri = getPickImageResultUri(data);

        try {
            myBitmap = thumbnail;
            // myBitmap = rotateImageIfRequired(myBitmap, picUri);
            myBitmap = getResizedBitmap(myBitmap, 600);

            mPhotoView.setImageBitmap(myBitmap);

            //----------
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // scale it down and save it to the same file
            //scaledPhoto = Bitmap.createScaledBitmap(bitmap, IMG_WIDTH, IMG_HEIGHT, true);

            scaledPhoto=myBitmap;
            scaledPhoto.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            byte[] b = baos.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            //----------

        } catch (Exception e) {
            // e.printStackTrace();
            Log.v("ERROR", ""+e);
        }

    }
    // -----------------------------------------------------------------------------------
    //compartir con las redes sociales
    public void compartir2(View v) {
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                new Reportar.OnPermissionRequested() {
                    @Override
                    public void onGranted() {
                        if(tomofoto){
                            sendAll(myBitmap);
                        }else{
                            Toast.makeText(getApplicationContext(), "Debe tomar una fotografía para compartir.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    // -----------------------------------------------------------------------------------
    //Compartir con twitter
    private void sendTweet(Bitmap bitmap) {
        String location = "http://maps.google.com/maps?q=" +lat+","+lon;
        String msg = type +": "+description.getText().toString() +" "+location+" #SimsVias";

        try {
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "fotosimsvias" + ".jpg";

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, msg);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
            intent.setType("image/jpeg");
            intent.setPackage("com.twitter.android");
            startActivity(intent);

            outputStream.close();

        } catch (Throwable e) {
            // Captura los distintos errores que puedan surgir
            e.printStackTrace();
        }
    }
    // -----------------------------------------------------------------------------------
    //compartir con las redes sociales del dispositivo, recibe la imagen para compartir
    private void sendAll(Bitmap bitmap) {
        String location = "http://maps.google.com/maps?q=" +lat+","+lon;
        String msg = type +": "+description.getText().toString() +" "+location+" #SimsVias";

        try {
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "fotosimsvias" + ".jpg";

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, msg);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
            intent.setType("image/jpeg");
            //intent.setPackage("com.twitter.android");
            startActivity(intent);

            outputStream.close();

        } catch (Throwable e) {
            // Captura los distintos errores que puedan surgir
            e.printStackTrace();
        }
    }
    // -----------------------------------------------------------------------------------
    //
    private Bitmap takeScreenshot() {

        try {
            View v1 = contenido;
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getWidth(), v1.getHeight(), Bitmap.Config.ARGB_8888);

            Canvas c = new Canvas(bitmap);

            v1.layout(0, 0, v1.getLayoutParams().width, v1.getLayoutParams().height);
            v1.draw(c);
            v1.setDrawingCacheEnabled(false);
            return bitmap;
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();

            return null;
        }
    }
    //----------------------------------------------------------------------------------------------
    //Carga los datos del usuario
    private void setUsuario(){
    }

    //----------------------------------------------------------------------------------------------
    //
    public void takePhotox(View view) {
        requestPermission(Manifest.permission.CAMERA,
                new Reportar.OnPermissionRequested() {
                    @Override
                    public void onGranted() {
                        // what you want to do
                        startActivityForResult(getPickImageChooserIntent(), 200);
                    }
                });

    }
    //----------------------------------------------------------------------------------------------
    //Sale de la vista actual y retorna al punto de origen
    public void exit(View v){
        finish();
    }

    //----------------------------------------------------------------------------------------------
    //
    public void setLevel1(View v){
        level= "Leve";

    }
    //----------------------------------------------------------------------------------------------
    //
    public void setLevel2(View v){
        level= "Grave";

    }
    //----------------------------------------------------------------------------------------------
    //
    public void setLevel3(View v){
        level= "Muy Grave";
    }

    //----------------------------------------------------------------------------------------------
    //
    public Bitmap getRut(Bitmap bitmap, String path) {

        try {

            ExifInterface ei = new ExifInterface(path);
            //int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            //int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            Log.v("dato es", ": " + orientation);
            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    Log.v("quien:", "me llamo 90");
                    bitmap = rotateImage(bitmap, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    Log.v("quien:", "me llamo 180");
                    bitmap = rotateImage(bitmap, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    Log.v("quien:", "me llamo 270");
                    bitmap = rotateImage(bitmap, 270);
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                    //bitmap = rotateImage(bitmap, 90);
                default:
                    break;

            }
        } catch (Exception ex) {
            Log.v("Error:", "Se presento problema con la ruta imagen");
            return bitmap;
        }
        return bitmap;
    }
    //----------------------------------------------------------------------------------------------
    //
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {

        }
    }

    //----------------------------------------------------------------------------------------------
    //Valida que esten marcadas las opciones del accidente
    private boolean validate(){
        if(level.isEmpty()){
            Toast.makeText(getApplicationContext(), "Debe seleccionar una opción.",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if(lat.isEmpty() || lon.isEmpty()){
            return false;
        }
        if(description.getText().toString().isEmpty()){
            description.setError("Este campo es obligatorio");
            return false;
        }
        return true;
    }

    //----------------------------------------------------------------------------------------------
    //
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
            Location loc = lm
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lat = "" + loc.getLatitude();
            lon = "" + loc.getLongitude();
            Log.v("Informe: ", "Datos del GPS obtenidos correctamente");
        } catch (Exception e) {
            // Log.v("Informe: ", "NO se pudo obtener datos del GPS");
            return false;
        }
        return true;
    }
    //----------------------------------------------------------------------------------------------
    //Envia a la api el reporte del accidente
    public void sendTrafico(View v){
        if (validate()) {
            progreso.setVisibility(View.VISIBLE);
            enviar.setEnabled(false);
            String url = servidor.getSendInforme();
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // response
                    if (response.equals("ok")) {
                        Toast.makeText(getApplicationContext(), "Su reporte se registro exitosamente.",
                                Toast.LENGTH_LONG).show();
                        progreso.setVisibility(View.INVISIBLE);
                        finish();
                        // -------------------->>
                    } else {
                        Toast.makeText(getApplicationContext(), "Error no se pudo enviar el reporte",
                                Toast.LENGTH_LONG).show();
                        enviar.setEnabled(true);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.error_server), Toast.LENGTH_SHORT);
                    toast1.show();
                    progreso.setVisibility(View.INVISIBLE);

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("type", type);
                    params.put("description", description.getText().toString());
                    params.put("level", level);
                    params.put("lat", lat);
                    params.put("lon", lon);
                    if(tomofoto)
                        params.put("image", "data:image/jpeg;base64," + encodedImage);
                    return params;
                }
            };
            postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);
        }
    }
    //----------------------------------------------------------------------------------------------
    //
    /**
     * Create a chooser intent to select the source to get image from.<br/>
     * The source can be camera's (ACTION_IMAGE_CAPTURE) or gallery's (ACTION_GET_CONTENT).<br/>
     * All possible sources are added to the intent chooser.
     */
    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    //----------------------------------------------------------------------------------------------
    //
    /**
     * Get URI to image received from capture by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                Uri chosenImageUri = data.getData();
                bm =  MediaStore.Images.Media.getBitmap(getContentResolver(), chosenImageUri);
                mPhotoView.setImageBitmap(bm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    //Retorno de actividad, consulta la operacion realizada por la actividad reciente
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap bitmap;

        if(resultCode==RESULT_OK){
            if(requestCode==1){
                //onSelectFromGalleryResult(data);
            }else if(requestCode==2){
                onCaptureImageResult(data);
            }
        }

        /*
        if (resultCode == Activity.RESULT_OK) {

            tomofoto = true;
            Bitmap scaledPhoto;


            if (getPickImageResultUri(data) != null) {
                picUri = getPickImageResultUri(data);

                try {
                    myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                    myBitmap = rotateImageIfRequired(myBitmap, picUri);
                    myBitmap = getResizedBitmap(myBitmap, 500);

                    mPhotoView.setImageBitmap(myBitmap);

                    //----------
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    // scale it down and save it to the same file
                    //scaledPhoto = Bitmap.createScaledBitmap(bitmap, IMG_WIDTH, IMG_HEIGHT, true);

                    scaledPhoto=myBitmap;
                    scaledPhoto.compress(Bitmap.CompressFormat.JPEG, 30, baos);
                    byte[] b = baos.toByteArray();
                    encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    //----------

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {


                bitmap = (Bitmap) data.getExtras().get("data");

                myBitmap = bitmap;
                if (mPhotoView != null) {
                    mPhotoView.setImageBitmap(myBitmap);
                }

            }

        }
*/
    }

    private static Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) throws IOException {

        ExifInterface ei = new ExifInterface(selectedImage.getPath());
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    //----------------------------------------------------------------------------------------------
    //Gira una imagen
    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }
    //----------------------------------------------------------------------------------------------
    //modifica el tamaño de una imagen
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    //----------------------------------------------------------------------------------------------
    //
    /**
     * Get the URI of the selected image from {@link #getPickImageChooserIntent()}.<br/>
     * Will return the correct URI for camera and gallery image.
     *
     * @param data the returned data of the activity result
     */
    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }


        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    //----------------------------------------------------------------------------------------------
    //
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("pic_uri", picUri);
    }

    //----------------------------------------------------------------------------------------------
    //
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        picUri = savedInstanceState.getParcelable("pic_uri");
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    //----------------------------------------------------------------------------------------------
    //solicita permiso
    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    //----------------------------------------------------------------------------------------------
    //respuesta a permiso
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    //----------------------------------------------------------------------------------------------
    //consulta version de android
    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    //----------------------------------------------------------------------------------------------
    //resultado de permiso
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (hasPermission(perms)) {

                    } else {

                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                                                //Log.d("API123", "permisionrejected " + permissionsRejected.size());

                                                requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }

    }
}