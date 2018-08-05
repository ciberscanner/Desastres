
package com.kiwabolab.ibmreto.modelo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Serializable
{

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("category_cd")
    @Expose
    private Integer categoryCd;
    private final static long serialVersionUID = 8284483982690488896L;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(Integer categoryCd) {
        this.categoryCd = categoryCd;
    }

}
