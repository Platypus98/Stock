package com.example.stock;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.navigation);
        navView.setSelectedItemId(R.id.navigation_stock);

        navView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_buy:
                                selectedFragment = BuyFragment.newInstance();
                               // setTitle(getString(R.string.title_buy));
                                break;
                            case R.id.navigation_sale:
                                selectedFragment = SailingFragment.newInstance();
                                //setTitle(getString(R.string.title_sale));
                                break;
                            case R.id.navigation_stock:
                                selectedFragment = StockFragment.newInstance();
                                //setTitle(getString(R.string.title_stock));
                                break;
                            case R.id.navigation_list:
                                selectedFragment = GraphsFragment.newInstance();
                                //setTitle(getString(R.string.title_list));
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, StockFragment.newInstance());
        transaction.commit();
    }


}
