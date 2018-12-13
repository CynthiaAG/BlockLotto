package cynthia.blocklotto.start;

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

import cynthia.blocklotto.R;
import cynthia.blocklotto.fragment.about.Fragment_about;
import cynthia.blocklotto.fragment.lottery.Fragment_lottery;
import cynthia.blocklotto.fragment.operation.Fragment_operation;
import cynthia.blocklotto.fragment.wallet.Fragment_my_wallet;
import cynthia.blocklotto.fragment.notification.Fragment_notification;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
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
        moveTaskToBack(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
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
                actionItemSelected("Operaciones", new Fragment_operation(), fragmentManager);
               break;
            case R.id.my_notifications:
                actionItemSelected("Notificaciones", new Fragment_notification(), fragmentManager);
                break;
            case R.id.about_aplication:
                actionItemSelected("Acerca de", new Fragment_about(), fragmentManager);
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        itemSelected(id, fragmentManager, drawer);

        return true;
    }

}