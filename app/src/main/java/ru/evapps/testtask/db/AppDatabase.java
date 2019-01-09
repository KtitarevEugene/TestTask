package ru.evapps.testtask.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.evapps.testtask.db.dao.EmployeeSpecialityJoinDao;
import ru.evapps.testtask.db.dao.EmployeesDao;
import ru.evapps.testtask.db.dao.SpecialitiesDao;
import ru.evapps.testtask.db.entities.EmployeeEntity;
import ru.evapps.testtask.db.entities.EmployeeSpecialityJoinEntity;
import ru.evapps.testtask.db.entities.SpecialityEntity;

/**
 * Created by ektitarev on 09/01/2019.
 */

@Database(version = 1, entities = {EmployeeEntity.class, SpecialityEntity.class, EmployeeSpecialityJoinEntity.class}, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EmployeesDao employeesDao();

    public abstract EmployeeSpecialityJoinDao employeeSpecialityJoinDao();

    public abstract SpecialitiesDao specialitiesDao();
}
