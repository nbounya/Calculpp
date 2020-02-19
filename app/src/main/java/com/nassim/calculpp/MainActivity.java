package com.nassim.calculpp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "com.nassim.calculpp.DISPLACALCULATOR";

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
        String TabSelectedName = intent.getStringExtra(DisplayCalculatorActivity.BACK_REFRESH);

        //determines the default tab to select, if app just launched, default to the ALL tab
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
