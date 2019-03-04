package com.olga.shoplist.dependencyinjection;

import android.app.Application;

import com.olga.shoplist.viewlayer.activitys.ShopCartListActivity;
import com.olga.shoplist.viewlayer.dialogs.AddToProductListDialog;
import com.olga.shoplist.viewlayer.dialogs.ArchiveNameDialog;
import com.olga.shoplist.viewlayer.dialogs.NotEmptyShopDialog;
import com.olga.shoplist.viewlayer.fragments.ArchiveDetailFragment;
import com.olga.shoplist.viewlayer.fragments.ArchiveFragment;
import com.olga.shoplist.viewlayer.fragments.CardFragment;
import com.olga.shoplist.viewlayer.fragments.InfoFragment;
import com.olga.shoplist.viewlayer.fragments.ProductListFragment;
import com.olga.shoplist.viewlayer.fragments.ShopListFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(ArchiveFragment listFragment);

    void inject(ArchiveDetailFragment archiveDetailFragment);

    void inject(InfoFragment createFragment);

    void inject(CardFragment detailFragment);

    void inject(ProductListFragment detailFragment);

    void inject(ShopListFragment shopListFragment);

    void inject(AddToProductListDialog shopListFragment);

    void inject(NotEmptyShopDialog shopListFragment);

    void inject(ShopCartListActivity shopCartListActivity);

    void inject(ArchiveNameDialog archiveNameDialog);

    Application application();

    RoomModule room();

}
