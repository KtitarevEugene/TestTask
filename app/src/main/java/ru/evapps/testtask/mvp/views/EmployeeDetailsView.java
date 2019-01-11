package ru.evapps.testtask.mvp.views;

import java.util.List;

import ru.evapps.testtask.db.entities.SpecialityEntity;

/**
 * Created by ektitarev on 11/01/2019.
 *
 */

public interface EmployeeDetailsView extends BaseView {
    void showEmployeeFirstLastName(String firstLastName);
    void showEmployeeBirthDay(String birthday);
    void showEmployeeAge(String age);
    void showEmployeeSpecialities(String specialities);
}
