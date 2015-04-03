package ru.avsidorov.github;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.avsidorov.github.API.ApiGIT;
import ru.avsidorov.github.MODELS.GHUser;


public class MainActivity extends ActionBarActivity {
    GHUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        String base64 = getSharedPreferences(Constants.PREFERENCES, MODE_APPEND).getString(Constants.BASE_64, null);

                        request.addHeader("Authorization", base64);

                    }
                })
                .setEndpoint(Constants.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.HEADERS).build();
        ApiGIT apiGIT = restAdapter.create(ApiGIT.class);
        String login = getSharedPreferences(Constants.PREFERENCES, MODE_APPEND).getString(Constants.USER_NAME, null);
        apiGIT.getUser(login, new Callback<GHUser>() {
            @Override
            public void success(GHUser ghUser, Response response) {
                user = ghUser;
                toolbar.setTitle(ghUser.getName());
                toolbar.setSubtitle(ghUser.getEmail());

                AccountHeader.Result headerResult = new AccountHeader()
                        .withActivity(MainActivity.this)
                        .build();


//Now create your drawer and pass the AccountHeader.Result
                Drawer.Result result = new Drawer()
                        .withActivity(MainActivity.this)
                        .withAccountHeader(headerResult)
                        .withToolbar(toolbar)
                        .withTranslucentStatusBar(false)
                        .withActionBarDrawerToggle(true)
                        .addDrawerItems(
                                new PrimaryDrawerItem().withName(R.string.repository),

                                new PrimaryDrawerItem().withName(R.string.settings),
                                new DividerDrawerItem(),
                                new PrimaryDrawerItem().withName(R.string.logout)
                        )
                        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                                // do something with the clicked item :D
                            }
                        })
                        .build();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new RepositoryFragment())
                    .commit();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



}
