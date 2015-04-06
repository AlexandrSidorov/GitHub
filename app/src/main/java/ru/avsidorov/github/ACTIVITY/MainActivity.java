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
    UserFragment mUserFragment;
    RepositoryFragment mRepositoryFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        createNavigationDrawer(toolbar);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new UserFragment())
                    .commit();
        }


    }

    private void createNavigationDrawer(Toolbar toolbar) {
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
                                startUserFragment();
                                break;
                            }
                            case Constants.REPOSITORY: {
                                startRepositoryFragment();
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
    }

    private void startRepositoryFragment() {
        mRepositoryFragment = new RepositoryFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.activity_to, R.anim.activity_from)
                .replace(R.id.container, mRepositoryFragment)
                .addToBackStack(Constants.REP_NAME)
                .commit();
    }

    private void startUserFragment() {
        mUserFragment = new UserFragment();

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.activity_to, R.anim.activity_from)
                .replace(R.id.container, mUserFragment)
                .addToBackStack(Constants.REP_NAME)
                .commit();
    }


}
