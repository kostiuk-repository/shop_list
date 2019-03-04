package com.olga.shoplist.domain.repositorys;

import android.arch.lifecycle.LiveData;

import com.olga.shoplist.domain.dao.ProductDao;
import com.olga.shoplist.domain.entitys.Product;

import java.util.List;

import javax.inject.Inject;

public class ProductRepository {

    private final ProductDao productDao;

    @Inject
    public ProductRepository(final ProductDao productDao) {
        this.productDao = productDao;
    }


    public LiveData<List<Product>> getListOfData() {
        return productDao.getProducts();
    }

    public LiveData<Product> getProduct(final String productId) {
        return productDao.getProductById(productId);
    }

    public Long createNewProduct(final Product product) {
        return productDao.insertProduct(product);
    }

    public void deleteProduct(final Product product) {
        productDao.deleteProduct(product);
    }


}
