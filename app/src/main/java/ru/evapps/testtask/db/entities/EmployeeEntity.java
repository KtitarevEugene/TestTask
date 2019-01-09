package ru.evapps.testtask.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

@Entity(tableName = "Employees")
public class EmployeeEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "f_name")
    private String firstName;

    @ColumnInfo(name = "l_name")
    private String lastName;

    @ColumnInfo(name = "birthday")
    private String birthday;

    @ColumnInfo(name = "avatar_url")
    private String avatarUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}
