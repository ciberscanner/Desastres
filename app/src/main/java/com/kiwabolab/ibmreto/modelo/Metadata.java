
package com.kiwabolab.ibmreto.modelo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata implements Serializable
{

    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("units")
    @Expose
    private String units;
    @SerializedName("expire_time_gmt")
    @Expose
    private String expireTimeGmt;
    @SerializedName("status_code")
    @Expose
    private String statusCode;
    private final static long serialVersionUID = 3306388739195107828L;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getExpireTimeGmt() {
        return expireTimeGmt;
    }

    public void setExpireTimeGmt(String expireTimeGmt) {
        this.expireTimeGmt = expireTimeGmt;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}
