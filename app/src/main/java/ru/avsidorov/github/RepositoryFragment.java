package ru.avsidorov.github;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.avsidorov.github.API.ApiGIT;
import ru.avsidorov.github.Adapters.RepositoryAdapter;
import ru.avsidorov.github.MODELS.GHRepository;

/**
 * Created by Сидоров on 03.04.2015.
 */
public class RepositoryFragment extends Fragment {

    Toolbar toolbar_top;
    RepositoryAdapter adapter;
    ListView reposListView;
    ArrayList<GHRepository> ghRepository;
    ApiGIT apiGIT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repository, container, false);

        toolbar_top = (Toolbar) rootView.findViewById(R.id.toolbar_top);
        toolbar_top.setTitle(R.string.repository);
        reposListView = (ListView) rootView.findViewById(R.id.repositoryListView);
        if (ghRepository != null || adapter != null) {
            reposListView.setAdapter(adapter);
            synchronized (adapter) {
                adapter.notify();
            }

        } else {
            ghRepository = new ArrayList<GHRepository>();
        }
        Api api = Api.getInstance();
        apiGIT = api.getRestAdapter(getActivity()).create(ApiGIT.class);
        String login = getActivity().getSharedPreferences(Constants.PREFERENCES, Activity.MODE_APPEND).getString(Constants.USER_NAME, null);

        apiGIT.getRepository(login, new Callback<ArrayList<GHRepository>>() {
            @Override
            public void success(ArrayList<GHRepository> ghRepositories, Response response) {
                ghRepository.addAll(ghRepositories); // в данном случае вызывается один раз поэтому задвоения данных не будет, кнопки обновить нет
                adapter = new RepositoryAdapter(getActivity(), R.layout.row_repos, ghRepositories);
                reposListView.setAdapter(adapter);


            }

            @Override
            public void failure(RetrofitError error) {

            }

        });
        reposListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommitsFragment commitsFragment = new CommitsFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.REP_NAME, ghRepository.get(position).getName());
                commitsFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.activity_to, R.anim.activity_from)
                        .replace(R.id.container, commitsFragment).addToBackStack("repository_stack")
                        .commit()
                ;

            }
        });

        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
