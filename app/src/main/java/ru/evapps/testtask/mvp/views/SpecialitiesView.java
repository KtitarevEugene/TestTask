package ru.evapps.testtask.mvp.views;

import android.os.Bundle;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.evapps.testtask.db.entities.SpecialityEntity;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

public interface SpecialitiesView extends BaseView {
    void showSpecialities(List<SpecialityEntity> specialities);

    @StateStrategyType(SkipStrategy.class)
    void navigateToEmployeesList(Bundle args);
}
