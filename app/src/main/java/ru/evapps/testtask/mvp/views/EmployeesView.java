package ru.evapps.testtask.mvp.views;

import android.os.Bundle;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.evapps.testtask.db.entities.EmployeeEntity;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

public interface EmployeesView extends BaseView {
    void showEmployees(List<EmployeeEntity> employees);

    @StateStrategyType(SkipStrategy.class)
    void navigateToEmployeeDetails(Bundle args);
}
