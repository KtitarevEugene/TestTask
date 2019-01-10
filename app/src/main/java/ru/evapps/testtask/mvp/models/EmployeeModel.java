package ru.evapps.testtask.mvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

public class EmployeeModel {

    private long id;

    @SerializedName("f_name")
    private String firstName;

    @SerializedName("l_name")
    private String lastName;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("avatr_url")
    private String avatarUrl;

    @SerializedName("specialty")
    private List<SpecialityModel> specialities;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<SpecialityModel> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<SpecialityModel> specialties) {
        this.specialities = specialties;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
