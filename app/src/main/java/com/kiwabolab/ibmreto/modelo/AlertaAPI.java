
package com.kiwabolab.ibmreto.modelo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlertaAPI implements Serializable
{

    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("alerts")
    @Expose
    private List<Alert> alerts = null;
    private final static long serialVersionUID = 9107279484412967627L;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

}
