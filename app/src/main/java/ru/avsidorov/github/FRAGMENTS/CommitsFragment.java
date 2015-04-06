package ru.avsidorov.github.FRAGMENTS;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.avsidorov.github.API.ApiGIT;
import ru.avsidorov.github.Adapters.CommitsAdapter;
import ru.avsidorov.github.Api;
import ru.avsidorov.github.Constants;
import ru.avsidorov.github.Dialogs;
import ru.avsidorov.github.MODELS.GHCommit;
import ru.avsidorov.github.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommitsFragment extends android.support.v4.app.Fragment {
    private Toolbar mToolbarTop;
    private ListView mCommitsListView;
    private ApiGIT apiGIT;
    private CommitsAdapter mCommitsAdapter;
    private ProgressBarCircularIndeterminate mCircleProgressBar;

    public CommitsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commits, container, false);
        mToolbarTop = (Toolbar) view.findViewById(R.id.toolbar_top);
        mToolbarTop.setTitle(R.string.commits);
        mCommitsListView = (ListView) view.findViewById(R.id.commitsListView);
        Api api = new Api();
        apiGIT = api.getRestAdapter(getActivity()).create(ApiGIT.class);
        final String login = getActivity().getSharedPreferences(Constants.PREFERENCES, Activity.MODE_APPEND).getString(Constants.USER_NAME, null);
        mCircleProgressBar = (ProgressBarCircularIndeterminate) view.findViewById(R.id.progressBarCircularCommits);
        mCircleProgressBar.setVisibility(View.VISIBLE);


        apiGIT.getCommits(login, getArguments().getString(Constants.REP_NAME), new Callback<ArrayList<GHCommit>>() {
            @Override
            public void success(ArrayList<GHCommit> ghCommits, Response response) {

                mCommitsAdapter = new CommitsAdapter(getActivity(), R.layout.row_repos, ghCommits);
                mCommitsListView.setAdapter(mCommitsAdapter);
                mCircleProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                Dialogs.showDialog(getActivity(), error);
                mCircleProgressBar.setVisibility(View.INVISIBLE);

            }
        });
        // ArrayList<GHCommit> ghCommits = apiGIT.getCommits(login,getArguments().getString(Constants.REP_NAME));


        return view;
    }


}
