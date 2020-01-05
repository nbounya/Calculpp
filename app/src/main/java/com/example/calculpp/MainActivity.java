package com.example.calculpp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.DISPLAYINPUTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        final TabAdapter tabadapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabadapter);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        Intent intent = getIntent();
        String TabSelectedName = intent.getStringExtra(DisplayAllInputsActivity.BACK_REFRESH);

        if (TabSelectedName != null) {
            if (TabSelectedName.equals("all")) {
                TabLayout.Tab tabSelected = tabLayout.getTabAt(1);
                tabSelected.select();
            } else {
                TabLayout.Tab tabSelected = tabLayout.getTabAt(0);
                tabSelected.select();
            }
        } else {
            TabLayout.Tab tabSelected = tabLayout.getTabAt(1);
            tabSelected.select();
        }
    }
}
