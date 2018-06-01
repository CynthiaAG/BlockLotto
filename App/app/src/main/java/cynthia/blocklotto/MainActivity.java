package cynthia.blocklotto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment_my_lotteries()).commit();

      /*  FloatingActionButton fab = findViewById(R.id.notify);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment_notification()).commit();
                //fragmentManager.beginTransaction().replace(R.id.lottoWallet, new Fragment_notification()).commit();
            }
        });*/



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        getSupportActionBar().setTitle("Mis sorteos pendientes");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (id == R.id.my_lotteries) {
            getSupportActionBar().setTitle("Mis sorteos pendientes");

            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment_my_lotteries()).commit();

        } else if (id == R.id.future_lottery) {
            getSupportActionBar().setTitle("Próximos sorteos");

            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment_next_lotteries()).commit();

        } else if (id == R.id.history_lottery) {
            getSupportActionBar().setTitle("Sorteos celebrados");

            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment_history_lotteries()).commit();

        } else if (id == R.id.my_wallet) {
            getSupportActionBar().setTitle("Mi Wallet");
            /*View sorteos = findViewById(R.id.lotteries);
            sorteos.setVisibility(View.INVISIBLE);
            View lotto= findViewById(R.id.lottoWallet);
            lotto.setVisibility(View.VISIBLE);*/

            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment_my_wallet()).commit();

        } else if (id == R.id.my_notifications) {
            getSupportActionBar().setTitle("Notificaciones");

            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment_notification()).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}















