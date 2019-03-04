package com.olga.shoplist.domain.entitys;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;


@Entity(tableName = "product")
public class Product {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String productName;
    @NonNull
    @ColumnInfo(name = "type")
    private String productType;
    @ColumnInfo(name = "weight")
    private String productUnit;
    @ColumnInfo(name = "count")
    private Long productCount;


    @Ignore
    public Product(String productType, String productUnit, Long productCount) {
        this(UUID.randomUUID().toString(), productType, productUnit, productCount);
    }


    public Product(@NonNull String productName, @NonNull String productType, String productUnit, Long productCount) {
        this.productName = productName;
        this.productType = productType;
        this.productUnit = productUnit;
        this.productCount = productCount;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }


    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public @NonNull
    String getProductType() {
        return productType;
    }

    public void setProductType(@NonNull String productType) {
        this.productType = productType;
    }

    public Long getProductCount() {
        return productCount;
    }
}
