package com.example.calculpp;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabAdapter extends FragmentStatePagerAdapter {
    String[] titles = new String[]{"Favorites", "All"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        FavoritesActivity favfragment = new FavoritesActivity();
        AllActivity allfragment = new AllActivity();
        switch (position) {
            case 0:
                return favfragment;
            case 1:
                return allfragment;
            default:
                return favfragment;
        }
    }
//     this counts total number of tabs
    @Override
    public int getCount() {
        return 2;
    }
}