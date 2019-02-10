package com.example.baran.mobileprogrammingproject;

import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBar;
    private NavigationView navigationView;
    FirebaseAuth auth;
    Menu leftmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.left_navigation);
        actionBar=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBar);
        actionBar.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         leftmenu = navigationView.getMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               Fragment selectFragment=null;
               switch (menuItem.getItemId())
               {
                   case R.id.nav_home:
                       selectFragment=new HomeFragment();
                       break;
                   case R.id.nav_restaurant:
                       selectFragment=new RestaurantFragment();
                       break;
                   case R.id.nav_pharmacy:
                       selectFragment=new PharmacyFragment();
                       break;
                   case R.id.nav_hotels:
                           selectFragment=new HotelsFragment();
                           break;
                   case R.id.nav_cinema:
                       selectFragment=new CinemaFragment();
                       break;
                   case R.id.nav_hospital:
                       selectFragment=new HospitalFragment();
                       break;
                   case R.id.nav_garden:
                       selectFragment=new GardenFragment();
                       break;
                   case R.string.logout:
                       leftmenu.removeItem(R.string.logout);
                       auth.signOut();
                       selectFragment=new HomeFragment();
                       break;


               }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectFragment).commit();
                drawerLayout.closeDrawers();
                return  true;
            }
        });
    }


    @Override

    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        Fragment selectFragment=null;
        if (actionBar.onOptionsItemSelected(menuItem)) {

            return true;
        }

        return  super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (auth.getCurrentUser()!=null)
        {


            leftmenu.add(Menu.NONE,R.string.logout,Menu.NONE,"Çıkış Yap");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
