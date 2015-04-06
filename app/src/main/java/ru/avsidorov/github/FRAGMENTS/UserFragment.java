package ru.avsidorov.github.FRAGMENTS;


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
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.avsidorov.github.API.ApiGIT;
import ru.avsidorov.github.Api;
import ru.avsidorov.github.Constants;
import ru.avsidorov.github.Dialogs;
import ru.avsidorov.github.MODELS.GHUser;
import ru.avsidorov.github.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends android.support.v4.app.Fragment {
    ImageView mAvatar;
    TextView mName;
    TextView mEmail;
    TextView mFollowers;
    TextView mPublicRepositories;
    TextView mFollowing;
    TextView mLocation;

    ProgressBarCircularIndeterminate mCircleProgressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);


        mAvatar = (ImageView) view.findViewById(R.id.userPhotoFragment);
        mCircleProgressBar = (ProgressBarCircularIndeterminate) view.findViewById(R.id.progressBarCircular);
        mCircleProgressBar.setVisibility(View.INVISIBLE);
        mName = (TextView) view.findViewById(R.id.userNameFrag);
        mEmail = (TextView) view.findViewById(R.id.userEmailFrag);
        mFollowers = (TextView) view.findViewById(R.id.followersCountTextView);
        mFollowing = (TextView) view.findViewById(R.id.followingCountTextView);
        mPublicRepositories = (TextView) view.findViewById(R.id.publicRepoCountTextView);
        mLocation = (TextView) view.findViewById(R.id.locationTextView);


        Api api = new Api();
        ApiGIT apiGIT = api.getRestAdapter(getActivity()).create(ApiGIT.class);
        String login = getActivity().getSharedPreferences(Constants.PREFERENCES, Activity.MODE_APPEND).getString(Constants.USER_NAME, null);
        mCircleProgressBar.setVisibility(View.VISIBLE);
        apiGIT.getUser(login, new Callback<GHUser>() {
            @Override
            public void success(GHUser ghUser, Response response) {
                Picasso.with(getActivity().getBaseContext()).load(ghUser.getAvatarUrl()).fit().centerInside().into(mAvatar);
                mName.setText(ghUser.getName());
                if (ghUser.getEmail().isEmpty()) {
                    mEmail.setText(R.string.noemail);
                } else mEmail.setText(ghUser.getEmail());

                mFollowers.setText(String.valueOf(ghUser.getFollowers()));
                mFollowing.setText(String.valueOf(ghUser.getFollowing()));
                mPublicRepositories.setText(String.valueOf(ghUser.getPublicRepos()));
                mLocation.setText(ghUser.getLocation());
                mCircleProgressBar.setVisibility(View.INVISIBLE);


            }

            @Override
            public void failure(RetrofitError error) {
                Dialogs.showDialog(getActivity(), error);
                mCircleProgressBar.setVisibility(View.INVISIBLE);

            }
        });
        return view;


    }


}
