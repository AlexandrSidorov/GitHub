package ru.avsidorov.github;

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
import ru.avsidorov.github.MODELS.GHUser;


public class AuthorizationActivity extends ActionBarActivity {
    EditText evLogin;
    EditText evPassword;
    ProgressBarCircularIndeterminate pbCircle;

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

        evLogin = (EditText) findViewById(R.id.loginEditText);
        evPassword = (EditText) findViewById(R.id.loginEditView);
        pbCircle = (ProgressBarCircularIndeterminate) findViewById(R.id.progressBarCircularIndeterminate);
        pbCircle.setVisibility(View.GONE);
        if (savedInstanceState != null) {
            evLogin.setText(savedInstanceState.getString(Constants.LOGIN));
            evPassword.setText(savedInstanceState.getString(Constants.PASSWORD));
        }
        Button btnLogin = (Button) findViewById(R.id.loginButton);
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(this); //костыль какой-то, но контексты через getBaseContext() или getApplicationContext в OnClickListener не принимает
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbCircle.setVisibility(View.VISIBLE);
                final String login = evLogin.getText().toString();
                String password = evPassword.getText().toString();
                if (!login.isEmpty() && !password.isEmpty()) {
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
                            pbCircle.setVisibility(View.GONE);
                            startActivity(startIntent);

                            overridePendingTransition(R.anim.activity_from, R.anim.activity_to);

                        }

                        @Override
                        public void failure(RetrofitError error) { //выводим ошибку, в случае её появления
                            materialDialog.title(R.string.dialog_error)
                                    .content(error.getLocalizedMessage())
                                    .positiveText(R.string.ok)
                                    .show();
                        }
                    });
                } else {
                    pbCircle.setVisibility(View.GONE);
                    materialDialog
                            .title(R.string.dialog_error)
                            .content(R.string.dialog_content_enter_login_and_password)
                            .positiveText(R.string.ok)
                            .show();


                }
            }


        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(Constants.LOGIN, evLogin.getText().toString());
        outState.putString(Constants.PASSWORD, evPassword.getText().toString());

    }

    private String encodeCredentialsForBasicAuthorization(String login, String password) {
        final String userAndPassword = login + ":" + password;
        return "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);
    }

    public String getLoginFromPreference(SharedPreferences preferences) {
        return preferences.getString(Constants.BASE_64, null);
    }

    public void putBase64AuthToSharePreference(SharedPreferences preferences, String base64_string) {
        preferences.edit().putString(Constants.BASE_64, base64_string).apply();


    }

}
