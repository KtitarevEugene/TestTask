package ru.evapps.testtask.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import dagger.Module;
import ru.evapps.testtask.db.AppDatabase;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

@Module(includes = {ContextModule.class})
public class DatabaseModule {

    private static final String DATABASE_NAME = "app_database";

    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }
}
