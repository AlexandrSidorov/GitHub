package ru.avsidorov.github.API;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import ru.avsidorov.github.MODELS.GHCommits;
import ru.avsidorov.github.MODELS.GHRepository;
import ru.avsidorov.github.MODELS.GHUser;

/**
 * Created by Сидоров on 03.04.2015.
 */
public interface ApiGIT {
    @GET("/users/{userName}")
    void getUser(@Path("userName") String user_name, Callback<GHUser> ghUserCallback);

    @GET("/users/{userName}/repos")
    void getRepository(@Path("userName") String user_name, Callback<ArrayList<GHRepository>> repositoryCallback);

    @GET("")
    void getCommits(@Path("sha") String sha, Callback<ArrayList<GHCommits>> commitsCallback);
}
