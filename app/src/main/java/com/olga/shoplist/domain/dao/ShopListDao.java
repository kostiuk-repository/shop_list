package com.olga.shoplist.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.olga.shoplist.domain.entitys.ShopList;

import java.util.List;

@Dao
public interface ShopListDao {

    @Query("SELECT * FROM product_list")
    LiveData<List<ShopList>> getShopsList();

}
