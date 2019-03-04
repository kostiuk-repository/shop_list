package com.olga.shoplist.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;

import com.olga.shoplist.domain.entitys.Archive;
import com.olga.shoplist.domain.entitys.ArchiveProduct;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface ArchiveDao {

    @Query("SELECT * FROM archive WHERE id = :archiveId")
    LiveData<Archive> getArchiveById(String archiveId);

    @Query("SELECT * FROM ARCHIVE_PRODUCT WHERE archive_id = :archiveId")
    LiveData<List<ArchiveProduct>> getArchiveProducts(String archiveId);

    @Query("SELECT * FROM archive")
    LiveData<List<Archive>> getArchives();

    @Insert(onConflict = REPLACE)
    Long insertArchive(Archive archive);

    @Delete
    void deleteArchive(Archive archive);

    @RawQuery
    Long insertArchiveProductViaQuery(SupportSQLiteQuery query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertArchiveProduct(ArchiveProduct archiveProduct);

}
