package ru.evapps.testtask.mvp.presenters;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import javax.inject.Inject;

import ru.evapps.testtask.db.AppDatabase;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    @Inject
    AppDatabase appDatabase;
}
