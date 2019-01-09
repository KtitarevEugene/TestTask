package ru.evapps.testtask.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.evapps.testtask.di.modules.ApiModule;
import ru.evapps.testtask.di.modules.ContextModule;
import ru.evapps.testtask.di.modules.DatabaseModule;

/**
 * Created by ektitarev on 09/01/2019.
 */

@Singleton
@Component(modules = {ApiModule.class, ContextModule.class, DatabaseModule.class})
public interface AppComponent {
}
