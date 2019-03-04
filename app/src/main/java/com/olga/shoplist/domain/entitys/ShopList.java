package com.olga.shoplist.domain.entitys;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "product_list")
public class ShopList {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "shop_name")
    private String shopName;

    public ShopList(@NonNull final String shopName) {
        this.shopName = shopName;
    }

    @NonNull
    public String getShopName() {
        return shopName;
    }

    public void setShopName(@NonNull final String shopName) {
        this.shopName = shopName;
    }
}
