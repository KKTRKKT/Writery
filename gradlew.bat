package com.example.taplayoutdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements Tab1Fragment.WritePageListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TabLayout tabLayout = findViewById(R.id.Tab_Layout);

        tabLayout.addTab(tabLayout.newTab().setIcon(android.R.drawable.ic_menu_edit));
        tabLayout.addTab(tabLayout.newTab().setIcon(android.R.drawable.ic_menu_view));

        final ViewPager pager = findViewById(R.id.pager);

        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onButtonClick(String title, String content) {
        Tab2Fragment tab2Fragment = (Tab2Fragment) getSupportFragmentManager().findFragmentById(R.id.tab2Fragment);
        tab2Fragment.getData(title, content);
    }
}
                                                                                                                                                                                                                                                                                                                                                