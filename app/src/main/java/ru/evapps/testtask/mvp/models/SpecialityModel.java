package ru.evapps.testtask.mvp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ektitarev on 10/01/2019.
 */

public class SpecialityModel {

    @SerializedName("specialty_id")
    private long id;

    @SerializedName("name")
    private String specialityName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }
}
