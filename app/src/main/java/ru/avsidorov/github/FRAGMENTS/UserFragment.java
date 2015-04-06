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
    ImageView avatarImageView;
    TextView userName;
    TextView userEmail;
    TextView followerCount;
    TextView publicRepoCount;
    TextView followingCount;
    TextView location;

    ProgressBarCircularIndeterminate pbCircle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);


        avatarImageView = (ImageView) view.findViewById(R.id.userPhotoFragment);
        pbCircle = (ProgressBarCircularIndeterminate) view.findViewById(R.id.progressBarCircular);
        pbCircle.setVisibility(View.INVISIBLE);
        userName = (TextView) view.findViewById(R.id.userNameFrag);
        userEmail = (TextView) view.findViewById(R.id.userEmailFrag);
        followerCount = (TextView) view.findViewById(R.id.followersCountTextView);
        followingCount = (TextView) view.findViewById(R.id.followingCountTextView);
        publicRepoCount = (TextView) view.findViewById(R.id.publicRepoCountTextView);
        location = (TextView) view.findViewById(R.id.locationTextView);


        Api api = new Api();
        ApiGIT apiGIT = api.getRestAdapter(getActivity()).create(ApiGIT.class);
        String login = getActivity().getSharedPreferences(Constants.PREFERENCES, Activity.MODE_APPEND).getString(Constants.USER_NAME, null);
        pbCircle.setVisibility(View.VISIBLE);
        apiGIT.getUser(login, new Callback<GHUser>() {
            @Override
            public void success(GHUser ghUser, Response response) {
                Picasso.with(getActivity().getBaseContext()).load(ghUser.getAvatarUrl()).fit().centerInside().into(avatarImageView);
                userName.setText(ghUser.getName());
                if (ghUser.getEmail().isEmpty()) {
                    userEmail.setText(R.string.noemail);
                } else userEmail.setText(ghUser.getEmail());

                followerCount.setText(String.valueOf(ghUser.getFollowers()));
                followingCount.setText(String.valueOf(ghUser.getFollowing()));
                publicRepoCount.setText(String.valueOf(ghUser.getPublicRepos()));
                location.setText(ghUser.getLocation());
                pbCircle.setVisibility(View.INVISIBLE);


            }

            @Override
            public void failure(RetrofitError error) {
                Dialogs.showDialog(getActivity(), error);
                pbCircle.setVisibility(View.INVISIBLE);

            }
        });
        return view;


    }


}
