
package com.kiwabolab.ibmreto.modelo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClimaAPI implements Serializable
{

    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("observation")
    @Expose
    private Observation observation;
    private final static long serialVersionUID = 5643448431968355335L;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

}
