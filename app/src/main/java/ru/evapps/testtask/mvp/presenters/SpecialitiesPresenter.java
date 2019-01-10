package ru.evapps.testtask.mvp.presenters;

import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.evapps.testtask.App;
import ru.evapps.testtask.api.Api;
import ru.evapps.testtask.common.Constants;
import ru.evapps.testtask.db.entities.EmployeeEntity;
import ru.evapps.testtask.db.entities.EmployeeSpecialityJoinEntity;
import ru.evapps.testtask.db.entities.SpecialityEntity;
import ru.evapps.testtask.mvp.models.EmployeeModel;
import ru.evapps.testtask.mvp.models.SpecialityModel;
import ru.evapps.testtask.mvp.views.SpecialitiesView;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

@InjectViewState
public class SpecialitiesPresenter extends BasePresenter<SpecialitiesView> {

    private static final String TAG = SpecialitiesPresenter.class.getSimpleName();

    @Inject
    Api api;

    private SharedPreferences prefs;

    public SpecialitiesPresenter(SharedPreferences prefs) {
        App.getAppComponent().provideDependency(this);

        this.prefs = prefs;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();

    }

    private void loadData() {
        if (!isDataLoaded()) {
            api.getEmployees()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> getViewState().showProgressBar(true))
                    .subscribe(responseModel -> {
                        getViewState().showProgressBar(false);
                        if (responseModel != null) {
                            List<EmployeeModel> employeeModels = responseModel.getResponse();
                            if (employeeModels != null && !employeeModels.isEmpty())
                                addEmployees(employeeModels);
                        }
                    }, error -> {
                        getViewState().showProgressBar(false);
                        getViewState().showErrorMessage();
                    });
        } else {
            getSpecialities();
        }
    }

    private boolean isDataLoaded() {
        return prefs != null && prefs.getBoolean(Constants.IS_DATA_LOADED, false);
    }

    private void addEmployees(List<EmployeeModel> employees) {
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        for (EmployeeModel employeeModel : employees) {
            EmployeeEntity employeeEntity = new EmployeeEntity();

            employeeEntity.setFirstName(employeeModel.getFirstName());
            employeeEntity.setLastName(employeeModel.getLastName());
            employeeEntity.setBirthday(employeeModel.getBirthday());
            employeeEntity.setAvatarUrl(employeeModel.getAvatarUrl());

            employeeEntities.add(employeeEntity);
        }

        Single.fromCallable(() -> appDatabase.employeesDao()
                .insert(employeeEntities.toArray(new EmployeeEntity [employeeEntities.size()])))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(disposable -> getViewState().showProgressBar(true))
            .subscribe(ids -> {
                Log.d(TAG, "inserted");
                setEmployeesTableIds(ids, employees);
                addSpecialities(employees);
            }, error -> {
                getViewState().showProgressBar(false);
                getViewState().showErrorMessage();
            });
    }

    private void setEmployeesTableIds(List<Long> ids, List<EmployeeModel> employees) {
        int indexOfIds = 0;
        for (EmployeeModel model : employees) {
            model.setId(ids.get(indexOfIds));
            indexOfIds++;
        }
    }

    private void addSpecialities(List<EmployeeModel> employees) {
        List<SpecialityEntity> specialityEntities = new ArrayList<>();
        for (EmployeeModel employeeModel : employees) {
            List<SpecialityModel> specialities = employeeModel.getSpecialities();
            if (specialities != null && !specialities.isEmpty()) {
                for (SpecialityModel speciality : specialities) {
                    SpecialityEntity specialityEntity = new SpecialityEntity();

                    specialityEntity.setId(speciality.getId());
                    specialityEntity.setName(speciality.getSpecialityName());

                    specialityEntities.add(specialityEntity);
                }
            }
        }

        Single.fromCallable(() -> appDatabase.specialitiesDao()
                .insert(specialityEntities.toArray(new SpecialityEntity[specialityEntities.size()])))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(specialitiesTableIds -> {
                addEmployeeSpecialitiyJoins(employees);
            }, error -> {
                getViewState().showProgressBar(false);
                getViewState().showErrorMessage();
            });
    }

    private void addEmployeeSpecialitiyJoins(List<EmployeeModel> employees) {
        List<EmployeeSpecialityJoinEntity> joins = new ArrayList<>();
        for (EmployeeModel employee : employees) {
            List<SpecialityModel> specialities = employee.getSpecialities();
            if (specialities != null && !specialities.isEmpty()) {
                for (SpecialityModel speciality : specialities) {
                    EmployeeSpecialityJoinEntity joinEntity = new EmployeeSpecialityJoinEntity();

                    joinEntity.setEmployeeId(employee.getId());
                    joinEntity.setSpecialityId(speciality.getId());

                    joins.add(joinEntity);
                }
            }
        }

        Single.fromCallable(() -> appDatabase.employeeSpecialityJoinDao().insert(joins))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(joinsIds -> {
                    setDataLoaded(true);
                    getViewState().showProgressBar(false);
                    getSpecialities();
                }, error -> {
                    getViewState().showProgressBar(false);
                    getViewState().showErrorMessage();
                });
    }

    private void getSpecialities() {
        appDatabase.specialitiesDao().getAllSpecialities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showProgressBar(true))
                .subscribe(specialities -> {
                    getViewState().showProgressBar(false);
                    getViewState().showSpecialities(specialities);
                }, error -> {
                    getViewState().showProgressBar(false);
                    getViewState().showErrorMessage();
                });
    }

    private void setDataLoaded(boolean isLoaded) {
        if (prefs != null) {
            prefs.edit()
                    .putBoolean(Constants.IS_DATA_LOADED, isLoaded)
                    .apply();
        }
    }
}
