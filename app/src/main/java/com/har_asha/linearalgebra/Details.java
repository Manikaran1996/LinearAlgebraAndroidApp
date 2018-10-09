package com.har_asha.linearalgebra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by manikaran on 14/2/17.
 */
public class Details extends AppCompatActivity {

    private int[] numberOftabs = {5,  5};
    private CharSequence[][] tabs = {{"Matrix" , "Operations" , "Types of Matrix","Transpose" , "Symmetric Matrices"},
            {"Basics" , "Properties" , "Example 1" , "Example 2" , "Example 3"}
    };
    private int option;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        option = 0;
        if(i != null) {
            option = i.getIntExtra("option",1);
            if(option != 2)
                option = 0;
            else
                option = 1;
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new PagerFragmentAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class PagerFragmentAdapter extends FragmentStatePagerAdapter {

        public PagerFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = DetailsFragment.getInstance(position,option);
            return fragment;
        }

        @Override
        public int getCount() {
            return numberOftabs[option];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Details.this.tabs[Details.this.option][position];
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
