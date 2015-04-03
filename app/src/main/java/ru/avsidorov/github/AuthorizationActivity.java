package ru.avsidorov.github;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        getSupportActionBar().hide();
        final EditText evLogin = (EditText) findViewById(R.id.loginEditText);
        final EditText evPassword = (EditText) findViewById(R.id.loginEditView);
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
                            materialDialog.title("Ура").content(ghUser.getName()).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            materialDialog.title("Ошибка").content(error.getLocalizedMessage()).show();
                        }
                    });
                } else {
                    materialDialog
                            .title(R.string.dialog_title_no_login_or_password)
                            .content(R.string.dialog_content_enter_login_and_password)
                            .positiveText(R.string.ok)
                            .show();


                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_authorization, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String encodeCredentialsForBasicAuthorization(String login, String password) {
        final String userAndPassword = login + ":" + password;
        return "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);
    }

}
