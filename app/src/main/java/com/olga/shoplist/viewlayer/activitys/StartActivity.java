package com.olga.shoplist.viewlayer.activitys;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.olga.shoplist.R;
import com.olga.shoplist.listeners.StartActivityBottomNavigationListener;
import com.olga.shoplist.viewlayer.dialogs.NotEmptyShopDialog;
import com.olga.shoplist.viewlayer.fragments.ShopListFragment;

import static com.olga.shoplist.listeners.StartActivityBottomNavigationListener.SHOP_LIST;

public class StartActivity extends BaseActivity {


    private BottomNavigationView navigation;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_start);
        final Toolbar myToolbar = this.findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
        this.setSupportActionBar(myToolbar);
        navigation = this.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new StartActivityBottomNavigationListener(this, myToolbar));
        navigation.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
        navigation.getMenu().findItem(R.id.navigation_home).setChecked(true);
        ShopListFragment fragment = (ShopListFragment) getSupportFragmentManager().findFragmentByTag(SHOP_LIST);

        if (fragment == null) {
            fragment = ShopListFragment.newInstance();
        }
        myToolbar.setTitle("Lista Sklepow");
        this.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container, SHOP_LIST);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list:
                NotEmptyShopDialog notEmptyShopDialog = new NotEmptyShopDialog();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                notEmptyShopDialog.show(fragmentTransaction, "NotEmptyShopDialog");
                return true;
            case R.id.plus:
                Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("", "onDestroy: START ACTIVITY");
    }

    public BottomNavigationView getNavigation() {
        return navigation;
    }
}
