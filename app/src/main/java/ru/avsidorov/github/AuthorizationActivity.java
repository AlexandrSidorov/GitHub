package ru.avsidorov.github;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.avsidorov.github.API.ApiGIT;
import ru.avsidorov.github.MODELS.GHUser;


public class AuthorizationActivity extends ActionBarActivity {
    public static final String LOGIN = "LOGIN";
    public static final String PASSWORD = "PASSWORD";
    EditText evLogin;
    EditText evPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        getSupportActionBar().hide();
        evLogin = (EditText) findViewById(R.id.loginEditText);
        evPassword = (EditText) findViewById(R.id.loginEditView);
        if (savedInstanceState != null) {
            evLogin.setText(savedInstanceState.getString(LOGIN));
            evPassword.setText(savedInstanceState.getString(PASSWORD));
        }
        Button btnLogin = (Button) findViewById(R.id.loginButton);
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(this); //костыль какой-то, но контексты через getBaseContext() или getApplicationContext в OnClickListener не принимает
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String login = evLogin.getText().toString();
                final String password = evPassword.getText().toString();
                if (!login.isEmpty() && !password.isEmpty()) {

                    RestAdapter restAdapter = new RestAdapter.Builder()
                            .setRequestInterceptor(new RequestInterceptor() {
                                @Override
                                public void intercept(RequestFacade request) {
                                    final String authorizationValue = encodeCredentialsForBasicAuthorization(login, password);
                                    request.addHeader("Authorization", authorizationValue);

                                }
                            })
                            .setEndpoint(Constants.ENDPOINT)
                            .setLogLevel(RestAdapter.LogLevel.HEADERS).build();
                    ApiGIT apiGIT = restAdapter.create(ApiGIT.class);
                    apiGIT.getUser(login, new Callback<GHUser>() {
                        @Override
                        public void success(GHUser ghUser, Response response) {
                            Intent startIntent = new Intent(AuthorizationActivity.this,
                                    MainActivity.class);
                            startActivity(startIntent);
                            overridePendingTransition(R.anim.activity_from, R.anim.activity_to);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            materialDialog.title(R.string.dialog_error)
                                    .content(error.getLocalizedMessage())
                                    .positiveText(R.string.ok)
                                    .show();
                        }
                    });
                } else {
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
        outState.putString(LOGIN, evLogin.getText().toString());
        outState.putString(PASSWORD, evPassword.getText().toString());

    }


    private String encodeCredentialsForBasicAuthorization(String login, String password) {
        final String userAndPassword = login + ":" + password;
        return "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);
    }

}
