package com.olga.shoplist.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.olga.shoplist.domain.repositorys.ArchiveRepository;
import com.olga.shoplist.domain.repositorys.CardProductRepository;
import com.olga.shoplist.domain.repositorys.ProductRepository;
import com.olga.shoplist.domain.repositorys.ShopListRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {

    private final ArchiveRepository archiveRepository;
    private final ShopListRepository shopListRepository;
    private final ProductRepository productRepository;
    private final CardProductRepository cardProductRepository;

    @Inject
    public CustomViewModelFactory(final ArchiveRepository archiveRepository,
                                  final ShopListRepository shopListRepository,
                                  final ProductRepository productRepository,
                                  final CardProductRepository cardProductRepository) {
        this.archiveRepository = archiveRepository;
        this.shopListRepository = shopListRepository;
        this.productRepository = productRepository;
        this.cardProductRepository = cardProductRepository;
    }

    @Override
    public <T extends ViewModel> T create(final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArchiveViewModel.class)) {
            return (T) new ArchiveViewModel(archiveRepository);
        } else if (modelClass.isAssignableFrom(ShopListViewModel.class)) {
            return (T) new ShopListViewModel(shopListRepository);
        } else if (modelClass.isAssignableFrom(ProductViewModel.class)) {
            return (T) new ProductViewModel(productRepository);
        } else if (modelClass.isAssignableFrom(CardProductViewModel.class)) {
            return (T) new CardProductViewModel(cardProductRepository);
        } else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
