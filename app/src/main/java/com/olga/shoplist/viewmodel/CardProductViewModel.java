package com.olga.shoplist.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.olga.shoplist.domain.entitys.CardProduct;
import com.olga.shoplist.domain.repositorys.CardProductRepository;

import java.util.List;

public class CardProductViewModel extends ViewModel {

    private CardProductRepository repository;

    CardProductViewModel(final CardProductRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<CardProduct>> getListOfData() {
        return repository.getListOfData();
    }

    public LiveData<List<String>> getAvailableShops(String archiveType) {
        return repository.getAvailableShops(archiveType);
    }

    public LiveData<List<CardProduct>> getCardProductByShopName(String shopName, String archiveType) {
        return repository.getCardProductByShopName(shopName,archiveType);
    }

    public LiveData<List<CardProduct>> findCardProductsByArchiveIdAndArchiveType(String archiveId, String archiveType) {
        return repository.findCardProductsByArchiveIdAndArchiveType(archiveId,archiveType);
    }

    public LiveData<CardProduct> getProduct(final String productId) {
        return repository.getProduct(productId);
    }

    public void addCardProduct(final CardProduct product) {
        new AddCardProduct().execute(product);
    }

    private class AddCardProduct extends AsyncTask<CardProduct, Void, Void> {

        @Override
        protected Void doInBackground(final CardProduct... cardProducts) {
            repository.addCardProduct(cardProducts[0]);
            return null;
        }
    }

    public void deleteCardProduct(final CardProduct product) {
        final CardProductViewModel.DeleteCardProduct deleteItemTask = new CardProductViewModel.DeleteCardProduct();
        deleteItemTask.execute(product);
    }

    private class DeleteCardProduct extends AsyncTask<CardProduct, Void, Void> {

        @Override
        protected Void doInBackground(final CardProduct... cardProducts) {
            repository.deleteCardProduct(cardProducts[0]);
            return null;
        }
    }

    public void updateCardProduct(final CardProduct... products) {
        final CardProductViewModel.UpdateCardProduct updateCardProduct = new CardProductViewModel.UpdateCardProduct();
        updateCardProduct.execute(products);
    }

    private class UpdateCardProduct extends AsyncTask<CardProduct, Void, Void> {

        @Override
        protected Void doInBackground(final CardProduct... cardProducts) {
            repository.updateCardProduct(cardProducts);
            return null;
        }
    }
}
