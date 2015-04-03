package ru.avsidorov.github.API;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import ru.avsidorov.github.MODELS.GHRepository;
import ru.avsidorov.github.MODELS.GHUser;

/**
 * Created by Сидоров on 03.04.2015.
 */
public interface ApiGIT {
    @GET("/users/{userName}")
    void getUser(@Path("userName") String user_name, Callback<GHUser> ghUserCallback);

    @GET("users/{userName}/repos")
    ArrayList<GHRepository> getRepository(@Path("userName") String user_name, Callback<GHRepository> repositoryCallback);
}
