package ru.evapps.testtask.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ektitarev on 09/01/2019.
 */

public interface Api {

    @GET("/testTask.json")
    Observable<Object> getEmployees();

}
