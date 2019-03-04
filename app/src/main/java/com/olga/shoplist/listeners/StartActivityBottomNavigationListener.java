package com.olga.shoplist.listeners;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.olga.shoplist.R;
import com.olga.shoplist.viewlayer.activitys.StartActivity;
import com.olga.shoplist.viewlayer.fragments.ArchiveFragment;
import com.olga.shoplist.viewlayer.fragments.InfoFragment;
import com.olga.shoplist.viewlayer.fragments.ShopListFragment;

public class StartActivityBottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String SHOP_LIST = "SHOP_LIST";
    private static final String ARCHIVE = "ARCHIVE";
    private static final String INFO = "INFO";
    private StartActivity activity;
    private final FragmentManager manager;
    private final Toolbar toolbar;

    public StartActivityBottomNavigationListener(final StartActivity activity, Toolbar toolbar) {
        this.activity = activity;
        manager = activity.getSupportFragmentManager();
        this.toolbar = toolbar;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                ShopListFragment shopListFragment = (ShopListFragment) manager.findFragmentByTag(SHOP_LIST);

                if (shopListFragment == null) {
                    shopListFragment = ShopListFragment.newInstance();
                }
                toolbar.setTitle("Lista Sklepow");
                activity.addFragmentToActivity(manager, shopListFragment, R.id.fragment_container, SHOP_LIST);
                return true;
            case R.id.navigation_dashboard:
                ArchiveFragment archiveFragment = (ArchiveFragment) manager.findFragmentByTag(ARCHIVE);

                if (archiveFragment == null) {
                    archiveFragment = new ArchiveFragment();
                }
                toolbar.setTitle("Lista Archiwow");
                activity.addFragmentToActivity(manager, archiveFragment, R.id.fragment_container, ARCHIVE);
                return true;
            case R.id.navigation_notifications:
                InfoFragment infoFragment = (InfoFragment) manager.findFragmentByTag(INFO);

                if (infoFragment == null) {
                    infoFragment = new InfoFragment();
                }
                toolbar.setTitle("Informacja");
                activity.addFragmentToActivity(manager, infoFragment, R.id.fragment_container, INFO);
                return true;
        }
        return false;
    }

}
