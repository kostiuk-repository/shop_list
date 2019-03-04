package com.olga.shoplist.domain.entitys;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.olga.shoplist.domain.entitys.converter.DateConverter;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "archive")
@TypeConverters(DateConverter.class)
public class Archive {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String archiveId;
    @ColumnInfo(name = "archive_name")
    private String archiveName;
    @ColumnInfo(name = "date")
    private Date date;
    @NonNull
    @ColumnInfo(name = "shop_name")
    private String shopName;


    @Ignore
    public Archive(String archiveName, Date date, @NonNull String shopName) {
        this(UUID.randomUUID().toString(), archiveName, date, shopName);
    }

    public Archive(@NonNull String archiveId, String archiveName, Date date, @NonNull String shopName) {
        this.archiveId = archiveId;
        this.archiveName = archiveName;
        this.date = date;
        this.shopName = shopName;
    }

    @NonNull
    public String getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(@NonNull String archiveId) {
        this.archiveId = archiveId;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NonNull
    public String getShopName() {
        return shopName;
    }

    public void setShopName(@NonNull String shopName) {
        this.shopName = shopName;
    }
}
