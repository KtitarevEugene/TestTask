package ru.evapps.testtask.mvp.views;

import java.util.List;

import ru.evapps.testtask.db.entities.SpecialityEntity;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

public interface SpecialitiesView extends BaseView {
    void showSpecialities(List<SpecialityEntity> specialities);
}
