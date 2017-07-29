package com.example.android.foodwhips.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.foodwhips.AboutActivity;
import com.example.android.foodwhips.CuisineFilterActivity;
import com.example.android.foodwhips.IngredientFilterActivity;
import com.example.android.foodwhips.MainActivity;
import com.example.android.foodwhips.R;

/* Responsible of keeping the same base layout (Toolbar/Navigation Drawer/Search Bar/Ellipses Menu)
   throughout all activities when navigating through the app.
*/
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    LinearLayout activityContainer;
    DrawerLayout fullView;


    @Override
    public void setContentView(int layoutResID){
        fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        activityContainer = (LinearLayout) fullView.findViewById(R.id.content_frame);

        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);

        //Setup toolbar on the activity
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Setup navigation drawer in activity
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_ellipsis_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.information:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_home) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.drawer_favorites) {

        } else if (id == R.id.drawer_random) {

        } else if (id == R.id.search_name) {

        } else if (id == R.id.search_ingredients) {
            startActivity(new Intent(this, IngredientFilterActivity.class));
        } else if (id == R.id.search_cuisine) {
            startActivity(new Intent(this, CuisineFilterActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
