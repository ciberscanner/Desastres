
package com.kiwabolab.ibmreto.modelo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Observation implements Serializable
{

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("expire_time_gmt")
    @Expose
    private String expireTimeGmt;
    @SerializedName("obs_id")
    @Expose
    private String obsId;
    @SerializedName("obs_name")
    @Expose
    private String obsName;
    @SerializedName("valid_time_gmt")
    @Expose
    private String validTimeGmt;
    @SerializedName("day_ind")
    @Expose
    private String dayInd;
    @SerializedName("temp")
    @Expose
    private String temp;
    @SerializedName("wx_icon")
    @Expose
    private String wxIcon;
    @SerializedName("icon_extd")
    @Expose
    private String iconExtd;
    @SerializedName("wx_phrase")
    @Expose
    private String wxPhrase;
    @SerializedName("pressure_tend")
    @Expose
    private String pressureTend;
    @SerializedName("pressure_desc")
    @Expose
    private String pressureDesc;
    @SerializedName("dewPt")
    @Expose
    private String dewPt;
    @SerializedName("heat_index")
    @Expose
    private String heatIndex;
    @SerializedName("rh")
    @Expose
    private String rh;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("vis")
    @Expose
    private String vis;
    @SerializedName("wc")
    @Expose
    private String wc;
    @SerializedName("wdir")
    @Expose
    private String wdir;
    @SerializedName("wdir_cardinal")
    @Expose
    private String wdirCardinal;
    @SerializedName("gust")
    @Expose
    private Object gust;
    @SerializedName("wspd")
    @Expose
    private String wspd;
    @SerializedName("max_temp")
    @Expose
    private Object maxTemp;
    @SerializedName("min_temp")
    @Expose
    private Object minTemp;
    @SerializedName("precip_total")
    @Expose
    private Object precipTotal;
    @SerializedName("precip_hrly")
    @Expose
    private Object precipHrly;
    @SerializedName("snow_hrly")
    @Expose
    private Object snowHrly;
    @SerializedName("uv_desc")
    @Expose
    private String uvDesc;
    @SerializedName("feels_like")
    @Expose
    private String feelsLike;
    @SerializedName("uv_index")
    @Expose
    private String uvIndex;
    @SerializedName("qualifier")
    @Expose
    private Object qualifier;
    @SerializedName("qualifier_svrty")
    @Expose
    private Object qualifierSvrty;
    @SerializedName("blunt_phrase")
    @Expose
    private Object bluntPhrase;
    @SerializedName("terse_phrase")
    @Expose
    private Object tersePhrase;
    @SerializedName("clds")
    @Expose
    private String clds;
    @SerializedName("water_temp")
    @Expose
    private Object waterTemp;
    @SerializedName("primary_wave_period")
    @Expose
    private Object primaryWavePeriod;
    @SerializedName("primary_wave_height")
    @Expose
    private Object primaryWaveHeight;
    @SerializedName("primary_swell_period")
    @Expose
    private Object primarySwellPeriod;
    @SerializedName("primary_swell_height")
    @Expose
    private Object primarySwellHeight;
    @SerializedName("primary_swell_direction")
    @Expose
    private Object primarySwellDirection;
    @SerializedName("secondary_swell_period")
    @Expose
    private Object secondarySwellPeriod;
    @SerializedName("secondary_swell_height")
    @Expose
    private Object secondarySwellHeight;
    @SerializedName("secondary_swell_direction")
    @Expose
    private Object secondarySwellDirection;
    private final static long serialVersionUID = 7049937791887421347L;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getExpireTimeGmt() {
        return expireTimeGmt;
    }

    public void setExpireTimeGmt(String expireTimeGmt) {
        this.expireTimeGmt = expireTimeGmt;
    }

    public String getObsId() {
        return obsId;
    }

    public void setObsId(String obsId) {
        this.obsId = obsId;
    }

    public String getObsName() {
        return obsName;
    }

    public void setObsName(String obsName) {
        this.obsName = obsName;
    }

    public String getValidTimeGmt() {
        return validTimeGmt;
    }

    public void setValidTimeGmt(String validTimeGmt) {
        this.validTimeGmt = validTimeGmt;
    }

    public String getDayInd() {
        return dayInd;
    }

    public void setDayInd(String dayInd) {
        this.dayInd = dayInd;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWxIcon() {
        return wxIcon;
    }

    public void setWxIcon(String wxIcon) {
        this.wxIcon = wxIcon;
    }

    public String getIconExtd() {
        return iconExtd;
    }

    public void setIconExtd(String iconExtd) {
        this.iconExtd = iconExtd;
    }

    public String getWxPhrase() {
        return wxPhrase;
    }

    public void setWxPhrase(String wxPhrase) {
        this.wxPhrase = wxPhrase;
    }

    public String getPressureTend() {
        return pressureTend;
    }

    public void setPressureTend(String pressureTend) {
        this.pressureTend = pressureTend;
    }

    public String getPressureDesc() {
        return pressureDesc;
    }

    public void setPressureDesc(String pressureDesc) {
        this.pressureDesc = pressureDesc;
    }

    public String getDewPt() {
        return dewPt;
    }

    public void setDewPt(String dewPt) {
        this.dewPt = dewPt;
    }

    public String getHeatIndex() {
        return heatIndex;
    }

    public void setHeatIndex(String heatIndex) {
        this.heatIndex = heatIndex;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getWc() {
        return wc;
    }

    public void setWc(String wc) {
        this.wc = wc;
    }

    public String getWdir() {
        return wdir;
    }

    public void setWdir(String wdir) {
        this.wdir = wdir;
    }

    public String getWdirCardinal() {
        return wdirCardinal;
    }

    public void setWdirCardinal(String wdirCardinal) {
        this.wdirCardinal = wdirCardinal;
    }

    public Object getGust() {
        return gust;
    }

    public void setGust(Object gust) {
        this.gust = gust;
    }

    public String getWspd() {
        return wspd;
    }

    public void setWspd(String wspd) {
        this.wspd = wspd;
    }

    public Object getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Object maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Object getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Object minTemp) {
        this.minTemp = minTemp;
    }

    public Object getPrecipTotal() {
        return precipTotal;
    }

    public void setPrecipTotal(Object precipTotal) {
        this.precipTotal = precipTotal;
    }

    public Object getPrecipHrly() {
        return precipHrly;
    }

    public void setPrecipHrly(Object precipHrly) {
        this.precipHrly = precipHrly;
    }

    public Object getSnowHrly() {
        return snowHrly;
    }

    public void setSnowHrly(Object snowHrly) {
        this.snowHrly = snowHrly;
    }

    public String getUvDesc() {
        return uvDesc;
    }

    public void setUvDesc(String uvDesc) {
        this.uvDesc = uvDesc;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Object getQualifier() {
        return qualifier;
    }

    public void setQualifier(Object qualifier) {
        this.qualifier = qualifier;
    }

    public Object getQualifierSvrty() {
        return qualifierSvrty;
    }

    public void setQualifierSvrty(Object qualifierSvrty) {
        this.qualifierSvrty = qualifierSvrty;
    }

    public Object getBluntPhrase() {
        return bluntPhrase;
    }

    public void setBluntPhrase(Object bluntPhrase) {
        this.bluntPhrase = bluntPhrase;
    }

    public Object getTersePhrase() {
        return tersePhrase;
    }

    public void setTersePhrase(Object tersePhrase) {
        this.tersePhrase = tersePhrase;
    }

    public String getClds() {
        return clds;
    }

    public void setClds(String clds) {
        this.clds = clds;
    }

    public Object getWaterTemp() {
        return waterTemp;
    }

    public void setWaterTemp(Object waterTemp) {
        this.waterTemp = waterTemp;
    }

    public Object getPrimaryWavePeriod() {
        return primaryWavePeriod;
    }

    public void setPrimaryWavePeriod(Object primaryWavePeriod) {
        this.primaryWavePeriod = primaryWavePeriod;
    }

    public Object getPrimaryWaveHeight() {
        return primaryWaveHeight;
    }

    public void setPrimaryWaveHeight(Object primaryWaveHeight) {
        this.primaryWaveHeight = primaryWaveHeight;
    }

    public Object getPrimarySwellPeriod() {
        return primarySwellPeriod;
    }

    public void setPrimarySwellPeriod(Object primarySwellPeriod) {
        this.primarySwellPeriod = primarySwellPeriod;
    }

    public Object getPrimarySwellHeight() {
        return primarySwellHeight;
    }

    public void setPrimarySwellHeight(Object primarySwellHeight) {
        this.primarySwellHeight = primarySwellHeight;
    }

    public Object getPrimarySwellDirection() {
        return primarySwellDirection;
    }

    public void setPrimarySwellDirection(Object primarySwellDirection) {
        this.primarySwellDirection = primarySwellDirection;
    }

    public Object getSecondarySwellPeriod() {
        return secondarySwellPeriod;
    }

    public void setSecondarySwellPeriod(Object secondarySwellPeriod) {
        this.secondarySwellPeriod = secondarySwellPeriod;
    }

    public Object getSecondarySwellHeight() {
        return secondarySwellHeight;
    }

    public void setSecondarySwellHeight(Object secondarySwellHeight) {
        this.secondarySwellHeight = secondarySwellHeight;
    }

    public Object getSecondarySwellDirection() {
        return secondarySwellDirection;
    }

    public void setSecondarySwellDirection(Object secondarySwellDirection) {
        this.secondarySwellDirection = secondarySwellDirection;
    }

}
