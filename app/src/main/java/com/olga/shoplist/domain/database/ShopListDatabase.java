package com.olga.shoplist.domain.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.olga.shoplist.domain.dao.ArchiveDao;
import com.olga.shoplist.domain.dao.CardProductDao;
import com.olga.shoplist.domain.dao.ProductDao;
import com.olga.shoplist.domain.dao.ShopListDao;
import com.olga.shoplist.domain.entitys.Archive;
import com.olga.shoplist.domain.entitys.ArchiveProduct;
import com.olga.shoplist.domain.entitys.CardProduct;
import com.olga.shoplist.domain.entitys.Product;
import com.olga.shoplist.domain.entitys.ShopList;


@Database(entities = {Product.class,
        Archive.class,
        ShopList.class,
        CardProduct.class,
        ArchiveProduct.class},
        version = 1)
public abstract class ShopListDatabase extends RoomDatabase {

    public abstract ProductDao shopDao();

    public abstract ArchiveDao archiveDao();

    public abstract ShopListDao shopListDao();

    public abstract CardProductDao cardProductDao();

}
