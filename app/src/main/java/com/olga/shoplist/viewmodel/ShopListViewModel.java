package com.olga.shoplist.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.olga.shoplist.domain.entitys.ShopList;
import com.olga.shoplist.domain.repositorys.ShopListRepository;

import java.util.List;


public class ShopListViewModel extends ViewModel {

    private ShopListRepository repository;

    ShopListViewModel(final ShopListRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<ShopList>> getProductList() {
        return repository.getProductList();
    }

}
