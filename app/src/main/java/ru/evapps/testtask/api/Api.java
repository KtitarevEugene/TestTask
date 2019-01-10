package ru.evapps.testtask.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.evapps.testtask.mvp.models.EmployeeModel;
import ru.evapps.testtask.mvp.models.ResponseModel;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

public interface Api {

    @GET("65gb/static/raw/master/testTask.json")
    Observable<ResponseModel<List<EmployeeModel>>> getEmployees();

}
