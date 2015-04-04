package ru.avsidorov.github;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.avsidorov.github.API.ApiGIT;
import ru.avsidorov.github.MODELS.GHUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends android.support.v4.app.Fragment {
    ImageView imageView;
    TextView textView;
    TextView textView2;

    ProgressBarCircularIndeterminate pbCircle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        imageView = (ImageView) view.findViewById(R.id.userPhotoFragment);
        textView = (TextView) view.findViewById(R.id.userNameFrag);
        textView2 = (TextView) view.findViewById(R.id.userEmailFrag);

        return view;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        pbCircle.setVisibility(View.GONE);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        String base64 = getActivity().getSharedPreferences(Constants.PREFERENCES, Activity.MODE_APPEND).getString(Constants.BASE_64, null);

                        request.addHeader("Authorization", base64);

                    }
                })
                .setEndpoint(Constants.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.HEADERS).build();
        ApiGIT apiGIT = restAdapter.create(ApiGIT.class);
        String login = getActivity().getSharedPreferences(Constants.PREFERENCES, Activity.MODE_APPEND).getString(Constants.USER_NAME, null);

        apiGIT.getUser(login, new Callback<GHUser>() {
            @Override
            public void success(GHUser ghUser, Response response) {
                Picasso.with(getActivity().getBaseContext()).load(ghUser.getAvatarUrl()).fit().centerInside().into(imageView);
                textView.setText(ghUser.getName());
                textView2.setText(ghUser.getCompany());


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
