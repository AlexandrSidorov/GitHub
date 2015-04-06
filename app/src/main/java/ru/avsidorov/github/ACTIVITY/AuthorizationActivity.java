package ru.avsidorov.github.ACTIVITY;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.avsidorov.github.API.ApiGIT;
import ru.avsidorov.github.Constants;
import ru.avsidorov.github.Dialogs;
import ru.avsidorov.github.MODELS.GHUser;
import ru.avsidorov.github.R;


public class AuthorizationActivity extends ActionBarActivity {
    EditText mLogin;
    EditText mPassword;
    ProgressBarCircularIndeterminate mCircleProgressBar;

    public static RestAdapter getRestAdapterBasicAuth(final String base64) {
        return new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {

                        request.addHeader("Authorization", base64);

                    }
                })
                .setEndpoint(Constants.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.HEADERS).build();
    }

    public static void putBase64AuthToSharePreference(SharedPreferences preferences, String base64_string) {
        preferences.edit().putString(Constants.BASE_64, base64_string).apply();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        if (getLoginFromPreference(getSharedPreferences(Constants.PREFERENCES, MODE_APPEND)) != null) {
            Intent startIntent = new Intent(AuthorizationActivity.this,
                    MainActivity.class);
            startActivity(startIntent);
            overridePendingTransition(R.anim.activity_from, R.anim.activity_to);
        }

        initInterface();
        mCircleProgressBar.setVisibility(View.GONE);

        Button btnLogin = (Button) findViewById(R.id.loginButton);
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(this); //костыль какой-то, но контексты через getBaseContext() или getApplicationContext в OnClickListener не принимает
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleProgressBar.setVisibility(View.VISIBLE);
                final String login = getLogin();
                String password = getPassword();
                if (!login.isEmpty() && !password.isEmpty()) {
                    getUser(login, password);
                } else {
                    mCircleProgressBar.setVisibility(View.INVISIBLE);
                    Dialogs.showDialogNoLogin(AuthorizationActivity.this);


                }
            }


        });
    }

    private void initInterface() {
        mLogin = (EditText) findViewById(R.id.loginEditText);
        mPassword = (EditText) findViewById(R.id.loginEditView);
        mCircleProgressBar = (ProgressBarCircularIndeterminate) findViewById(R.id.progressBarCircularIndeterminate);
    }

    private String getPassword() {
        return mPassword.getText().toString();
    }

    private String getLogin() {
        return mLogin.getText().toString();
    }

    private void getUser(final String login, String password) {

        final String encode = encodeCredentialsForBasicAuthorization(login, password);
        RestAdapter restAdapter = getRestAdapterBasicAuth(encode); //Используется Basic авторизация
        final ApiGIT apiGIT = restAdapter.create(ApiGIT.class);
        apiGIT.getUser(login, new Callback<GHUser>() {// получаем данные пользователя по логину, если пришли то все ок
            @Override
            public void success(GHUser ghUser, Response response) {
                Intent startIntent = new Intent(AuthorizationActivity.this,
                        MainActivity.class);
                putBase64AuthToSharePreference(getSharedPreferences(Constants.PREFERENCES, MODE_APPEND), encode);
                getSharedPreferences(Constants.PREFERENCES, MODE_APPEND).edit().putString(Constants.USER_NAME, login).apply();
                mCircleProgressBar.setVisibility(View.GONE);
                startActivity(startIntent);

                overridePendingTransition(R.anim.activity_from, R.anim.activity_to);

            }

            @Override
            public void failure(RetrofitError error) { //выводим ошибку, в случае её появления
                Dialogs.showDialog(AuthorizationActivity.this, error);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    private String encodeCredentialsForBasicAuthorization(String login, String password) {
        final String userAndPassword = login + ":" + password;
        return "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);
    }

    public String getLoginFromPreference(SharedPreferences preferences) {
        return preferences.getString(Constants.BASE_64, null);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
