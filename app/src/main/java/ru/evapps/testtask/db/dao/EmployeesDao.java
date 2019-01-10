package ru.evapps.testtask.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.evapps.testtask.db.entities.EmployeeEntity;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

@Dao
public interface EmployeesDao {

    @Query("select * from Employees")
    Single<List<EmployeeEntity>> getAllEmployees();

    @Query("select * from Employees where id = :id")
    Single<EmployeeEntity> getEmployeeById (int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert (EmployeeEntity... model);
}
