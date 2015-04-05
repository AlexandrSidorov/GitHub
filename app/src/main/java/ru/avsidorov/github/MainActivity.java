package ru.avsidorov.github;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import ru.avsidorov.github.MODELS.GHUser;


public class MainActivity extends ActionBarActivity {
    GHUser user;
    RepositoryFragment repositoryFragment = new RepositoryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /**AccountHeader.Result headerResult = new AccountHeader()
         .withActivity(MainActivity.this)
         .build();*/


//Now create your drawer and pass the AccountHeader.Result
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
                        // do something with the clicked item :D
                        switch (drawerItem.getIdentifier()) {
                            case 1: {
                                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.activity_from, R.anim.activity_to).replace(R.id.container, new UserFragment()).commit();
                                break;
                            }
                            case Constants.REPOSITORY: {
                                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.activity_from, R.anim.activity_to).replace(R.id.container, new RepositoryFragment()).commit();
                                break;
                            }

                            case Constants.LOGOUT: {
                                AuthorizationActivity.putBase64AuthToSharePreference(getSharedPreferences(Constants.PREFERENCES, MODE_APPEND), null);
                                startActivity(new Intent(MainActivity.this, AuthorizationActivity.class));

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
