package ru.evapps.testtask.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.evapps.testtask.db.entities.SpecialityEntity;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

@Dao
public interface SpecialitiesDao {

    @Query("select * from Specialities")
    Single<List<SpecialityEntity>> getAllSpecialities();

    @Query("select * from Specialities where id = :id")
    Single<SpecialityEntity> getSpecialityById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(SpecialityEntity... model);
}
