package com.kiwabolab.ibmreto.modelo;

public class Servidor {
    //----------------------------------------------------------------------------------------------
    //Variables
    public String user = "fa2a09f7-4abd-4fd6-b0a8-a188b8d2f0f1";
    public String pass = "l1vl99WwEF";

    private String urlClima = "";

    //https://fa2a09f7-4abd-4fd6-b0a8-a188b8d2f0f1:l1vl99WwEF
    //@twcservice.mybluemix.net:443/api/weather/v1/geocode/4.716804/-74.036240/observations.json?units=m&language=es-ES
    //----------------------------------------------------------------------------------------------
    //Setters and Getters
    public String getUrlClima(String lat, String lon){
        return "https://@twcservice.mybluemix.net:443/api/weather/v1/geocode/"+lat+"/"+lon+"/observations.json?units=m&language=es-ES";
    }
    private String getUrlAlertas(String lat, String lon){
        return "https://@twcservice.mybluemix.net:443/api/weather/v1/geocode/"+lat+"/"+lon+"/alerts.json?language=es-ES";
    }

}
