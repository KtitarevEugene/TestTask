package ru.evapps.testtask.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.evapps.testtask.db.entities.EmployeeEntity;
import ru.evapps.testtask.db.entities.EmployeeSpecialityJoinEntity;
import ru.evapps.testtask.db.entities.SpecialityEntity;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

@Dao
public interface EmployeeSpecialityJoinDao {

    @Query("select * from Employees inner join EmployeeSpecialityJoin on Employees.id=EmployeeSpecialityJoin.employeeId where EmployeeSpecialityJoin.specialityId=:specialityId")
    Single<List<EmployeeEntity>> getEmployeesBySpecialityId(long specialityId);

    @Query("select * from Specialities inner join EmployeeSpecialityJoin on Specialities.id=EmployeeSpecialityJoin.specialityId where EmployeeSpecialityJoin.employeeId=:employeeId")
    Single<List<SpecialityEntity>> getSpecialitiesByEmployeeId(long employeeId);

    @Query("select * from EmployeeSpecialityJoin")
    Single<List<EmployeeSpecialityJoinEntity>> getAllJoins();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<EmployeeSpecialityJoinEntity> entities);
}
