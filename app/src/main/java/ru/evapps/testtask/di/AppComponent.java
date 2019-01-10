package ru.evapps.testtask.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.evapps.testtask.di.modules.ApiModule;
import ru.evapps.testtask.di.modules.ContextModule;
import ru.evapps.testtask.di.modules.DatabaseModule;
import ru.evapps.testtask.mvp.presenters.EmployeesPresenter;
import ru.evapps.testtask.mvp.presenters.SpecialitiesPresenter;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

@Singleton
@Component(modules = {ApiModule.class, ContextModule.class, DatabaseModule.class})
public interface AppComponent {

    void provideDependency(SpecialitiesPresenter presenter);
    void provideDependency(EmployeesPresenter presenter);
}
