package com.olga.shoplist.viewlayer.activitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {

    public void addFragmentToActivity(final FragmentManager fragmentManager,
                                      final Fragment fragment,
                                      final int frameId,
                                      final String tag) {

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment, tag);
        transaction.commit();
    }

}
