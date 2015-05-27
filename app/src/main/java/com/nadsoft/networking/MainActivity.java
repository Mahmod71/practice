package com.nadsoft.networking;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nadsoft.networking.Fragments.DrawerFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.main_tool_bar));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DrawerFragment drawerFragment=(DrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        drawerFragment.setUp(R.id.fragment,(DrawerLayout)findViewById(R.id.drawerLayout),(Toolbar)findViewById(R.id.main_tool_bar));
    }
}