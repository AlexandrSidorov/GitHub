package ru.avsidorov.github.ACTIVITY;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import ru.avsidorov.github.Constants;
import ru.avsidorov.github.FRAGMENTS.RepositoryFragment;
import ru.avsidorov.github.FRAGMENTS.UserFragment;
import ru.avsidorov.github.R;


public class MainActivity extends ActionBarActivity {
    UserFragment userFragment;
    RepositoryFragment repositoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        Drawer.Result result = new Drawer()
                .withActivity(MainActivity.this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.profile).withIdentifier(Constants.PROFILE),
                        new PrimaryDrawerItem().withName(R.string.repository).withIdentifier(Constants.REPOSITORY),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.logout).withIdentifier(Constants.LOGOUT)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {

                        switch (drawerItem.getIdentifier()) {
                            case Constants.PROFILE: {
                                userFragment = new UserFragment();
                                getSupportFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.anim.activity_to, R.anim.activity_from)
                                        .replace(R.id.container, userFragment)
                                        .addToBackStack(Constants.REP_NAME)
                                        .commit();
                                break;
                            }
                            case Constants.REPOSITORY: {
                                repositoryFragment = new RepositoryFragment();
                                getSupportFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.anim.activity_to, R.anim.activity_from)
                                        .replace(R.id.container, repositoryFragment)
                                        .addToBackStack(Constants.REP_NAME)
                                        .commit();
                                break;
                            }

                            case Constants.LOGOUT: {
                                AuthorizationActivity.putBase64AuthToSharePreference(getSharedPreferences(Constants.PREFERENCES, MODE_APPEND), null);
                                finish();
                            }
                        }
                    }
                })
                .build();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new UserFragment())
                    .commit();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
