package com.olga.shoplist.domain.entitys;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "card_product", foreignKeys = @ForeignKey(entity = ShopList.class,
        parentColumns = "shop_name",
        childColumns = "shop_n",
        onDelete = CASCADE))
public class CardProduct {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;
    @NonNull
    @ColumnInfo(name = "product_name")
    private String productName;
    @NonNull
    @ColumnInfo(name = "type")
    private String productType;
    @ColumnInfo(name = "shop_n")
    private String shopName;
    @ColumnInfo(name = "archive_type")
    private String archiveType;
    @ColumnInfo(name = "archive_id")
    private String archiveId;
    @ColumnInfo(name = "count")
    private Long productCount;


    @Ignore
    public CardProduct(String productName, String productType, String shopName, Long productCount, String archiveType, String archiveId) {
        this(UUID.randomUUID().toString(), productName, productType, shopName, archiveType, archiveId, productCount);
    }

    public CardProduct(@NonNull String id, @NonNull String productName, @NonNull String productType, String shopName, String archiveType, String archiveId, Long productCount) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
        this.shopName = shopName;
        this.archiveType = archiveType;
        this.archiveId = archiveId;
        this.productCount = productCount;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    @NonNull
    public String getProductType() {
        return productType;
    }

    public void setProductType(@NonNull String productType) {
        this.productType = productType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public String getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(String archiveType) {
        this.archiveType = archiveType;
    }

    public String getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId;
    }
}
