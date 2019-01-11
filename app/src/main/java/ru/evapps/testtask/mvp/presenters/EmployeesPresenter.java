package ru.evapps.testtask.mvp.presenters;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.evapps.testtask.App;
import ru.evapps.testtask.common.Constants;
import ru.evapps.testtask.db.entities.EmployeeEntity;
import ru.evapps.testtask.mvp.views.EmployeesView;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

@InjectViewState
public class EmployeesPresenter extends BasePresenter<EmployeesView> {

    private long specialityId = -1;

    public EmployeesPresenter(Bundle args) {
        App.getAppComponent().provideDependency(this);

        if (args != null) {
            specialityId = args.getLong(Constants.SPECIALITY_ID, -1);
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadEmployees();
    }

    private void loadEmployees() {
        if (specialityId != -1) {
            appDatabase.employeeSpecialityJoinDao().getEmployeesBySpecialityId(specialityId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(d -> getViewState().showProgressBar(true))
                    .subscribe(employeeEntities -> {
                        getViewState().showProgressBar(false);
                        getViewState().showEmployees(employeeEntities);
                    }, error -> {
                        getViewState().showProgressBar(false);
                        getViewState().showErrorMessage();
                    });
        }
    }

    public void showEmployeeDetails(EmployeeEntity employee, int position) {
        Bundle args = new Bundle();
        args.putLong(Constants.EMPLOYEE_ID, employee.getId());

        getViewState().navigateToEmployeeDetails(args);
    }
}
