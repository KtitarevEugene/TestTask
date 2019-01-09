package ru.evapps.testtask.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

@Entity(tableName = "EmployeeSpecialityJoin",
        primaryKeys = {"employeeId", "specialityId"},
        foreignKeys = {
            @ForeignKey(entity = EmployeeEntity.class, parentColumns = {"id"}, childColumns = {"employeeId"}),
            @ForeignKey(entity = SpecialityEntity.class, parentColumns = {"id"}, childColumns = {"specialityId"}),
        })
public class EmployeeSpecialityJoinEntity {
    private long employeeId;
    private long specialityId;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(long specialityId) {
        this.specialityId = specialityId;
    }
}
