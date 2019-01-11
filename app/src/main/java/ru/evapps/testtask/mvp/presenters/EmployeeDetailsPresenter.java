package ru.evapps.testtask.mvp.presenters;

import android.content.Context;
import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.evapps.testtask.App;
import ru.evapps.testtask.R;
import ru.evapps.testtask.common.Constants;
import ru.evapps.testtask.common.Utils;
import ru.evapps.testtask.db.entities.EmployeeEntity;
import ru.evapps.testtask.db.entities.SpecialityEntity;
import ru.evapps.testtask.mvp.views.EmployeeDetailsView;

/**
 * Created by ektitarev on 11/01/2019.
 *
 */

@InjectViewState
public class EmployeeDetailsPresenter extends BasePresenter<EmployeeDetailsView> {

    @Inject
    Context context;

    private long employeeId = -1;

    public EmployeeDetailsPresenter(Bundle args) {
        App.getAppComponent().provideDependency(this);

        if (args != null) {
            employeeId = args.getLong(Constants.EMPLOYEE_ID, -1);
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadEmployeeDetails();
    }

    private void loadEmployeeDetails() {
        if (employeeId != -1) {
            appDatabase.employeesDao().getEmployeeById(employeeId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(d -> getViewState().showProgressBar(true))
                    .subscribe(employeeEntity -> {
                        getViewState().showProgressBar(false);
                        showDetails(employeeEntity);
                        loadEmployeeSpecialities();
                    }, error -> {
                        getViewState().showProgressBar(false);
                        getViewState().showErrorMessage();
                    });
        }
    }

    private void loadEmployeeSpecialities() {
        appDatabase.employeeSpecialityJoinDao().getSpecialitiesByEmployeeId(employeeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::showSpecialities,
                        error -> getViewState().showErrorMessage()
                );
    }

    private void showSpecialities(List<SpecialityEntity> specialities) {
        if (!specialities.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append(specialities.get(0).getName());

            if (specialities.size() > 1) {
                for (int i = 1; i < specialities.size(); ++i) {
                    builder.append(", ");
                    builder.append(specialities.get(i).getName());
                }

                getViewState().showEmployeeSpecialities(
                        String.format(context.getString(R.string.employee_specialities),
                                builder.toString()));
            } else {
                getViewState().showEmployeeSpecialities(
                        String.format(context.getString(R.string.employee_speciality),
                                builder.toString()));
            }
        }
    }

    private void showDetails(EmployeeEntity employee) {
        String birthdayString = employee.getBirthday();
        String formattedFirlsLastName = String.format(context.getString(R.string.first_last_names),
                Utils.upperCaseFirstLetter(employee.getFirstName()),
                Utils.upperCaseFirstLetter(employee.getLastName()));

        getViewState().showEmployeeFirstLastName(formattedFirlsLastName);

        if (birthdayString != null && !birthdayString.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat(context.getString(R.string.birthday_format), Locale.getDefault());

            Date birthdayDate = Utils.stringToDate(birthdayString);

            getViewState().showEmployeeBirthDay(format.format(birthdayDate));

            String formattedAgeString = String.format(context.getString(R.string.age_format), Utils.formatAge(context, Utils.calcAge(birthdayDate)));

            getViewState().showEmployeeAge(formattedAgeString);
        } else {
            getViewState().showEmployeeBirthDay(context.getString(R.string.empty_birthday_format));
            getViewState().showEmployeeAge(context.getString(R.string.empty_age_format));
        }
    }
}
