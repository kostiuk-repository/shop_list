package com.olga.shoplist.domain.repositorys;

import android.arch.lifecycle.LiveData;

import com.olga.shoplist.domain.dao.ShopListDao;
import com.olga.shoplist.domain.entitys.ShopList;

import java.util.List;

import javax.inject.Inject;

public class ShopListRepository {

    private final ShopListDao shopListDao;


    @Inject
    public ShopListRepository(final ShopListDao shopListDao) {
        this.shopListDao = shopListDao;
    }


    public LiveData<List<ShopList>> getProductList() {
        return shopListDao.getShopsList();
    }

}
