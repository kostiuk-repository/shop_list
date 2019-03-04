package com.olga.shoplist.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.olga.shoplist.domain.entitys.CardProduct;

import java.util.List;

@Dao
public interface CardProductDao {

    @Insert
    void insert(CardProduct cardProduct);

    @Update
    void update(CardProduct... cardProducts);

    @Delete
    void delete(CardProduct... cardProducts);

    @Query("SELECT * FROM card_product")
    LiveData<List<CardProduct>> getAllCardProducts();

    @Query("SELECT * FROM card_product WHERE shop_n=:shopName AND archive_type=:archiveType")
    LiveData<List<CardProduct>> findCardProductsByShopNameAndArchiveType(final String shopName,final String archiveType);

    @Query("SELECT * FROM card_product WHERE archive_id=:archiveId AND archive_type=:archiveType")
    LiveData<List<CardProduct>> findCardProductsByArchiveIdAndArchiveType(final String archiveId,final String archiveType);

    @Query("SELECT DISTINCT shop_n FROM card_product WHERE archive_type=:archiveType")
    LiveData<List<String>> findAllUsedShops(String archiveType);

    @Query("SELECT * FROM card_product WHERE id=:id")
    LiveData<CardProduct> getCardProduct(final String id);
}
