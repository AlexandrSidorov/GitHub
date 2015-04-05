package ru.avsidorov.github.API;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import ru.avsidorov.github.MODELS.GHCommit;
import ru.avsidorov.github.MODELS.GHRepository;
import ru.avsidorov.github.MODELS.GHRepositoryFull;
import ru.avsidorov.github.MODELS.GHUser;

/**
 * Created by Сидоров on 03.04.2015.
 */
public interface ApiGIT {
    @GET("/users/{userName}")
    void getUser(@Path("userName") String user_name, Callback<GHUser> ghUserCallback);

    @GET("/users/{userName}/repos")
    void getRepository(@Path("userName") String user_name, Callback<ArrayList<GHRepository>> repositoryCallback);

    @GET("/users/{userName}/repos")
    void getRepositoryFull(@Path("userName") String user_name, Callback<ArrayList<GHRepositoryFull>> repositoryCallback);

    @GET("/repos/{userName}/{repoName}/commits")
    void getCommits(@Path("userName") String userName, @Path("repoName") String repositoryName, Callback<ArrayList<GHCommit>> commitsCallback);

    @GET("/repos/{userName}/{repoName}/commits")
    ArrayList<GHCommit> getCommits(@Path("userName") String userName, @Path("repoName") String repositoryName);

    @GET("/repos/{userName}/{repoName}/commits")
    void getResponseCommits(@Path("userName") String userName, @Path("repoName") String repositoryName, Callback<Response> responseCallback);
}
