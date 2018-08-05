
package com.kiwabolab.ibmreto.modelo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseType implements Serializable
{

    @SerializedName("response_type")
    @Expose
    private String responseType;
    @SerializedName("response_type_cd")
    @Expose
    private Integer responseTypeCd;
    private final static long serialVersionUID = -7275378970728332949L;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public Integer getResponseTypeCd() {
        return responseTypeCd;
    }

    public void setResponseTypeCd(Integer responseTypeCd) {
        this.responseTypeCd = responseTypeCd;
    }

}
