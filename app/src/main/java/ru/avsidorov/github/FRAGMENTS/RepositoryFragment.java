package ru.avsidorov.github.FRAGMENTS;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.avsidorov.github.API.ApiGIT;
import ru.avsidorov.github.Adapters.RepositoryAdapter;
import ru.avsidorov.github.Api;
import ru.avsidorov.github.Constants;
import ru.avsidorov.github.Dialogs;
import ru.avsidorov.github.MODELS.GHRepositoryFull;
import ru.avsidorov.github.R;

/**
 * Created by Сидоров on 03.04.2015.
 */
public class RepositoryFragment extends Fragment {

    Toolbar mToolbarTop;
    RepositoryAdapter mRepositoryAdapter;
    ListView mRepositoryListView;
    ArrayList<GHRepositoryFull> mRepositoryArrayList;
    ApiGIT apiGIT;
    ProgressBarCircularIndeterminate mCircleProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository, container, false);
        mCircleProgressBar = (ProgressBarCircularIndeterminate) view.findViewById(R.id.progressBarCircularRepository);
        mToolbarTop = (Toolbar) view.findViewById(R.id.toolbar_top);
        mToolbarTop.setTitle(R.string.repository);
        mRepositoryListView = (ListView) view.findViewById(R.id.repositoryListView);
        mRepositoryArrayList = new ArrayList<GHRepositoryFull>();
        Api api = new Api();
        apiGIT = api.getRestAdapter(getActivity()).create(ApiGIT.class);
        String login = getActivity().getSharedPreferences(Constants.PREFERENCES, Activity.MODE_APPEND).getString(Constants.USER_NAME, null);
        mCircleProgressBar.setVisibility(View.VISIBLE);
        apiGIT.getRepositoryFull(login, new Callback<ArrayList<GHRepositoryFull>>() {
            @Override
            public void success(ArrayList<GHRepositoryFull> ghRepositories, Response response) {
                mRepositoryArrayList.addAll(ghRepositories); // в данном случае вызывается один раз поэтому задвоения данных не будет, кнопки обновить нет
                mRepositoryAdapter = new RepositoryAdapter(getActivity(), R.layout.row_repos, ghRepositories);
                mRepositoryListView.setAdapter(mRepositoryAdapter);
                mCircleProgressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void failure(RetrofitError error) {
                Dialogs.showDialog(getActivity(), error);
                mCircleProgressBar.setVisibility(View.INVISIBLE);
            }

        });
        mRepositoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommitsFragment commitsFragment = new CommitsFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.REP_NAME, mRepositoryArrayList.get(position).getName());
                commitsFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.activity_to, R.anim.activity_from)
                        .replace(R.id.container, commitsFragment)
                        .addToBackStack(Constants.REP_NAME)
                        .commit()

                ;

            }
        });

        return view;

    }


}
