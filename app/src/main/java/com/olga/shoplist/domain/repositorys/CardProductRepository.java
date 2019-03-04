package com.olga.shoplist.domain.repositorys;

import android.arch.lifecycle.LiveData;

import com.olga.shoplist.domain.dao.CardProductDao;
import com.olga.shoplist.domain.entitys.CardProduct;

import java.util.List;

import javax.inject.Inject;

public class CardProductRepository {

    private final CardProductDao cardProductDao;

    @Inject
    public CardProductRepository(final CardProductDao cardProductDao) {
        this.cardProductDao = cardProductDao;
    }

    public LiveData<List<CardProduct>> getListOfData() {
        return cardProductDao.getAllCardProducts();
    }

    public LiveData<List<String>> getAvailableShops(String archiveType) {
        return cardProductDao.findAllUsedShops(archiveType);
    }

    public LiveData<List<CardProduct>> getCardProductByShopName(String shopName,String archiveType) {
        return cardProductDao.findCardProductsByShopNameAndArchiveType(shopName,archiveType);
    }

    public LiveData<List<CardProduct>> findCardProductsByArchiveIdAndArchiveType(String archiveId,String archiveType) {
        return cardProductDao.findCardProductsByArchiveIdAndArchiveType(archiveId,archiveType);
    }

    public LiveData<CardProduct> getProduct(final String productId) {
        return cardProductDao.getCardProduct(productId);
    }

    public void addCardProduct(final CardProduct product) {
        cardProductDao.insert(product);
    }

    public void updateCardProduct(final CardProduct... products) {
        cardProductDao.update(products);
    }

    public void deleteCardProduct(final CardProduct product) {
        cardProductDao.delete(product);
    }
}
