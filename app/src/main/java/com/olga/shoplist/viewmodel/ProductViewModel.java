package com.olga.shoplist.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.olga.shoplist.domain.entitys.Product;
import com.olga.shoplist.domain.repositorys.ProductRepository;

import java.util.List;


public class ProductViewModel extends ViewModel {

    private ProductRepository repository;

    ProductViewModel(final ProductRepository repository) {
        this.repository = repository;
    }

    public void createNewProduct(final Product product) {
        new AddItemTask().execute(product);
    }

    private class AddItemTask extends AsyncTask<Product, Void, Void> {

        @Override
        protected Void doInBackground(final Product... products) {
            repository.createNewProduct(products[0]);
            return null;
        }
    }

    public LiveData<List<Product>> getListOfData() {
        return repository.getListOfData();
    }

    public LiveData<Product> getProduct(final String productId) {
        return repository.getProduct(productId);
    }

    public void deleteProduct(final Product product) {
        final ProductViewModel.DeleteProduct deleteItemTask = new ProductViewModel.DeleteProduct();
        deleteItemTask.execute(product);
    }

    private class DeleteProduct extends AsyncTask<Product, Void, Void> {

        @Override
        protected Void doInBackground(final Product... shopName) {
            repository.deleteProduct(shopName[0]);
            return null;
        }
    }

}
