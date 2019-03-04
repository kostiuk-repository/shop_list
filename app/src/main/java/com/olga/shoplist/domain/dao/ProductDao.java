package com.olga.shoplist.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.olga.shoplist.domain.entitys.Product;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface ProductDao {

    @Query("SELECT * FROM Product WHERE id = :productId")
    LiveData<Product> getProductById(String productId);

    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getProducts();

    @Insert(onConflict = REPLACE)
    Long insertProduct(Product product);

    @Delete
    void deleteProduct(Product product);

}
