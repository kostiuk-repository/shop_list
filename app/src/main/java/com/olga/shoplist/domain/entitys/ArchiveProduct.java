package com.olga.shoplist.domain.entitys;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "archive_product", foreignKeys = @ForeignKey(entity = Archive.class,
        parentColumns = "id",
        childColumns = "archive_id",
        onDelete = CASCADE))
public class ArchiveProduct {

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
    @ColumnInfo(name = "archive_id")
    private String archiveId;
    @ColumnInfo(name = "count")
    private Long productCount;


    @Ignore
    public ArchiveProduct(String productName, String productType, String archiveId, Long productCount) {
        this(UUID.randomUUID().toString(), productName, productType, archiveId, productCount);
    }

    public ArchiveProduct(@NonNull String id, @NonNull String productName, @NonNull String productType, String archiveId, Long productCount) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
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

    public String getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }
}
