
package com.kiwabolab.ibmreto.modelo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alert implements Serializable
{

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("msg_type_cd")
    @Expose
    private Integer msgTypeCd;
    @SerializedName("msg_type")
    @Expose
    private String msgType;
    @SerializedName("pil")
    @Expose
    private String pil;
    @SerializedName("phenomena")
    @Expose
    private String phenomena;
    @SerializedName("significance")
    @Expose
    private String significance;
    @SerializedName("etn")
    @Expose
    private String etn;
    @SerializedName("office_cd")
    @Expose
    private String officeCd;
    @SerializedName("office_name")
    @Expose
    private String officeName;
    @SerializedName("office_st_cd")
    @Expose
    private String officeStCd;
    @SerializedName("office_cntry_cd")
    @Expose
    private String officeCntryCd;
    @SerializedName("event_desc")
    @Expose
    private String eventDesc;
    @SerializedName("severity_cd")
    @Expose
    private Integer severityCd;
    @SerializedName("severity")
    @Expose
    private String severity;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("response_types")
    @Expose
    private List<ResponseType> responseTypes = null;
    @SerializedName("urgency")
    @Expose
    private String urgency;
    @SerializedName("urgency_cd")
    @Expose
    private Integer urgencyCd;
    @SerializedName("certainty")
    @Expose
    private String certainty;
    @SerializedName("certainty_cd")
    @Expose
    private Integer certaintyCd;
    @SerializedName("effective_dt_tm_local")
    @Expose
    private String effectiveDtTmLocal;
    @SerializedName("effective_dt_tm_tz_abbrv")
    @Expose
    private String effectiveDtTmTzAbbrv;
    @SerializedName("expire_dt_tm_local")
    @Expose
    private String expireDtTmLocal;
    @SerializedName("expire_dt_tm_tz_abbrv")
    @Expose
    private String expireDtTmTzAbbrv;
    @SerializedName("expire_time_gmt")
    @Expose
    private Integer expireTimeGmt;
    @SerializedName("onset_dt_tm_local")
    @Expose
    private String onsetDtTmLocal;
    @SerializedName("onset_dt_tm_tz_abbrv")
    @Expose
    private String onsetDtTmTzAbbrv;
    @SerializedName("flood")
    @Expose
    private Object flood;
    @SerializedName("area_type")
    @Expose
    private String areaType;
    @SerializedName("lat")
    @Expose
    private Float lat;
    @SerializedName("lon")
    @Expose
    private Float lon;
    @SerializedName("area_id")
    @Expose
    private String areaId;
    @SerializedName("area_name")
    @Expose
    private String areaName;
    @SerializedName("st_cd")
    @Expose
    private String stCd;
    @SerializedName("st_name")
    @Expose
    private String stName;
    @SerializedName("cntry_cd")
    @Expose
    private String cntryCd;
    @SerializedName("cntry_name")
    @Expose
    private String cntryName;
    @SerializedName("headline_text")
    @Expose
    private String headlineText;
    @SerializedName("detail_key")
    @Expose
    private String detailKey;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("disclaimer")
    @Expose
    private Object disclaimer;
    @SerializedName("issue_dt_tm_local")
    @Expose
    private String issueDtTmLocal;
    @SerializedName("issue_dt_tm_tz_abbrv")
    @Expose
    private String issueDtTmTzAbbrv;
    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("proc_dt_tm_local")
    @Expose
    private String procDtTmLocal;
    @SerializedName("proc_dt_tm_tz_abbrv")
    @Expose
    private String procDtTmTzAbbrv;
    private final static long serialVersionUID = 8278123891394397805L;

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

    public Integer getMsgTypeCd() {
        return msgTypeCd;
    }

    public void setMsgTypeCd(Integer msgTypeCd) {
        this.msgTypeCd = msgTypeCd;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getPil() {
        return pil;
    }

    public void setPil(String pil) {
        this.pil = pil;
    }

    public String getPhenomena() {
        return phenomena;
    }

    public void setPhenomena(String phenomena) {
        this.phenomena = phenomena;
    }

    public String getSignificance() {
        return significance;
    }

    public void setSignificance(String significance) {
        this.significance = significance;
    }

    public String getEtn() {
        return etn;
    }

    public void setEtn(String etn) {
        this.etn = etn;
    }

    public String getOfficeCd() {
        return officeCd;
    }

    public void setOfficeCd(String officeCd) {
        this.officeCd = officeCd;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeStCd() {
        return officeStCd;
    }

    public void setOfficeStCd(String officeStCd) {
        this.officeStCd = officeStCd;
    }

    public String getOfficeCntryCd() {
        return officeCntryCd;
    }

    public void setOfficeCntryCd(String officeCntryCd) {
        this.officeCntryCd = officeCntryCd;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public Integer getSeverityCd() {
        return severityCd;
    }

    public void setSeverityCd(Integer severityCd) {
        this.severityCd = severityCd;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<ResponseType> getResponseTypes() {
        return responseTypes;
    }

    public void setResponseTypes(List<ResponseType> responseTypes) {
        this.responseTypes = responseTypes;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public Integer getUrgencyCd() {
        return urgencyCd;
    }

    public void setUrgencyCd(Integer urgencyCd) {
        this.urgencyCd = urgencyCd;
    }

    public String getCertainty() {
        return certainty;
    }

    public void setCertainty(String certainty) {
        this.certainty = certainty;
    }

    public Integer getCertaintyCd() {
        return certaintyCd;
    }

    public void setCertaintyCd(Integer certaintyCd) {
        this.certaintyCd = certaintyCd;
    }

    public String getEffectiveDtTmLocal() {
        return effectiveDtTmLocal;
    }

    public void setEffectiveDtTmLocal(String effectiveDtTmLocal) {
        this.effectiveDtTmLocal = effectiveDtTmLocal;
    }

    public String getEffectiveDtTmTzAbbrv() {
        return effectiveDtTmTzAbbrv;
    }

    public void setEffectiveDtTmTzAbbrv(String effectiveDtTmTzAbbrv) {
        this.effectiveDtTmTzAbbrv = effectiveDtTmTzAbbrv;
    }

    public String getExpireDtTmLocal() {
        return expireDtTmLocal;
    }

    public void setExpireDtTmLocal(String expireDtTmLocal) {
        this.expireDtTmLocal = expireDtTmLocal;
    }

    public String getExpireDtTmTzAbbrv() {
        return expireDtTmTzAbbrv;
    }

    public void setExpireDtTmTzAbbrv(String expireDtTmTzAbbrv) {
        this.expireDtTmTzAbbrv = expireDtTmTzAbbrv;
    }

    public Integer getExpireTimeGmt() {
        return expireTimeGmt;
    }

    public void setExpireTimeGmt(Integer expireTimeGmt) {
        this.expireTimeGmt = expireTimeGmt;
    }

    public String getOnsetDtTmLocal() {
        return onsetDtTmLocal;
    }

    public void setOnsetDtTmLocal(String onsetDtTmLocal) {
        this.onsetDtTmLocal = onsetDtTmLocal;
    }

    public String getOnsetDtTmTzAbbrv() {
        return onsetDtTmTzAbbrv;
    }

    public void setOnsetDtTmTzAbbrv(String onsetDtTmTzAbbrv) {
        this.onsetDtTmTzAbbrv = onsetDtTmTzAbbrv;
    }

    public Object getFlood() {
        return flood;
    }

    public void setFlood(Object flood) {
        this.flood = flood;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStCd() {
        return stCd;
    }

    public void setStCd(String stCd) {
        this.stCd = stCd;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getCntryCd() {
        return cntryCd;
    }

    public void setCntryCd(String cntryCd) {
        this.cntryCd = cntryCd;
    }

    public String getCntryName() {
        return cntryName;
    }

    public void setCntryName(String cntryName) {
        this.cntryName = cntryName;
    }

    public String getHeadlineText() {
        return headlineText;
    }

    public void setHeadlineText(String headlineText) {
        this.headlineText = headlineText;
    }

    public String getDetailKey() {
        return detailKey;
    }

    public void setDetailKey(String detailKey) {
        this.detailKey = detailKey;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Object getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(Object disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getIssueDtTmLocal() {
        return issueDtTmLocal;
    }

    public void setIssueDtTmLocal(String issueDtTmLocal) {
        this.issueDtTmLocal = issueDtTmLocal;
    }

    public String getIssueDtTmTzAbbrv() {
        return issueDtTmTzAbbrv;
    }

    public void setIssueDtTmTzAbbrv(String issueDtTmTzAbbrv) {
        this.issueDtTmTzAbbrv = issueDtTmTzAbbrv;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getProcDtTmLocal() {
        return procDtTmLocal;
    }

    public void setProcDtTmLocal(String procDtTmLocal) {
        this.procDtTmLocal = procDtTmLocal;
    }

    public String getProcDtTmTzAbbrv() {
        return procDtTmTzAbbrv;
    }

    public void setProcDtTmTzAbbrv(String procDtTmTzAbbrv) {
        this.procDtTmTzAbbrv = procDtTmTzAbbrv;
    }

}
