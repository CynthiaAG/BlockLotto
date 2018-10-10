package cynthia.blocklotto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import cynthia.blocklotto.fragment.Fragment_lottery;
import cynthia.blocklotto.fragment.Fragment_my_wallet;
import cynthia.blocklotto.fragment.Fragment_next_lottery;
import cynthia.blocklotto.fragment.Fragment_notification;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState==null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new Fragment_lottery()).commit();
            getSupportActionBar().setTitle("Sorteos");
        }else{
            String p = savedInstanceState.getString("TITLE");
            getSupportActionBar().setTitle(p);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("TITLE", (String) getSupportActionBar().getTitle());
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
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

       /* if (id == R.id.option_search) {
            Intent search = new Intent(this, SearchLottery.class);
            this.startActivity(search);
            return true;
        }
        */
        return super.onOptionsItemSelected(item);
    }


    public void itemSelected(int id, FragmentManager fragmentManager, DrawerLayout drawer){
        switch(id){
            case R.id.my_lotteries:
                actionItemSelected("Sorteos", new Fragment_lottery(), fragmentManager);
                break;
            case R.id.my_wallet:
                actionItemSelected("Wallet", new Fragment_my_wallet(), fragmentManager);
                break;
            case R.id.operation_option:
                actionItemSelected("Operaciones", new Fragment_next_lottery(), fragmentManager);
               break;
            case R.id.backup_wallet:
                actionItemSelected("Backup", new Fragment_my_wallet(), fragmentManager);
                break;
            case R.id.my_notifications:
                actionItemSelected("Notificaciones", new Fragment_notification(), fragmentManager);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
    }


    public void actionItemSelected(String tittle, Fragment fragment, FragmentManager fragmentManager){
        getSupportActionBar().setTitle(tittle);
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
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

        itemSelected(id, fragmentManager, drawer);

        return true;
    }
}